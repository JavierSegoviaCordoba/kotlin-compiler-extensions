package com.javiersc.kotlin.compiler.extensions.ir

import kotlin.reflect.KClass
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.name.FqName

public fun String.toFqName(): FqName = FqName(this)

public val IrFunctionAccessExpression.packageFqName: FqName
    get() = symbol.owner.kotlinFqName.parent()

public inline fun <reified T> fqName(): FqName = T::class.toFqName()

public inline fun <reified T> packageFqName(): FqName = T::class.toFqName().parent()

public fun KClass<*>.toFqName(): FqName = FqName(requireNotNull(qualifiedName))
