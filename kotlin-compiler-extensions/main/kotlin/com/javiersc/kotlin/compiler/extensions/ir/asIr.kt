package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.types.IrType

public inline fun <reified I : IrType> IrType?.asIr(): I? = this@asIr as? I?

public inline fun <reified I : IrElement> IrElement?.asIr(): I? = this@asIr as? I?
