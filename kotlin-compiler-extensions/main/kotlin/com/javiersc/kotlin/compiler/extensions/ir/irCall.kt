@file:Suppress("LongParameterList")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.IrType

public fun IrDeclaration.toIrCall(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrFunctionSymbol =
        when (this) {
            is IrSimpleFunction -> this.symbol
            else -> TODO()
        },
    type: IrType = symbol.owner.returnType,
    typeArgumentsCount: Int = symbol.owner.typeParameters.size,
    valueArgumentsCount: Int = symbol.owner.valueParameters.size,
    origin: IrStatementOrigin? = null,
    superQualifierSymbol: IrClassSymbol? = null,
    block: IrCallImpl.() -> Unit = {},
): IrCall =
    when (this) {
        is IrSimpleFunction ->
            toIrCall(
                startOffset = startOffset,
                endOffset = endOffset,
                symbol = symbol as IrSimpleFunctionSymbol,
                type = type,
                typeArgumentsCount = typeArgumentsCount,
                valueArgumentsCount = valueArgumentsCount,
                origin = origin,
                superQualifierSymbol = superQualifierSymbol,
                block = block,
            )
        else -> TODO()
    }

public fun IrSimpleFunction.toIrCall(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrSimpleFunctionSymbol = this.symbol,
    type: IrType = symbol.owner.returnType,
    typeArgumentsCount: Int = symbol.owner.typeParameters.size,
    valueArgumentsCount: Int = symbol.owner.valueParameters.size,
    origin: IrStatementOrigin? = null,
    superQualifierSymbol: IrClassSymbol? = null,
    block: IrCallImpl.() -> Unit = {},
): IrCall =
    IrCallImpl(
            startOffset = startOffset,
            endOffset = endOffset,
            type = type,
            symbol = symbol,
            typeArgumentsCount = typeArgumentsCount,
            valueArgumentsCount = valueArgumentsCount,
            origin = origin,
            superQualifierSymbol = superQualifierSymbol,
        )
        .apply(block)

public fun IrSimpleFunctionSymbol.toIrCall(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    type: IrType = owner.returnType,
    typeArgumentsCount: Int = owner.typeParameters.size,
    valueArgumentsCount: Int = owner.valueParameters.size,
    origin: IrStatementOrigin? = null,
    superQualifierSymbol: IrClassSymbol? = null,
    block: IrCallImpl.() -> Unit = {},
): IrCall =
    IrCallImpl.fromSymbolOwner(
            startOffset = startOffset,
            endOffset = endOffset,
            type = type,
            symbol = this,
            typeArgumentsCount = typeArgumentsCount,
            valueArgumentsCount = valueArgumentsCount,
            origin = origin,
            superQualifierSymbol = superQualifierSymbol,
        )
        .apply(block)
