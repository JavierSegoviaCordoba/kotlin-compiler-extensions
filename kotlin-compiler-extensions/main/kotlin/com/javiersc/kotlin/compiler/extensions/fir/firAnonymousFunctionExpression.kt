package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousFunctionExpressionBuilder
import org.jetbrains.kotlin.fir.expressions.builder.buildAnonymousFunctionExpression

public fun createFirAnonymousFunctionExpression(
    anonymousFunction: FirAnonymousFunction,
    builder: FirAnonymousFunctionExpressionBuilder.() -> Unit = {},
): FirAnonymousFunctionExpression = buildAnonymousFunctionExpression {
    this.anonymousFunction = anonymousFunction
    builder()
}
