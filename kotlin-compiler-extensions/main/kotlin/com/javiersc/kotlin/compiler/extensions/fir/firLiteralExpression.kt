package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression
import org.jetbrains.kotlin.fir.expressions.builder.buildLiteralExpression
import org.jetbrains.kotlin.types.ConstantValueKind

public fun <T> createFirLiteralExpression(
    kind: ConstantValueKind,
    value: T,
    source: KtSourceElement? = null,
    annotations: MutableList<FirAnnotation> = mutableListOf(),
    setType: Boolean = true,
): FirLiteralExpression =
    buildLiteralExpression(
        source = source,
        kind = kind,
        value = value,
        annotations = annotations,
        setType = setType,
    )
