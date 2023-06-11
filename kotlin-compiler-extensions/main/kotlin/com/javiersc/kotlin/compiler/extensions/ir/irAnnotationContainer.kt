package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.compiler.extensions.common.fqName
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer
import org.jetbrains.kotlin.ir.util.hasAnnotation

public inline fun <reified T : Annotation> IrAnnotationContainer.hasAnnotation(): Boolean =
    annotations.hasAnnotation(fqName<T>())
