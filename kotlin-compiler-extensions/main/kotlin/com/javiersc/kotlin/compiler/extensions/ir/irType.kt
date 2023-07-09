package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.compiler.extensions.common.classId
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.dumpKotlinLike
import org.jetbrains.kotlin.name.ClassId

public fun Iterable<IrType?>.dumpKotlinLike(): String =
    joinToString("\n") { "${it?.dumpKotlinLike()}" }

public val IrElement.irType: IrType
    get() =
        when (this) {
            is IrType -> this
            is IrFunction -> returnType
            is IrClass -> defaultType
            is IrValueParameter -> type
            else -> TODO()
        }

public inline fun <reified T> IrPluginContext.irType(): IrType {
    val classId: ClassId = classId<T>()
    val irClassSymbol: IrClassSymbol = checkNotNull(this.findIrClassSymbol(classId))
    return irClassSymbol.defaultType
}
