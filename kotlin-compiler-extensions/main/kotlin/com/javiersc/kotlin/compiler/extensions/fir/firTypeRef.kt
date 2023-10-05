package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.toClassLikeSymbol
import org.jetbrains.kotlin.fir.scopes.impl.toConeType
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.types.toFirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.toTypeProjection
import org.jetbrains.kotlin.name.ClassId

public fun FirTypeRef.toClassId(session: FirSession): ClassId =
    this.toClassLikeSymbol(session)!!.classId

public fun FirTypeRef.toClassIdOrNull(session: FirSession): ClassId? =
    this.toClassLikeSymbol(session)?.classId

public fun ClassId.toFirTypeRef(vararg typeArguments: ConeTypeProjection): FirTypeRef =
    this.toConeType(*typeArguments).toFirResolvedTypeRef()

public fun FirClassLikeSymbol<*>.toFirTypeRef(): FirTypeRef {
    val types: Array<ConeTypeProjection> =
        this.typeParameterSymbols
            .map { symbol -> symbol.toConeType().toTypeProjection(symbol.variance) }
            .toTypedArray()
    return this.classId.toFirTypeRef(*types)
}
