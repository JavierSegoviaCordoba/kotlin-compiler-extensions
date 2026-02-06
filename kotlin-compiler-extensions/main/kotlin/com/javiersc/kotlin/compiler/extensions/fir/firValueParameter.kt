@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder
import org.jetbrains.kotlin.fir.declarations.builder.buildValueParameter
import org.jetbrains.kotlin.fir.declarations.origin
import org.jetbrains.kotlin.fir.declarations.primaryConstructorIfAny
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol
import org.jetbrains.kotlin.fir.toFirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.classId
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.fir.types.coneTypeOrNull
import org.jetbrains.kotlin.name.Name

context(session: FirSession)
public inline fun FirBasedSymbol<*>.valueParameters(): List<FirValueParameter> =
    when (val fir = this.fir) {
        is FirFunction -> fir.valueParameters
        is FirRegularClass -> fir.primaryConstructorIfAny(session)?.fir?.valueParameters.orEmpty()
        is FirCallableDeclaration -> {
            val declaration: FirFunction? = fir.asFirOrNull<FirFunction>()
            declaration?.valueParameters.orEmpty()
        }

        else -> emptyList()
    }

context(session: FirSession)
public inline fun ConeKotlinType.toValueParameter(
    key: GeneratedDeclarationKey,
    containingDeclarationSymbol: FirBasedSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter = toValueParameter(key.origin, containingDeclarationSymbol, block)

context(session: FirSession)
public inline fun ConeKotlinType.toValueParameter(
    origin: FirDeclarationOrigin,
    containingDeclarationSymbol: FirBasedSymbol<*>,
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
        this.symbol = FirValueParameterSymbol()
        this.containingDeclarationSymbol = containingDeclarationSymbol
        this.isCrossinline = false
        this.isNoinline = false
        this.isVararg = false
        block(this, typeRef)
    }
}

context(session: FirSession)
public inline fun FirTypeRef.toValueParameter(
    key: GeneratedDeclarationKey,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter =
    with(session) { coneType.toValueParameter(key.origin, containingFunctionSymbol, block) }

context(session: FirSession)
public inline fun FirTypeRef.toValueParameterOrNull(
    key: GeneratedDeclarationKey,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter? =
    with(session) { coneTypeOrNull?.toValueParameter(key.origin, containingFunctionSymbol, block) }

context(session: FirSession)
public inline fun FirTypeRef.toValueParameter(
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter =
    with(session) { coneType.toValueParameter(origin, containingFunctionSymbol, block) }

context(session: FirSession)
public inline fun FirTypeRef.toValueParameterOrNull(
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    block: FirValueParameterBuilder.(FirTypeRef) -> Unit = {},
): FirValueParameter? =
    with(session) { coneTypeOrNull?.toValueParameter(origin, containingFunctionSymbol, block) }

// context(session: FirSession)
// public inline fun FirFunctionSymbol<*>.contextReceiversToValueParameters(
//    key: GeneratedDeclarationKey,
//    crossinline block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
// ): List<FirValueParameter> = contextReceiversToValueParameters(session, key.origin, block)
//
// context(session: FirSession)
// public inline fun FirFunctionSymbol<*>.contextReceiversToValueParameters(
//    origin: FirDeclarationOrigin,
//    crossinline block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
// ): List<FirValueParameter> {
//    val contextReceivers: List<FirContextReceiver> = this.resolvedContextReceivers
//    return contextReceivers.mapNotNull { contextReceiver ->
//        contextReceiver.toValueParameterOrNull(session, origin, this, block)
//    }
// }
//
// context(session: FirSession)
// public inline fun FirContextReceiver.toValueParameter(
//    key: GeneratedDeclarationKey,
//    containingFunctionSymbol: FirFunctionSymbol<*>,
//    crossinline block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
// ): FirValueParameter = toValueParameter(session, key.origin, containingFunctionSymbol, block)
//
// context(session: FirSession)
// public inline fun FirContextReceiver.toValueParameterOrNull(
//    key: GeneratedDeclarationKey,
//    containingFunctionSymbol: FirFunctionSymbol<*>,
//    crossinline block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
// ): FirValueParameter? = toValueParameterOrNull(session, key.origin, containingFunctionSymbol,
// block)
//
// context(session: FirSession)
// public inline fun FirContextReceiver.toValueParameter(
//    origin: FirDeclarationOrigin,
//    containingFunctionSymbol: FirFunctionSymbol<*>,
//    crossinline block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
// ): FirValueParameter {
//    val builder: FirValueParameterBuilder.(FirTypeRef) -> Unit = { block(this@toValueParameter) }
//    return typeRef.coneType.toValueParameter(session, origin, containingFunctionSymbol, builder)
// }
//
// context(session: FirSession)
// public inline fun FirContextReceiver.toValueParameterOrNull(
//    origin: FirDeclarationOrigin,
//    containingFunctionSymbol: FirFunctionSymbol<*>,
//    crossinline block: FirValueParameterBuilder.(FirContextReceiver) -> Unit = {},
// ): FirValueParameter? {
//    val builder: FirValueParameterBuilder.(FirTypeRef) -> Unit = {
//        block(this@toValueParameterOrNull)
//    }
//    return typeRef.coneTypeOrNull?.toValueParameter(
//        session,
//        origin,
//        containingFunctionSymbol,
//        builder,
//    )
// }
