package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.builders.declarations.IrFunctionBuilder
import org.jetbrains.kotlin.ir.builders.declarations.buildFun
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
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

public val IrFunction.contextReceivers: List<IrValueParameter>
    get() = valueParameters.take(contextReceiverParametersCount)

public fun IrPluginContext.findIrSimpleFunctionSymbol(
    callableId: CallableId
): IrSimpleFunctionSymbol? = referenceFunctions(callableId).firstOrNull()

public fun IrPluginContext.findIrFunctionSymbol(callableId: CallableId): IrFunctionSymbol? =
    referenceFunctions(callableId).firstOrNull()

public fun IrPluginContext.findIrSimpleFunction(callableId: CallableId): IrSimpleFunction? =
    referenceFunctions(callableId).firstOrNull()?.owner

public fun IrPluginContext.findIrFunction(callableId: CallableId): IrFunction? =
    referenceFunctions(callableId).firstOrNull()?.owner

public fun IrPluginContext.createGetterIrSimpleFunction(
    name: Name,
    builder: IrFunctionBuilder.() -> Unit = {},
    function: IrSimpleFunction.() -> Unit = {},
): IrSimpleFunction = createDefaultPropertyAccessor("<get-$name>", builder, function)

public fun IrPluginContext.createDefaultPropertyAccessor(
    name: String,
    builder: IrFunctionBuilder.() -> Unit = {},
    function: IrSimpleFunction.() -> Unit = {},
): IrSimpleFunction =
    irFactory
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

public fun IrPluginContext.createLambdaIrSimpleFunction(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    origin: IrDeclarationOrigin = IrDeclarationOrigin.LOCAL_FUNCTION_FOR_LAMBDA,
    name: Name = SpecialNames.ANONYMOUS,
    visibility: DescriptorVisibility = DescriptorVisibilities.LOCAL,
    isInline: Boolean = false,
    isExpect: Boolean = false,
    returnType: IrType = irBuiltIns.nothingType,
    modality: Modality = Modality.FINAL,
    symbol: IrSimpleFunctionSymbol = IrSimpleFunctionSymbolImpl(),
    isTailrec: Boolean = false,
    isSuspend: Boolean = false,
    isOperator: Boolean = false,
    isInfix: Boolean = false,
    isExternal: Boolean = false,
    containerSource: DeserializedContainerSource? = null,
    isFakeOverride: Boolean = origin == IrDeclarationOrigin.FAKE_OVERRIDE,
    block: IrSimpleFunction.() -> Unit,
): IrSimpleFunction =
    irFactory
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
