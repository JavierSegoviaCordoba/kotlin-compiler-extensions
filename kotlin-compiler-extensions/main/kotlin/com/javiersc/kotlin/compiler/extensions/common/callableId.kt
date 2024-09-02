package com.javiersc.kotlin.compiler.extensions.common

import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName

public inline fun String.toCallableId(): CallableId =
    FqName(this).run { CallableId(parent(), shortName()) }

public inline fun FqName.toCallableId(): CallableId = CallableId(parent(), shortName())

public inline fun ClassId.toCallableId(): CallableId =
    CallableId(packageFqName, this.shortClassName)

public inline fun <reified T> callableId(): CallableId = fqName<T>().toCallableId()
