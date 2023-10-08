package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.expressions.IrBlockBody

public fun IrPluginContext.createIrBlockBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
): IrBlockBody = irFactory.createBlockBody(startOffset, endOffset)

public fun IrPluginContext.createIrBlockBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    initializer: IrBlockBody.() -> Unit,
): IrBlockBody = irFactory.createBlockBody(startOffset, endOffset).apply(initializer)

public fun IrPluginContext.createIrBlockBody(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    statements: List<IrStatement>,
): IrBlockBody =
    irFactory.createBlockBody(startOffset, endOffset).apply { this.statements.addAll(statements) }
