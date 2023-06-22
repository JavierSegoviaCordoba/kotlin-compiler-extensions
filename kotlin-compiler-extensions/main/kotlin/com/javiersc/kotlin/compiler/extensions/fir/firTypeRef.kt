package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.scopes.impl.toConeType
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef
import org.jetbrains.kotlin.fir.types.toTypeProjection

public fun FirClassLikeSymbol<*>.toFirTypeRef(): FirTypeRef = buildResolvedTypeRef {
    val types: Array<ConeTypeProjection> =
        typeParameterSymbols
            .map { symbol -> symbol.toConeType().toTypeProjection(symbol.variance) }
            .toTypedArray()
    this.type = classId.toConeType(types)
}
