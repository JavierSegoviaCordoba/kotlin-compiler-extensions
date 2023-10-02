package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol

public inline fun <reified O : FirElement> FirElement.asFir(): O = this@asFir as O

public inline fun <reified O : FirElement> FirElement.asFirOrNull(): O? = this@asFirOrNull as? O

public inline fun <reified O : FirBasedSymbol<*>> FirBasedSymbol<*>.asFirSymbol(): O =
    this@asFirSymbol as O

public inline fun <reified O : FirBasedSymbol<*>> FirBasedSymbol<*>.asFirSymbolOrNull(): O? =
    this@asFirSymbolOrNull as? O
