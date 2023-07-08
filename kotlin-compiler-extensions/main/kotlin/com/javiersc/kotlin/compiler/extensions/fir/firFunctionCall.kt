package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.toClassLikeSymbol
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.expressions.buildResolvedArgumentList
import org.jetbrains.kotlin.fir.expressions.builder.buildFunctionCall
import org.jetbrains.kotlin.fir.references.builder.buildResolvedNamedReference

public val FirSession.nothingFirFunctionCall: FirFunctionCall
    get() = buildFunctionCall {
        val nothingSymbol =
            builtinTypes.nothingType.toClassLikeSymbol(this@nothingFirFunctionCall)!!
        typeRef = builtinTypes.nothingType
        argumentList = buildResolvedArgumentList(LinkedHashMap())
        calleeReference = buildResolvedNamedReference {
            this.name = nothingSymbol.name
            resolvedSymbol = nothingSymbol
        }
    }
