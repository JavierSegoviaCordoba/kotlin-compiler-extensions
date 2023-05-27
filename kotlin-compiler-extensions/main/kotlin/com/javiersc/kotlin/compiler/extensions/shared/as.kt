package com.javiersc.kotlin.compiler.extensions.shared

import org.jetbrains.kotlin.ir.IrElement

public inline fun <reified I : IrElement> IrElement?.asIr(): I? = this@asIr as? I?
