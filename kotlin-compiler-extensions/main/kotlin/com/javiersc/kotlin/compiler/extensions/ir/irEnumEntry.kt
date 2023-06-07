package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry
import org.jetbrains.kotlin.ir.util.isEnumClass

public inline fun <reified T : Enum<T>> IrClass.enumEntry(enum: T): IrEnumEntry? =
    if (isEnumClass) {
        declarations.filterIsInstance<IrEnumEntry>().firstOrNull { enumEntry ->
            "${enumEntry.name}" == enum.name
        }
    } else null
