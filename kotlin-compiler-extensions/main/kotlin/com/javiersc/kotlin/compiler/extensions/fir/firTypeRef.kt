package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.scopes.impl.toConeType
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef
import org.jetbrains.kotlin.fir.types.toTypeProjection
import org.jetbrains.kotlin.name.ClassId

public fun ClassId.toFirTypeRef(
    vararg typeArguments: ConeTypeProjection,
    block: FirResolvedTypeRefBuilder.(ClassId) -> Unit = {},
): FirTypeRef = buildResolvedTypeRef {
    val classId = this@toFirTypeRef
    this.type = classId.toConeType(*typeArguments)
    block(this, classId)
}

public fun FirClassLikeSymbol<*>.toFirTypeRef(
    block: FirResolvedTypeRefBuilder.(FirClassLikeSymbol<*>) -> Unit = {},
): FirTypeRef = buildResolvedTypeRef {
    val classSymbol = this@toFirTypeRef
    val types: Array<ConeTypeProjection> =
        typeParameterSymbols
            .map { symbol -> symbol.toConeType().toTypeProjection(symbol.variance) }
            .toTypedArray()
    this.type = classId.toConeType(types)
    block(this, classSymbol)
}
