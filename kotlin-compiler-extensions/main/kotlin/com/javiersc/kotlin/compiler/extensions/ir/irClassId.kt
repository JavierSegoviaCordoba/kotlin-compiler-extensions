package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName

public fun String.toClassId(): ClassId = FqName(this).run { ClassId(parent(), shortName()) }

public fun FqName.toClassId(): ClassId = ClassId(parent(), shortName())

public inline fun <reified T> classId(): ClassId = com.javiersc.kotlin.compiler.extensions.common.fqName<T>()
    .toClassId()
