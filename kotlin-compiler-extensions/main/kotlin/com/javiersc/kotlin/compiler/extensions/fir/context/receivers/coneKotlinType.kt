package com.javiersc.kotlin.compiler.extensions.fir.context.receivers

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.typeContext
import org.jetbrains.kotlin.types.AbstractTypeChecker

context(FirSession)
public fun ConeKotlinType.isSubtypeOf(superType: ConeKotlinType): Boolean =
    AbstractTypeChecker.isSubtypeOf(typeContext, this, superType)
