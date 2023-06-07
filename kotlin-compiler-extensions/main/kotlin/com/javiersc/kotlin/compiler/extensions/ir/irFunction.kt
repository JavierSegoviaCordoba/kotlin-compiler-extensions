package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId

public fun IrPluginContext.findIrSimpleFunctionSymbol(
    callableId: CallableId
): IrSimpleFunctionSymbol? = referenceFunctions(callableId).firstOrNull()

public fun IrPluginContext.findIrFunctionSymbol(callableId: CallableId): IrFunctionSymbol? =
    referenceFunctions(callableId).firstOrNull()

public fun IrPluginContext.findIrSimpleFunction(callableId: CallableId): IrSimpleFunction? =
    referenceFunctions(callableId).firstOrNull()?.owner

public fun IrPluginContext.findIrFunction(callableId: CallableId): IrFunction? =
    referenceFunctions(callableId).firstOrNull()?.owner
