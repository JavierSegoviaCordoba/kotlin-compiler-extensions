package com.javiersc.kotlin.compiler.extensions.common

import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName

public fun String.toCallableId(): CallableId =
    FqName(this).run { CallableId(parent(), shortName()) }

public fun FqName.toCallableId(): CallableId = CallableId(parent(), shortName())

public fun ClassId.toCallableId(): CallableId = CallableId(packageFqName, this.shortClassName)
