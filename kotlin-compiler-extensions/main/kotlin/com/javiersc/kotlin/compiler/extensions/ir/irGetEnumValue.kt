package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.declarations.IrEnumEntry
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue
import org.jetbrains.kotlin.ir.expressions.impl.IrGetEnumValueImpl
import org.jetbrains.kotlin.ir.types.IrType

public inline fun IrType.toIrGetEnumValue(enumEntry: IrEnumEntry): IrGetEnumValue =
    IrGetEnumValueImpl(
        startOffset = enumEntry.startOffset,
        endOffset = enumEntry.endOffset,
        type = this,
        symbol = enumEntry.symbol,
    )
