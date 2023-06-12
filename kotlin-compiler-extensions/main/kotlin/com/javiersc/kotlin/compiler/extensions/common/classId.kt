package com.javiersc.kotlin.compiler.extensions.common

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName

public fun String.toClassId(): ClassId = FqName(this).run { ClassId(parent(), shortName()) }

public fun FqName.toClassId(): ClassId = ClassId(parent(), shortName())

public inline fun <reified T> classId(): ClassId = fqName<T>()
    .toClassId()
