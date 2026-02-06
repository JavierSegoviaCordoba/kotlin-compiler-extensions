@file:Suppress("NOTHING_TO_INLINE", "TooManyFunctions")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder
import org.jetbrains.kotlin.ir.builders.declarations.buildFun
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource

public inline val IrFunction.dispatchReceiver: IrValueParameter?
    get() = parameters.firstOrNull(IrValueParameter::isDispatchReceiver)

public inline val IrFunction.contextParameters: List<IrValueParameter>
    get() = parameters.filter(IrValueParameter::isContextParameter)

public inline val IrFunction.extensionReceiver: IrValueParameter?
    get() = parameters.firstOrNull(IrValueParameter::isExtensionReceiver)

public inline val IrFunction.regularParameters: List<IrValueParameter>
    get() = parameters.filter(IrValueParameter::isRegular)

context(context: IrPluginContext)
public inline fun IrFile.firstIrSimpleFunctionSymbol(
    callableId: CallableId
): IrSimpleFunctionSymbol = firstIrSimpleFunctionSymbolOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun firstIrSimpleFunctionSymbol(callableId: CallableId): IrSimpleFunctionSymbol =
    firstIrSimpleFunctionSymbolOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun IrFile.firstIrSimpleFunctionSymbolOrNull(
    callableId: CallableId
): IrSimpleFunctionSymbol? =
    context
        .finderForSource(this@firstIrSimpleFunctionSymbolOrNull)
        .findFunctions(callableId)
        .firstOrNull()

context(context: IrPluginContext)
public inline fun firstIrSimpleFunctionSymbolOrNull(
    callableId: CallableId
): IrSimpleFunctionSymbol? = context.finderForBuiltins().findFunctions(callableId).firstOrNull()

context(context: IrPluginContext)
public inline fun IrFile.firstIrFunctionSymbol(callableId: CallableId): IrFunctionSymbol =
    firstIrFunctionSymbolOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun firstIrFunctionSymbol(callableId: CallableId): IrFunctionSymbol =
    firstIrFunctionSymbolOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun IrFile.firstIrFunctionSymbolOrNull(callableId: CallableId): IrFunctionSymbol? =
    context
        .finderForSource(this@firstIrFunctionSymbolOrNull)
        .findFunctions(callableId)
        .firstOrNull()

context(context: IrPluginContext)
public inline fun firstIrFunctionSymbolOrNull(callableId: CallableId): IrFunctionSymbol? =
    context.finderForBuiltins().findFunctions(callableId).firstOrNull()

context(context: IrPluginContext)
public inline fun IrFile.firstIrSimpleFunction(callableId: CallableId): IrSimpleFunction =
    firstIrSimpleFunctionOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun firstIrSimpleFunction(callableId: CallableId): IrSimpleFunction =
    firstIrSimpleFunctionOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun IrFile.firstIrSimpleFunctionOrNull(callableId: CallableId): IrSimpleFunction? =
    context
        .finderForSource(this@firstIrSimpleFunctionOrNull)
        .findFunctions(callableId)
        .firstOrNull()
        ?.owner

context(context: IrPluginContext)
public inline fun firstIrSimpleFunctionOrNull(callableId: CallableId): IrSimpleFunction? =
    context.finderForBuiltins().findFunctions(callableId).firstOrNull()?.owner

context(context: IrPluginContext)
public inline fun IrFile.firstIrFunction(callableId: CallableId): IrFunction =
    firstIrFunctionOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun firstIrFunction(callableId: CallableId): IrFunction =
    firstIrFunctionOrNull(callableId)!!

context(context: IrPluginContext)
public inline fun IrFile.firstIrFunctionOrNull(callableId: CallableId): IrFunction? =
    context
        .finderForSource(this@firstIrFunctionOrNull)
        .findFunctions(callableId)
        .firstOrNull()
        ?.owner

context(context: IrPluginContext)
public inline fun firstIrFunctionOrNull(callableId: CallableId): IrFunction? =
    context.finderForBuiltins().findFunctions(callableId).firstOrNull()?.owner

context(context: IrPluginContext)
public inline fun createGetterIrSimpleFunction(
    name: Name,
    builder: IrFunctionBuilder.() -> Unit = {},
    function: IrSimpleFunction.() -> Unit = {},
): IrSimpleFunction = createDefaultPropertyAccessor("<get-$name>", builder, function)

context(context: IrPluginContext)
public inline fun createDefaultPropertyAccessor(
    name: String,
    builder: IrFunctionBuilder.() -> Unit = {},
    function: IrSimpleFunction.() -> Unit = {},
): IrSimpleFunction =
    context.irFactory
        .buildFun {
            startOffset = UNDEFINED_OFFSET
            endOffset = UNDEFINED_OFFSET
            this.origin = IrDeclarationOrigin.DEFAULT_PROPERTY_ACCESSOR
            this.visibility = DescriptorVisibilities.LOCAL
            this.isInline = true
            this.name = Name.identifier(name)
            builder(this)
        }
        .apply(function)

context(context: IrPluginContext)
public inline fun createLambdaIrSimpleFunction(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    origin: IrDeclarationOrigin = IrDeclarationOrigin.LOCAL_FUNCTION_FOR_LAMBDA,
    name: Name = SpecialNames.ANONYMOUS,
    visibility: DescriptorVisibility = DescriptorVisibilities.LOCAL,
    isInline: Boolean = false,
    isExpect: Boolean = false,
    returnType: IrType = context.irBuiltIns.nothingType,
    modality: Modality = Modality.FINAL,
    symbol: IrSimpleFunctionSymbol = IrSimpleFunctionSymbolImpl(),
    isTailrec: Boolean = false,
    isSuspend: Boolean = false,
    isOperator: Boolean = false,
    isInfix: Boolean = false,
    isExternal: Boolean = false,
    containerSource: DeserializedContainerSource? = null,
    isFakeOverride: Boolean = origin == IrDeclarationOrigin.FAKE_OVERRIDE,
    block: IrSimpleFunction.() -> Unit = {},
): IrSimpleFunction =
    context.irFactory
        .createSimpleFunction(
            startOffset = startOffset,
            endOffset = endOffset,
            origin = origin,
            name = name,
            visibility = visibility,
            isInline = isInline,
            isExpect = isExpect,
            returnType = returnType,
            modality = modality,
            symbol = symbol,
            isTailrec = isTailrec,
            isSuspend = isSuspend,
            isOperator = isOperator,
            isInfix = isInfix,
            isExternal = isExternal,
            containerSource = containerSource,
            isFakeOverride = isFakeOverride,
        )
        .apply(block)
