package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.compiler.extensions.common.classId
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.plugin.createConeType
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.types.ConeClassLikeType
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.fir.types.typeContext
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.types.AbstractTypeChecker

public fun FirSession.isSubtypeOf(subType: ConeKotlinType, superType: ConeKotlinType): Boolean =
    AbstractTypeChecker.isSubtypeOf(typeContext, subType, superType)

public inline fun <reified T> FirSession.coneKotlinType(
    vararg typeArguments: ConeTypeProjection
): ConeKotlinType = this.coneKotlinType<T>(typeArguments.toList().toTypedArray())

@JvmName("coneKotlinTypeWithTypeArgumentsArray")
public inline fun <reified T> FirSession.coneKotlinType(
    typeArguments: Array<ConeTypeProjection> = emptyArray()
): ConeKotlinType = classId<T>().createConeType(this, typeArguments)

public val FirBasedSymbol<*>.coneKotlinType: ConeKotlinType
    get() =
        when (this) {
            is FirCallableSymbol<*> -> resolvedReturnTypeRef.coneType
            else -> TODO()
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
