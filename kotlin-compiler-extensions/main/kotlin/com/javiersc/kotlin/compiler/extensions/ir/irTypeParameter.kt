package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter
import org.jetbrains.kotlin.ir.declarations.IrTypeParametersContainer
import org.jetbrains.kotlin.ir.util.render

public inline val IrElement.typeParameters: List<IrTypeParameter>
    get() =
        when (val irElement: IrElement = this) {
            is IrTypeParametersContainer -> irElement.typeParameters
            else -> error("`IrElement::typeParameters` not supported for `${irElement.render()}`")
        }
