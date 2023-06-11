package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol

public inline fun <reified I : FirElement> FirElement?.asFir(): I? = this@asFir as? I?

public inline fun <reified I : FirBasedSymbol<*>> FirBasedSymbol<*>?.asFirSymbol(): I? =
    this@asFirSymbol as? I?
