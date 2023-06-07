package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName

public fun String.toCallableId(): CallableId =
    FqName(this).run { CallableId(parent(), shortName()) }

public fun FqName.toCallableId(): CallableId = CallableId(parent(), shortName())

public val IrFunctionAccessExpression.callableId: CallableId
    get() = CallableId(packageFqName, name)
