package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.IrTypeArgument
import org.jetbrains.kotlin.ir.types.IrTypeProjection
import org.jetbrains.kotlin.ir.util.dumpKotlinLike

public inline fun Iterable<IrTypeArgument?>.dumpKotlinLike(): String =
    joinToString("\n") { "${it?.dumpKotlinLike()}" }

public inline val IrTypeArgument.type: IrType
    get() = (this as IrTypeProjection).type

public inline val IrTypeArgument.irType: IrType
    get() = type
