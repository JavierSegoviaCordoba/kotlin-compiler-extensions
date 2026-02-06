@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.compiler.extensions.common.classId
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrTypeParametersContainer
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.defaultType
import org.jetbrains.kotlin.ir.util.defaultType
import org.jetbrains.kotlin.ir.util.dumpKotlinLike
import org.jetbrains.kotlin.ir.util.makeTypeParameterSubstitutionMap
import org.jetbrains.kotlin.ir.util.render
import org.jetbrains.kotlin.ir.util.substitute
import org.jetbrains.kotlin.name.ClassId

public inline fun Iterable<IrType?>.dumpKotlinLike(): String =
    joinToString("\n") { "${it?.dumpKotlinLike()}" }

public inline val IrElement.irType: IrType
    get() =
        when (val irElement = this) {
            is IrType -> irElement
            is IrFunction -> irElement.returnType
            is IrClass -> irElement.defaultType
            is IrValueParameter -> irElement.type
            is IrProperty -> irElement.getter!!.returnType
            is IrCall -> irElement.type
            else -> error("`IrElement::irType` not supported for `${irElement.render()}`")
        }

context(context: IrPluginContext)
public inline fun <reified T> irType(): IrType {
    val classId: ClassId = classId<T>()
    val irClassSymbol: IrClassSymbol = firstIrClassSymbol(classId)
    return irClassSymbol.defaultType
}

context(context: IrPluginContext)
public inline fun <reified T> IrFile.irType(): IrType {
    val classId: ClassId = classId<T>()
    val irClassSymbol: IrClassSymbol = this@irType.firstIrClassSymbol(classId)
    return irClassSymbol.defaultType
}

public inline fun IrType.substituteOrSelf(
    original: IrTypeParametersContainer,
    transformed: IrTypeParametersContainer,
): IrType = this.substituteOrSelf(makeTypeParameterSubstitutionMap(original, transformed))

public inline fun IrType.substituteOrSelf(
    substitutionMap: Map<IrTypeParameterSymbol, IrType>?
): IrType = substitutionMap?.let { this.substitute(it) } ?: this
