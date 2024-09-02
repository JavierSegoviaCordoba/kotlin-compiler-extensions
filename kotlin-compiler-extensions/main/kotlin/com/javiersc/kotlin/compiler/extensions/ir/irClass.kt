package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.name.ClassId

public inline fun IrPluginContext.firstIrClass(classId: ClassId): IrClass =
    firstIrClassOrNull(classId)!!

public inline fun IrPluginContext.firstIrClassOrNull(classId: ClassId): IrClass? =
    firstIrClassSymbolOrNull(classId)?.owner

public inline fun IrPluginContext.firstIrClassSymbol(classId: ClassId): IrClassSymbol =
    firstIrClassSymbolOrNull(classId)!!

public inline fun IrPluginContext.firstIrClassSymbolOrNull(classId: ClassId): IrClassSymbol? =
    referenceClass(classId)

public inline val IrClass.exhaustiveKind: IrClassExhaustiveKind
    get() = IrClassExhaustiveKind.from(this)
