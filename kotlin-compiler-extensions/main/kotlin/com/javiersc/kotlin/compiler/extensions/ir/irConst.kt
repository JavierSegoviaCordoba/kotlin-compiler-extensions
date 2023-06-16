package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.SYNTHETIC_OFFSET
import org.jetbrains.kotlin.ir.interpreter.toIrConst as originalToIrConst

public fun IrExpression.toIrConst(
    irType: IrType = type,
    startOffset: Int = SYNTHETIC_OFFSET,
    endOffset: Int = SYNTHETIC_OFFSET,
): IrConst<*> =
    originalToIrConst(
        irType = irType,
        startOffset = startOffset,
        endOffset = endOffset,
    )

public fun IrExpression?.toIrConstOrNull(
    irType: IrType? = this?.type,
    startOffset: Int = SYNTHETIC_OFFSET,
    endOffset: Int = SYNTHETIC_OFFSET,
): IrConst<*>? =
    if (this != null && irType != null) {
        runCatching { toIrConst(irType = irType, startOffset = startOffset, endOffset = endOffset) }
            .getOrNull()
    } else {
        null
    }
