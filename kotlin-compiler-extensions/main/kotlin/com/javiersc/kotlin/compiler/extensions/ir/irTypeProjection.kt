package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.types.IrSimpleType
import org.jetbrains.kotlin.ir.types.IrType
import org.jetbrains.kotlin.ir.types.IrTypeProjection
import org.jetbrains.kotlin.ir.types.impl.buildTypeProjection
import org.jetbrains.kotlin.ir.types.impl.toBuilder
import org.jetbrains.kotlin.types.Variance

public inline fun IrType.toIrTypeProjectionOrNull(
    variance: Variance = Variance.INVARIANT
): IrTypeProjection? = asIrOrNull<IrSimpleType>()?.toIrTypeProjection(variance)

public inline fun IrType.toIrTypeProjection(
    variance: Variance = Variance.INVARIANT
): IrTypeProjection = asIrOrNull<IrSimpleType>()!!.toIrTypeProjection(variance)

public inline fun IrSimpleType.toIrTypeProjection(
    variance: Variance = Variance.INVARIANT
): IrTypeProjection = toBuilder().buildTypeProjection(variance)
