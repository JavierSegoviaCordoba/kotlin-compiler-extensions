package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.stdlib.transform_string
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.util.isAnnotationClass
import org.jetbrains.kotlin.ir.util.isClass
import org.jetbrains.kotlin.ir.util.isEnumClass
import org.jetbrains.kotlin.ir.util.isEnumEntry
import org.jetbrains.kotlin.ir.util.isInterface
import org.jetbrains.kotlin.ir.util.isObject

/** Exhaustive list of all possible [IrClass] kinds to allow pretty printing */
public enum class IrClassExhaustiveKind {
    Class,
    Interface,
    EnumClass,
    EnumEntry,
    AnnotationClass,
    Object,
    CompanionObject,
    InnerClass,
    DataClass,
    DataObject,
    ValueClass,
    ExpectClass,
    ExpectInterface,
    ExpectAnnotationClass,
    FunInterface;

    override fun toString(): String = name.transform_string().replace("_", " ")

    public companion object {

        public fun from(irClass: IrClass): IrClassExhaustiveKind =
            with(irClass) {
                when {
                    isExpect && isAnnotationClass -> ExpectAnnotationClass
                    isExpect && isInterface -> ExpectInterface
                    isExpect && isClass -> ExpectClass
                    isFun && isInterface -> FunInterface
                    isInner && isClass -> InnerClass
                    isValue && isClass -> ValueClass
                    isData && isObject -> DataObject
                    isData && isClass -> DataClass
                    isObject && isCompanion -> CompanionObject
                    isAnnotationClass -> AnnotationClass
                    isInterface -> Interface
                    isEnumEntry -> EnumEntry
                    isEnumClass -> EnumClass
                    isObject -> Object
                    isClass -> Class
                    else -> Class
                }
            }
    }
}
