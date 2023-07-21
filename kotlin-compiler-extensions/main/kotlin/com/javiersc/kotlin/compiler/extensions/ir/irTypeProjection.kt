package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.types.IrSimpleType
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.IrTypeProjection
import org.jetbrains.kotlin.ir.types.impl.buildTypeProjection
import org.jetbrains.kotlin.ir.types.impl.toBuilder

public fun IrType.toIrTypeProjection(): IrTypeProjection =
    this.asIrOrNull<IrSimpleType>()?.toBuilder()?.buildTypeProjection()!!

public fun IrSimpleType.toIrTypeProjection(): IrTypeProjection = toIrTypeProjection()
