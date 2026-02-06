@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.name.ClassId

context(context: IrPluginContext)
public inline fun IrFile.firstIrClass(classId: ClassId): IrClass = firstIrClassOrNull(classId)!!

context(context: IrPluginContext)
public inline fun firstIrClass(classId: ClassId): IrClass = firstIrClassOrNull(classId)!!

context(context: IrPluginContext)
public inline fun IrFile.firstIrClassOrNull(classId: ClassId): IrClass? =
    firstIrClassSymbolOrNull(classId)?.owner

context(context: IrPluginContext)
public inline fun firstIrClassOrNull(classId: ClassId): IrClass? =
    firstIrClassSymbolOrNull(classId)?.owner

context(context: IrPluginContext)
public inline fun IrFile.firstIrClassSymbol(classId: ClassId): IrClassSymbol =
    firstIrClassSymbolOrNull(classId)!!

context(context: IrPluginContext)
public inline fun firstIrClassSymbol(classId: ClassId): IrClassSymbol =
    firstIrClassSymbolOrNull(classId)!!

public inline val IrClass.exhaustiveKind: IrClassExhaustiveKind
    get() = IrClassExhaustiveKind.from(this)

context(context: IrPluginContext)
public inline fun IrFile.firstIrClassSymbolOrNull(classId: ClassId): IrClassSymbol? =
    context.finderForSource(fromFile = this).findClass(classId)

context(context: IrPluginContext)
public inline fun firstIrClassSymbolOrNull(classId: ClassId): IrClassSymbol? =
    context.finderForBuiltins().findClass(classId)
