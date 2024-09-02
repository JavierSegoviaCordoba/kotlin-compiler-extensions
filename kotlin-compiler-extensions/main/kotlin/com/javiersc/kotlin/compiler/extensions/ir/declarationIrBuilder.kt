package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner
import org.jetbrains.kotlin.ir.symbols.IrSymbol

public inline fun IrPluginContext.declarationIrBuilder(symbol: IrSymbol): DeclarationIrBuilder =
    DeclarationIrBuilder(this, symbol)

public inline fun IrPluginContext.declarationIrBuilder(
    element: IrSymbolOwner
): DeclarationIrBuilder = DeclarationIrBuilder(this, element.symbol)
