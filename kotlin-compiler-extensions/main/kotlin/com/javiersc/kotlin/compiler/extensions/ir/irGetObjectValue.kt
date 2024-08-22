package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.expressions.IrGetObjectValue
import org.jetbrains.kotlin.ir.expressions.impl.IrGetObjectValueImpl
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.defaultType

public fun IrClass.toIrGetObjectValue(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrClassSymbol = this.symbol,
    type: IrType = symbol.owner.defaultType,
): IrGetObjectValue =
    IrGetObjectValueImpl(
        startOffset = startOffset,
        endOffset = endOffset,
        type = type,
        symbol = symbol,
    )
