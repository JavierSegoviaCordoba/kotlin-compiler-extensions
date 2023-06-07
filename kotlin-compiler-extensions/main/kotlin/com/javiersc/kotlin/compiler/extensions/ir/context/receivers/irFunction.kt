package com.javiersc.kotlin.compiler.extensions.ir.context.receivers

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId

context(IrPluginContext)
public fun CallableId.findIrSimpleFunctionSymbol(): IrSimpleFunctionSymbol? =
    referenceFunctions(this).firstOrNull()

context(IrPluginContext)
public fun CallableId.findIrFunctionSymbol(): IrFunctionSymbol? =
    referenceFunctions(this).firstOrNull()

context(IrPluginContext)
public fun CallableId.findIrSimpleFunction(): IrSimpleFunction? =
    referenceFunctions(this).firstOrNull()?.owner

context(IrPluginContext)
public fun CallableId.findIrFunction(): IrFunction? =
    referenceFunctions(this).firstOrNull()?.owner
