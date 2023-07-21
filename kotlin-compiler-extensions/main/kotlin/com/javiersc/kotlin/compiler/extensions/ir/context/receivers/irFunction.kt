package com.javiersc.kotlin.compiler.extensions.ir.context.receivers

import com.javiersc.kotlin.compiler.extensions.ir.firstIrFunction
import com.javiersc.kotlin.compiler.extensions.ir.firstIrFunctionSymbol
import com.javiersc.kotlin.compiler.extensions.ir.firstIrFunctionSymbolOrNull
import com.javiersc.kotlin.compiler.extensions.ir.firstIrSimpleFunction
import com.javiersc.kotlin.compiler.extensions.ir.firstIrSimpleFunctionSymbol
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.name.CallableId

context(IrPluginContext)
public fun CallableId.firstIrFunctionSymbol(): IrFunctionSymbol = firstIrFunctionSymbol(this)

context(IrPluginContext)
public fun CallableId.firstIrFunctionSymbolOrNull(): IrFunctionSymbol? =
    firstIrFunctionSymbolOrNull(this)

context(IrPluginContext)
public fun CallableId.firstIrSimpleFunction(): IrSimpleFunction = firstIrSimpleFunction(this)

context(IrPluginContext)
public fun CallableId.firstIrSimpleFunctionSymbol(): IrSimpleFunctionSymbol =
    firstIrSimpleFunctionSymbol(this)

context(IrPluginContext)
public fun CallableId.firstIrFunction(): IrFunction = firstIrFunction(this)
