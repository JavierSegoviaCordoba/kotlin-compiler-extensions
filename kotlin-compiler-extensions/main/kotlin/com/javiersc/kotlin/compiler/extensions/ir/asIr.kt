package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.types.IrType

public inline fun <reified O : IrElement> IrElement.asIr(): O = this@asIr as O

public inline fun <reified O : IrType> IrType.asIr(): O = this@asIr as O

public inline fun <reified O : IrElement> IrElement?.asIrOrNull(): O? = this@asIrOrNull as? O

public inline fun <reified O : IrType> IrType?.asIrOrNull(): O? = this@asIrOrNull as? O
