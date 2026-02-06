@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner
import org.jetbrains.kotlin.ir.symbols.IrSymbol

context(context: IrPluginContext)
public inline fun DeclarationIrBuilder(symbol: IrSymbol): DeclarationIrBuilder =
    DeclarationIrBuilder(generatorContext = context, symbol = symbol)

context(context: IrPluginContext)
public inline fun DeclarationIrBuilder(element: IrSymbolOwner): DeclarationIrBuilder =
    DeclarationIrBuilder(generatorContext = context, symbol = element.symbol)
