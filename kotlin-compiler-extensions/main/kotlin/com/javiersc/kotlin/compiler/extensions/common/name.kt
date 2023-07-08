package com.javiersc.kotlin.compiler.extensions.common

import kotlin.reflect.KFunction
import org.jetbrains.kotlin.name.Name

public fun String.toName(): Name = Name.identifier(this)

public fun KFunction<*>.toName(): Name = this.name.toName()
