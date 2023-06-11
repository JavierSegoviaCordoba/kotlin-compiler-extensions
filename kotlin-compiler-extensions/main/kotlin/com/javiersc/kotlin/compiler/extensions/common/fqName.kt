package com.javiersc.kotlin.compiler.extensions.common

import kotlin.reflect.KClass
import org.jetbrains.kotlin.name.FqName

public fun String.toFqName(): FqName = FqName(this)

public inline fun <reified T> fqName(): FqName = T::class.toFqName()

public inline fun <reified T> packageFqName(): FqName = T::class.toFqName().parent()

public fun KClass<*>.toFqName(): FqName = FqName(requireNotNull(qualifiedName))
