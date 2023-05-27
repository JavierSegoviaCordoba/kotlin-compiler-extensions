package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId

context(IrPluginContext)
public fun CallableId.findIrSimpleFunctionSymbol(): IrSimpleFunctionSymbol? =
    referenceFunctions(this).firstOrNull()

@JvmName("findIrSimpleFunctionSymbol2")
public fun IrPluginContext.findIrSimpleFunctionSymbol(
    callableId: CallableId
): IrSimpleFunctionSymbol? = referenceFunctions(callableId).firstOrNull()

context(IrPluginContext)
public fun CallableId.findIrFunctionSymbol(): IrFunctionSymbol? =
    referenceFunctions(this).firstOrNull()

@JvmName("findIrFunctionSymbol2")
public fun IrPluginContext.findIrFunctionSymbol(callableId: CallableId): IrFunctionSymbol? =
    referenceFunctions(callableId).firstOrNull()

context(IrPluginContext)
public fun CallableId.findIrSimpleFunction(): IrSimpleFunction? =
    referenceFunctions(this).firstOrNull()?.owner

@JvmName("findIrSimpleFunction2")
public fun IrPluginContext.findIrSimpleFunction(callableId: CallableId): IrSimpleFunction? =
    referenceFunctions(callableId).firstOrNull()?.owner

context(IrPluginContext)
public fun CallableId.findIrFunction(): IrFunction? =
    referenceFunctions(this).firstOrNull()?.owner

@JvmName("findIrFunction2")
public fun IrPluginContext.findIrFunction(callableId: CallableId): IrFunction? =
    referenceFunctions(callableId).firstOrNull()?.owner
