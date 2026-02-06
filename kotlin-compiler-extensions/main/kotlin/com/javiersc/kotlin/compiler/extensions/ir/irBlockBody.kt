@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.expressions.IrBlockBody

context(context: IrPluginContext)
public inline fun createIrBlockBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
): IrBlockBody = context.irFactory.createBlockBody(startOffset, endOffset)

context(context: IrPluginContext)
public inline fun createIrBlockBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    initializer: IrBlockBody.() -> Unit,
): IrBlockBody = context.irFactory.createBlockBody(startOffset, endOffset).apply(initializer)

context(context: IrPluginContext)
public inline fun createIrBlockBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    statements: List<IrStatement>,
): IrBlockBody =
    context.irFactory.createBlockBody(startOffset, endOffset).apply {
        this.statements.addAll(statements)
    }
