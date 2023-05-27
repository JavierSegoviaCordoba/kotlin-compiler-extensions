package com.javiersc.kotlin.compiler.extensions.ir

import kotlin.reflect.KClass
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.name.FqName

public val IrFunctionAccessExpression.packageFqName: FqName
    get() = symbol.owner.kotlinFqName.parent()

public inline fun <reified T : Annotation> T.toFqName(): FqName =
    FqName(requireNotNull(T::class.qualifiedName))

public inline fun <reified T : Annotation> KClass<T>.toFqName(): FqName =
    FqName(requireNotNull(qualifiedName))
