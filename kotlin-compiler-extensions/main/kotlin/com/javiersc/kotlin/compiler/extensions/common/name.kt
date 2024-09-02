package com.javiersc.kotlin.compiler.extensions.common

import kotlin.reflect.KFunction
import org.jetbrains.kotlin.name.Name

public inline fun String.toName(): Name = Name.identifier(this)

public inline fun KFunction<*>.toName(): Name = this.name.toName()
