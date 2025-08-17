package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.declarations.IrParameterKind
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol

public val IrMemberAccessExpression<IrFunctionSymbol>.extensionReceiverIndex: Int
    get() = symbol.owner.parameters.indexOfFirst { it.kind == IrParameterKind.ExtensionReceiver }

public val IrMemberAccessExpression<IrFunctionSymbol>.extensionReceiverArgument: IrExpression?
    get() = arguments[extensionReceiverIndex]
