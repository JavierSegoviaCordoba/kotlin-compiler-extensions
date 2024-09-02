package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.toClassLikeSymbol
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.expressions.buildResolvedArgumentList
import org.jetbrains.kotlin.fir.expressions.builder.buildFunctionCall
import org.jetbrains.kotlin.fir.references.builder.buildResolvedNamedReference

public inline val FirSession.nothingFirFunctionCall: FirFunctionCall
    get() = buildFunctionCall {
        val nothingSymbol =
            builtinTypes.nothingType.toClassLikeSymbol(this@nothingFirFunctionCall)!!
        argumentList = buildResolvedArgumentList(null, LinkedHashMap())
        calleeReference = buildResolvedNamedReference {
            this.name = nothingSymbol.name
            resolvedSymbol = nothingSymbol
        }
    }
