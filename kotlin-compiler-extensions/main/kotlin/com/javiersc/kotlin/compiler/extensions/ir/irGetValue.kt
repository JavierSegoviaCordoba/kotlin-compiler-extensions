package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.dumpKotlinLike

public inline fun createIrGetValue(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    type: IrType,
    symbol: IrValueSymbol,
    origin: IrStatementOrigin? = null,
    block: IrGetValueImpl.() -> Unit = {},
): IrGetValue =
    IrGetValueImpl(
            startOffset = startOffset,
            endOffset = endOffset,
            type = type,
            symbol = symbol,
            origin = origin,
        )
        .apply(block)

public inline fun IrFunction.toIrGetValue(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrValueSymbol = this.dispatchReceiverParameter!!.symbol,
    type: IrType = symbol.owner.type,
    origin: IrStatementOrigin? = null,
    block: IrGetValueImpl.() -> Unit = {},
): IrGetValue =
    createIrGetValue(
        startOffset = startOffset,
        endOffset = endOffset,
        type = type,
        symbol = symbol,
        origin = origin,
        block = block,
    )

public inline fun IrClass.toIrGetValue(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrValueSymbol =
        this.thisReceiver?.symbol ?: error("${dumpKotlinLike()} has no `thisReceiver`"),
    block: IrGetValueImpl.() -> Unit = {},
): IrGetValue =
    createIrGetValue(
        startOffset = startOffset,
        endOffset = endOffset,
        type = defaultType,
        symbol = symbol,
        origin = null,
        block = block,
    )
