package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.name.Name

public val IrCall.name: Name
    get() = symbol.owner.name

public fun String.toName(): Name = Name.identifier(this)
