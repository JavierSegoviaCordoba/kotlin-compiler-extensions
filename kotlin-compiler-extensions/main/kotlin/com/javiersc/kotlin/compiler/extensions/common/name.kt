package com.javiersc.kotlin.compiler.extensions.common

import org.jetbrains.kotlin.name.Name

public fun String.toName(): Name = Name.identifier(this)
