package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

public fun buildCallableId(packageFqName: FqName, callableName: Name): CallableId =
    CallableId(packageFqName, callableName)
