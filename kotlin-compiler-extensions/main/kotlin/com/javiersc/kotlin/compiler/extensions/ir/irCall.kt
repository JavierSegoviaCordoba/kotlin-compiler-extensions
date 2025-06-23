@file:Suppress("LongParameterList", "NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.descriptors.SourceElement
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrConstructor
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.types.IrType

public inline fun IrDeclaration.toIrExpression(): IrExpression =
    when (this) {
        is IrFunction -> toIrFunctionAccessExpression()
        is IrValueParameter -> toIrGetValue()
        is IrVariable -> toIrGetValue()
        else -> TODO()
    }

public inline fun IrDeclaration.toIrFunctionAccessExpression(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrFunctionSymbol =
        when (this) {
            is IrFunction -> this.symbol
            else -> TODO()
        },
    type: IrType = symbol.owner.returnType,
    typeArgumentsCount: Int = symbol.owner.typeParameters.size,
    origin: IrStatementOrigin? = null,
    superQualifierSymbol: IrClassSymbol? = null,
    source: SourceElement = SourceElement.NO_SOURCE,
    block: IrFunctionAccessExpression.() -> Unit = {},
): IrFunctionAccessExpression =
    when (this) {
        is IrFunction -> {
            toIrFunctionAccessExpression(
                startOffset = startOffset,
                endOffset = endOffset,
                symbol = symbol,
                type = type,
                typeArgumentsCount = typeArgumentsCount,
                origin = origin,
                superQualifierSymbol = superQualifierSymbol,
                source = source,
                block = block,
            )
        }

        else -> TODO()
    }

public inline fun IrFunction.toIrFunctionAccessExpression(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrFunctionSymbol = this.symbol,
    type: IrType = symbol.owner.returnType,
    typeArgumentsCount: Int = symbol.owner.typeParameters.size,
    origin: IrStatementOrigin? = null,
    superQualifierSymbol: IrClassSymbol? = null,
    source: SourceElement = SourceElement.NO_SOURCE,
    block: IrFunctionAccessExpression.() -> Unit = {},
): IrFunctionAccessExpression =
    when (val function = this) {
        is IrSimpleFunction -> {
            function.toIrCall(
                startOffset = startOffset,
                endOffset = endOffset,
                type = type,
                symbol = symbol as IrSimpleFunctionSymbol,
                typeArgumentsCount = typeArgumentsCount,
                origin = origin,
                superQualifierSymbol = superQualifierSymbol,
                block = block,
            )
        }

        is IrConstructor -> {
            function.toIrConstructorCall(
                startOffset = startOffset,
                endOffset = endOffset,
                type = type,
                symbol = symbol as IrConstructorSymbol,
                typeArgumentsCount = typeArgumentsCount,
                origin = origin,
                source = source,
                block = block,
            )
        }

        else -> TODO()
    }.apply(block)

public inline fun IrSimpleFunction.toIrCall(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrSimpleFunctionSymbol = this.symbol,
    type: IrType = returnType,
    typeArgumentsCount: Int = typeParameters.size,
    origin: IrStatementOrigin? = null,
    superQualifierSymbol: IrClassSymbol? = null,
    block: IrCall.() -> Unit = {},
): IrCall =
    IrCallImpl(
            startOffset = startOffset,
            endOffset = endOffset,
            type = type,
            symbol = symbol,
            typeArgumentsCount = typeArgumentsCount,
            origin = origin,
            superQualifierSymbol = superQualifierSymbol,
        )
        .apply {
            val function = this@toIrCall
            this.dispatchReceiver = function.dispatchReceiverParameter?.toIrGetValue()
            this.insertExtensionReceiver(function.extensionReceiver?.toIrGetValue())

            val functionValueParameters: List<IrValueParameter> = function.parameters
            for ((index: Int, param: IrValueParameter) in functionValueParameters.withIndex()) {
                this.arguments[index] = param.toIrGetValue()
            }
            block()
        }

public inline fun IrConstructor.toIrConstructorCall(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    symbol: IrConstructorSymbol = this.symbol,
    type: IrType = symbol.owner.returnType,
    typeArgumentsCount: Int = symbol.owner.typeParameters.size,
    origin: IrStatementOrigin? = null,
    source: SourceElement = SourceElement.NO_SOURCE,
    block: IrFunctionAccessExpression.() -> Unit = {},
): IrConstructorCall =
    IrConstructorCallImpl(
            startOffset = startOffset,
            endOffset = endOffset,
            type = type,
            symbol = symbol,
            typeArgumentsCount = typeArgumentsCount,
            constructorTypeArgumentsCount = typeArgumentsCount,
            origin = origin,
            source = source,
        )
        .apply(block)
