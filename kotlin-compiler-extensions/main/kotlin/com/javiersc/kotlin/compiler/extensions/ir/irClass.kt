package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.name.ClassId

public fun IrPluginContext.findIrClass(classId: ClassId): IrClass? =
    findIrClassSymbol(classId)?.owner

public fun IrPluginContext.findIrClassSymbol(classId: ClassId): IrClassSymbol? =
    referenceClass(classId)
