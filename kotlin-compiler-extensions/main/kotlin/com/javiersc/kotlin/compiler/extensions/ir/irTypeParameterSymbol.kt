package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter
import org.jetbrains.kotlin.ir.declarations.IrTypeParametersContainer
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol
import org.jetbrains.kotlin.ir.util.render

public val IrElement.typeParameterSymbols: List<IrTypeParameterSymbol>
    get() =
        when (val irElement: IrElement = this) {
            is IrTypeParametersContainer -> irElement.typeParameters.map(IrTypeParameter::symbol)
            else -> error("`IrElement::typeParameterSymbols` not supported for `${irElement.render()}`")
        }
