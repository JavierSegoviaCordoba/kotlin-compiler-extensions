package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody

public inline fun IrPluginContext.createExpressionBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    expression: IrExpression,
): IrExpressionBody =
    irFactory.createExpressionBody(
        startOffset = startOffset,
        endOffset = endOffset,
        expression = expression,
    )
