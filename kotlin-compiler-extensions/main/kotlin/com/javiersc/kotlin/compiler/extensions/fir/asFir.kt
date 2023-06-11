package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirElement

public inline fun <reified I : FirElement> FirElement?.asFir(): I? = this@asFir as? I?
