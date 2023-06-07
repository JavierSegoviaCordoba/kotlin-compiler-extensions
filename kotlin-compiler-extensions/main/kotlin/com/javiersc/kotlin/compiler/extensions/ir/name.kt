package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.name.Name

public val IrFunctionAccessExpression.name: Name
    get() = symbol.owner.name

public fun String.toName(): Name = Name.identifier(this)
