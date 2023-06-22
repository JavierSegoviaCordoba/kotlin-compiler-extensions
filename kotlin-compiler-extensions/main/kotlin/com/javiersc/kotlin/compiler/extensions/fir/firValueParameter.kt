package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder
import org.jetbrains.kotlin.fir.declarations.builder.buildValueParameter
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef
import org.jetbrains.kotlin.name.Name

public fun FirClassLikeSymbol<*>.toValueParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    defaultValue: FirExpression? = null,
    block: FirValueParameterBuilder.(FirClassLikeSymbol<*>) -> Unit = {},
): FirValueParameter {
    val classSymbol: FirClassLikeSymbol<*> = this@toValueParameter
    val name: Name = classSymbol.name
    return buildValueParameter {
        this.moduleData = session.moduleData
        this.origin = origin
        this.returnTypeRef = buildResolvedTypeRef { this.type = classSymbol.classId.toConeType() }
        this.name = name
        this.symbol = FirValueParameterSymbol(name)
        this.containingFunctionSymbol = containingFunctionSymbol
        this.defaultValue = defaultValue
        this.isCrossinline = false
        this.isNoinline = false
        this.isVararg = false
        block(this, classSymbol)
    }
}

public fun FirFunctionSymbol<*>.contextReceiversToValueParameters(
    session: FirSession,
    origin: FirDeclarationOrigin,
    block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
): List<FirValueParameter> {
    val contextReceivers: List<FirContextReceiver> = this.resolvedContextReceivers
    return contextReceivers.map { contextReceiver ->
        contextReceiver.toValueParameter(session, origin, this, block)
    }
}

public fun FirContextReceiver.toValueParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
): FirValueParameter {
    val name: Name = typeRefName ?: Name.special("<anonymous>")
    return buildValueParameter {
        val contextReceiver = this@toValueParameter
        this.moduleData = session.moduleData
        this.origin = origin
        this.returnTypeRef = contextReceiver.typeRef
        this.name = name
        this.symbol = FirValueParameterSymbol(name)
        this.containingFunctionSymbol = containingFunctionSymbol
        this.isCrossinline = false
        this.isNoinline = false
        this.isVararg = false
        block(this, contextReceiver)
    }
}
