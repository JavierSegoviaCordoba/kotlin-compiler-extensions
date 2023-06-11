package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.dumpKotlinLike
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

public fun Iterable<IrElement>.dump(): String = joinToString("\n") { it.dump() }

public fun Iterable<IrElement>.dumpKotlinLike(): String = joinToString("\n") { it.dumpKotlinLike() }

public fun IrElement.hasAnnotation(annotation: FqName): Boolean =
    when (this) {
        is IrClass -> hasAnnotation(annotation)
        is IrFunctionAccessExpression -> hasAnnotation(annotation)
        else -> false
    }
