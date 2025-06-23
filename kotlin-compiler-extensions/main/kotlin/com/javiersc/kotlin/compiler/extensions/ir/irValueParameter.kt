@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrParameterKind
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl

public inline fun IrValueParameter.toIrGetValue(): IrGetValue =
    IrGetValueImpl(
        startOffset = UNDEFINED_OFFSET,
        endOffset = UNDEFINED_OFFSET,
        type = symbol.owner.type,
        symbol = symbol,
    )

public val IrValueParameter.isContextParameter: Boolean
    get() = kind == IrParameterKind.Context

public val IrValueParameter.isDispatchReceiver: Boolean
    get() = kind == IrParameterKind.DispatchReceiver

public val IrValueParameter.isExtensionReceiver: Boolean
    get() = kind == IrParameterKind.ExtensionReceiver

public val IrValueParameter.isRegular: Boolean
    get() = kind == IrParameterKind.Regular
