package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType

public inline val ConeKotlinType.isTypeParameterType: Boolean
    get() = this is ConeTypeParameterType
