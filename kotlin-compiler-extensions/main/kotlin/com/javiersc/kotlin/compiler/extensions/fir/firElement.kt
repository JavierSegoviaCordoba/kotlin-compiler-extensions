package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.render

public fun List<FirElement?>.render(): String = buildString {
    for (firElement in this@render) appendLine(firElement?.render())
}
