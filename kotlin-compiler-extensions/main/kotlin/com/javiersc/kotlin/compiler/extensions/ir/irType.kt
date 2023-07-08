package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.dumpKotlinLike

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
