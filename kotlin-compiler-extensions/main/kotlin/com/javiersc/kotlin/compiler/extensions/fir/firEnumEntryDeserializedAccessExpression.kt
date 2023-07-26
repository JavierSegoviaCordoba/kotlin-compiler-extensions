package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.compiler.extensions.common.classId
import com.javiersc.kotlin.compiler.extensions.common.toName
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression
import org.jetbrains.kotlin.fir.expressions.builder.buildEnumEntryDeserializedAccessExpression
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name

public inline fun <reified T : Enum<T>> createFirEnumEntryDeserializedAccessExpression(
    enum: T
): FirEnumEntryDeserializedAccessExpression =
    createFirEnumEntryDeserializedAccessExpression(
        enumEntryName = enum.name.toName(),
        enumClassId = classId<T>(),
    )

public fun createFirEnumEntryDeserializedAccessExpression(
    enumEntryName: Name,
    enumClassId: ClassId,
): FirEnumEntryDeserializedAccessExpression = buildEnumEntryDeserializedAccessExpression {
    this.enumEntryName = enumEntryName
    this.enumClassId = enumClassId
}
