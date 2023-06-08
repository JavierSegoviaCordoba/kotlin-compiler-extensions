package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionExpression
import org.jetbrains.kotlin.ir.expressions.IrReturn
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.util.SYNTHETIC_OFFSET
import org.jetbrains.kotlin.ir.util.statements
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

public val IrFunctionExpression.irReturn: IrReturn?
    get() = function.irReturn

public val IrFunction.irReturn: IrReturn?
    get() = body?.statements?.firstIsInstanceOrNull()

public fun IrFunction.copyIrReturn(value: IrExpression): IrReturn =
    IrReturnImpl(SYNTHETIC_OFFSET, SYNTHETIC_OFFSET, this.returnType, this.symbol, value)
