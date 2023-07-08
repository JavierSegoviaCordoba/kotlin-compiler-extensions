package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder
import org.jetbrains.kotlin.fir.declarations.builder.buildValueParameter
import org.jetbrains.kotlin.fir.declarations.origin
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.classId
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.fir.types.toFirResolvedTypeRef
import org.jetbrains.kotlin.name.Name

public fun ConeKotlinType.toValueParameter(
    session: FirSession,
    key: GeneratedDeclarationKey,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter = toValueParameter(session, key.origin, containingFunctionSymbol, block)

public fun ConeKotlinType.toValueParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter {
    val coneKotlinType: ConeKotlinType = this
    val typeRef: FirResolvedTypeRef = coneKotlinType.toFirResolvedTypeRef()
    val name: Name =
        checkNotNull(coneKotlinType.classId?.shortClassName) {
            "`ConeKotlinType` must be a `ConeClassLikeType` in order to find the `classId`"
        }
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

public fun FirTypeRef.toValueParameter(
    session: FirSession,
    key: GeneratedDeclarationKey,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter =
    coneType.toValueParameter(session, key.origin, containingFunctionSymbol, block)

public fun FirTypeRef.toValueParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter = coneType.toValueParameter(session, origin, containingFunctionSymbol, block)

public fun FirFunctionSymbol<*>.contextReceiversToValueParameters(
    session: FirSession,
    key: GeneratedDeclarationKey,
    block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
): List<FirValueParameter> = contextReceiversToValueParameters(session, key.origin, block)

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
    key: GeneratedDeclarationKey,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
): FirValueParameter = toValueParameter(session, key.origin, containingFunctionSymbol, block)

public fun FirContextReceiver.toValueParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
): FirValueParameter {
    val builder: FirValueParameterBuilder.(FirTypeRef) -> Unit = { block(this@toValueParameter) }
    return typeRef.coneType.toValueParameter(session, origin, containingFunctionSymbol, builder)
}
