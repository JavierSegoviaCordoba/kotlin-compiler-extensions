package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.name.FqName

public val IrFunctionAccessExpression.annotations: List<IrConstructorCall>
    get() = symbol.owner.annotations

public inline fun <reified T : Annotation> IrAnnotationContainer.hasAnnotation(): Boolean =
    annotations.hasAnnotation(T::class.toFqName())

public inline fun <reified T : Annotation> IrFunctionAccessExpression.hasAnnotation(): Boolean =
    (this as IrAnnotationContainer).hasAnnotation<T>()

public fun IrFunctionAccessExpression.hasAnnotation(annotation: FqName): Boolean =
    annotations.hasAnnotation(annotation)
