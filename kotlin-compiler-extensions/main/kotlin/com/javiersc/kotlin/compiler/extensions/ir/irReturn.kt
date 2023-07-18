package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression
import org.jetbrains.kotlin.ir.expressions.IrReturn
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.symbols.IrReturnTargetSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.SYNTHETIC_OFFSET
import org.jetbrains.kotlin.ir.util.statements
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

public fun createIrReturn(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    type: IrType,
    returnTargetSymbol: IrReturnTargetSymbol,
    value: IrExpression,
    block: IrReturnImpl.() -> Unit = {},
): IrReturn =
    IrReturnImpl(
            startOffset = startOffset,
            endOffset = endOffset,
            type = type,
            returnTargetSymbol = returnTargetSymbol,
            value = value,
        )
        .apply(block)

public val IrFunctionExpression.irReturn: IrReturn?
    get() = function.irReturn

public val IrFunction.irReturn: IrReturn?
    get() = body?.statements?.firstIsInstanceOrNull()

public fun IrFunction.copyIrReturn(value: IrExpression): IrReturn =
    IrReturnImpl(SYNTHETIC_OFFSET, SYNTHETIC_OFFSET, this.returnType, this.symbol, value)
