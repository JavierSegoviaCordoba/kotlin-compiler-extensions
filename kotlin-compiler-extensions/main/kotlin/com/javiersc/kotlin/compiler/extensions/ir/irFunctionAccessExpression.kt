package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.compiler.extensions.common.fqName
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.hasAnnotation
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

public inline val IrFunctionAccessExpression.callableId: CallableId
    get() = CallableId(packageFqName, name)

public inline val IrFunctionAccessExpression.packageFqName: FqName
    get() = symbol.owner.kotlinFqName.parent()

public inline val IrFunctionAccessExpression.name: Name
    get() = symbol.owner.name

public inline val IrFunctionAccessExpression.annotations: List<IrConstructorCall>
    get() = symbol.owner.annotations

public inline fun <reified T : Annotation> IrFunctionAccessExpression.hasAnnotation(): Boolean =
    annotations.hasAnnotation(fqName<T>())

public inline fun IrFunctionAccessExpression.hasAnnotation(annotation: FqName): Boolean =
    annotations.hasAnnotation(annotation)
