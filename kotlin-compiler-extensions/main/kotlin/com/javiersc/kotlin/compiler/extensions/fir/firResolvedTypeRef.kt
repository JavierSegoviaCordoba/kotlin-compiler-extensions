package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef

public inline fun createFirResolvedTypeRef(
    type: ConeKotlinType,
    builder: FirResolvedTypeRefBuilder.() -> Unit = {},
): FirResolvedTypeRef = buildResolvedTypeRef {
    this.coneType = type
    builder()
}
