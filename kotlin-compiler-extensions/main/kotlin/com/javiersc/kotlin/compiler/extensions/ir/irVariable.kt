package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.declarations.impl.IrVariableImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.ir.builders.declarations.buildVariable as buildVariableImpl

public fun IrType.createVariable(
    name: Name,
    parent: IrDeclarationParent? = null,
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    origin: IrDeclarationOrigin = IrDeclarationOrigin.DEFINED,
    isVar: Boolean = false,
    isConst: Boolean = false,
    isLateinit: Boolean = false,
    block: IrVariableImpl.() -> Unit = {},
): IrVariable =
    createVariable(
        name = name,
        type = this,
        parent = parent,
        startOffset = startOffset,
        endOffset = endOffset,
        origin = origin,
        isVar = isVar,
        isConst = isConst,
        isLateinit = isLateinit,
        block = block,
    )

public fun createVariable(
    name: Name,
    type: IrType,
    parent: IrDeclarationParent? = null,
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    origin: IrDeclarationOrigin = IrDeclarationOrigin.DEFINED,
    isVar: Boolean = false,
    isConst: Boolean = false,
    isLateinit: Boolean = false,
    block: IrVariableImpl.() -> Unit = {},
): IrVariable =
    buildVariableImpl(
            parent = parent,
            startOffset = startOffset,
            endOffset = endOffset,
            origin = origin,
            name = name,
            type = type,
            isVar = isVar,
            isConst = isConst,
            isLateinit = isLateinit,
        )
        .apply { block(this as IrVariableImpl) }
