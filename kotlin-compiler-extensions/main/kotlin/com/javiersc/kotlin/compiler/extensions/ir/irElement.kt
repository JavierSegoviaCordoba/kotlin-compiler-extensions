package com.javiersc.kotlin.compiler.extensions.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.dumpKotlinLike

public fun Iterable<IrElement>.dump(): String = joinToString("\n") { it.dump() }

public fun Iterable<IrElement>.dumpKotlinLike(): String = joinToString("\n") { it.dumpKotlinLike() }
