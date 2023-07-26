package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.KtSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotation
import org.jetbrains.kotlin.fir.expressions.FirConstExpression
import org.jetbrains.kotlin.fir.expressions.builder.buildConstExpression
import org.jetbrains.kotlin.types.ConstantValueKind

public fun <T> createFirConstExpression(
    kind: ConstantValueKind<T>,
    value: T,
    source: KtSourceElement? = null,
    annotations: MutableList<FirAnnotation> = mutableListOf(),
    setType: Boolean = true
): FirConstExpression<T> =
    buildConstExpression(
        source = source,
        kind = kind,
        value = value,
        annotations = annotations,
        setType = setType,
    )
