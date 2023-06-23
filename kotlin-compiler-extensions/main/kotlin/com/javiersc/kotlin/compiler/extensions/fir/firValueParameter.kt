package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder
import org.jetbrains.kotlin.fir.declarations.builder.buildValueParameter
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.name.Name

public fun FirTypeRef.toValueParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter {
    val typeRef: FirTypeRef = this
    val name: Name = typeRef.toClassId(session).shortClassName
    return buildValueParameter {
        this.moduleData = session.moduleData
        this.origin = origin
        this.returnTypeRef = typeRef
        this.name = name
        this.symbol = FirValueParameterSymbol(name)
        this.containingFunctionSymbol = containingFunctionSymbol
        this.isCrossinline = false
        this.isNoinline = false
        this.isVararg = false
        block(this, typeRef)
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
