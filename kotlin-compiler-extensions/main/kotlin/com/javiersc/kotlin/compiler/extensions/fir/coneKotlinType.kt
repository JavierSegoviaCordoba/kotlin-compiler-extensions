package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.types.ConeClassLikeType
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.name.ClassId

public val FirBasedSymbol<*>.coneKotlinType: ConeKotlinType?
    get() =
        when (this) {
            is FirCallableSymbol<*> -> resolvedReturnTypeRef.coneType
            else -> null
        }

public fun ClassId.toConeType(vararg typeArguments: ConeTypeProjection): ConeClassLikeType {
    val lookupTag = ConeClassLikeLookupTagImpl(this)
    return ConeClassLikeTypeImpl(lookupTag, typeArguments, isNullable = false)
}

@JvmName("toConeTypeWithTypeArgumentsArray")
public fun ClassId.toConeType(typeArguments: Array<ConeTypeProjection>): ConeClassLikeType {
    val lookupTag = ConeClassLikeLookupTagImpl(this)
    return ConeClassLikeTypeImpl(lookupTag, typeArguments, isNullable = false)
}
