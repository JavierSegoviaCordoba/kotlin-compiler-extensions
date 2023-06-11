package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.name.CallableId

public val IrFunctionAccessExpression.callableId: CallableId
    get() = CallableId(packageFqName, name)
