package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder
import org.jetbrains.kotlin.fir.expressions.builder.buildAnnotation
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping
import org.jetbrains.kotlin.fir.types.FirTypeRef

public fun createFirAnnotation(
    annotationTypeRef: FirTypeRef,
    argumentMapping: FirAnnotationArgumentMapping = FirEmptyAnnotationArgumentMapping,
    builder: FirAnnotationBuilder.() -> Unit = {},
): FirAnnotation = buildAnnotation {
    this.annotationTypeRef = annotationTypeRef
    this.argumentMapping = argumentMapping
    builder()
}

public fun FirTypeRef.toFirAnnotation(
    argumentMapping: FirAnnotationArgumentMapping = FirEmptyAnnotationArgumentMapping,
    builder: FirAnnotationBuilder.() -> Unit = {},
): FirAnnotation = createFirAnnotation(this, argumentMapping, builder)
