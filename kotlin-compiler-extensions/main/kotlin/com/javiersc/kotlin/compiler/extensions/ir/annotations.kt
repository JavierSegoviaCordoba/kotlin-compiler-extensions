package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

public val IrFunctionAccessExpression.annotations: List<IrConstructorCall>
    get() = symbol.owner.annotations

public inline fun <reified T : Annotation> IrAnnotationContainer.hasAnnotation(): Boolean =
    annotations.hasAnnotation(fqName<T>())

public inline fun <reified T : Annotation> IrFunctionAccessExpression.hasAnnotation(): Boolean =
    annotations.hasAnnotation(fqName<T>())

public fun IrFunctionAccessExpression.hasAnnotation(annotation: FqName): Boolean =
    annotations.hasAnnotation(annotation)

public fun IrElement.hasAnnotation(annotation: FqName): Boolean =
    when (this) {
        is IrClass -> hasAnnotation(annotation)
        is IrFunctionAccessExpression -> hasAnnotation(annotation)
        else -> false
    }
