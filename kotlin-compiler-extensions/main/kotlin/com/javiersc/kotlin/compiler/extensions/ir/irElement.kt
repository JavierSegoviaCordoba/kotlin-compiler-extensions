@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.compiler.extensions.common.toFqName
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.dumpKotlinLike
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.name.FqName

public inline fun Iterable<IrElement>.dump(): String = joinToString("\n") { it.dump() }

public inline fun Iterable<IrElement>.dumpKotlinLike(): String =
    joinToString("\n") { it.dumpKotlinLike() }

public inline fun <reified T : Annotation> IrElement.hasAnnotation(): Boolean =
    hasAnnotation(T::class.toFqName())

public inline fun IrElement.hasAnnotation(annotation: FqName): Boolean =
    when (this) {
        is IrClass -> hasAnnotation(annotation)
        is IrFunctionAccessExpression -> hasAnnotation(annotation)
        is IrAnnotationContainer -> hasAnnotation(annotation)
        else -> false
    }

public inline val IrElement.contextParameters: List<IrValueParameter>
    get() =
        when (this) {
            is IrFunction -> contextParameters
            is IrClass -> primaryConstructor?.contextParameters.orEmpty()
            else -> TODO()
        }
