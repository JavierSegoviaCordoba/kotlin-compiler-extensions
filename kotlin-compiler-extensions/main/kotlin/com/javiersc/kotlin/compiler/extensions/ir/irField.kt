package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.descriptors.DescriptorVisibility
import org.jetbrains.kotlin.ir.UNDEFINED_OFFSET
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.impl.IrFieldSymbolImpl
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.name.Name

public fun IrPluginContext.createIrField(
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    origin: IrDeclarationOrigin = IrDeclarationOrigin.DEFINED,
    name: Name,
    visibility: DescriptorVisibility = DescriptorVisibilities.LOCAL,
    symbol: IrFieldSymbol = IrFieldSymbolImpl(),
    type: IrType,
    isFinal: Boolean = true,
    isStatic: Boolean = false,
    isExternal: Boolean = false,
    block: IrField.() -> Unit = {},
): IrField =
    irFactory
        .createField(
            startOffset = startOffset,
            endOffset = endOffset,
            origin = origin,
            name = name,
            visibility = visibility,
            symbol = symbol,
            type = type,
            isFinal = isFinal,
            isStatic = isStatic,
            isExternal = isExternal,
        )
        .apply(block)

public fun IrValueParameter.toIrField(
    pluginContext: IrPluginContext,
    startOffset: Int = UNDEFINED_OFFSET,
    endOffset: Int = UNDEFINED_OFFSET,
    origin: IrDeclarationOrigin = IrDeclarationOrigin.DEFINED,
    name: Name = this.name,
    visibility: DescriptorVisibility = DescriptorVisibilities.LOCAL,
    symbol: IrFieldSymbol = IrFieldSymbolImpl(),
    type: IrType = this.type,
    isFinal: Boolean = true,
    isStatic: Boolean = false,
    isExternal: Boolean = false,
    block: IrField.() -> Unit = {},
): IrField =
    pluginContext
        .createIrField(
            startOffset = startOffset,
            endOffset = endOffset,
            origin = origin,
            name = name,
            visibility = visibility,
            symbol = symbol,
            type = type,
            isFinal = isFinal,
            isStatic = isStatic,
            isExternal = isExternal,
        )
        .apply(block)
