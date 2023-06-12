package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.toClassLikeSymbol
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.expressions.buildResolvedArgumentList
import org.jetbrains.kotlin.fir.expressions.builder.buildFunctionCall
import org.jetbrains.kotlin.fir.references.builder.buildResolvedNamedReference
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol

public val FirSession.nothingFirFunctionCall: FirFunctionCall
    get() = buildFunctionCall {
        val session = this@nothingFirFunctionCall
        val nothingSymbol: FirClassLikeSymbol<*> =
            checkNotNull(builtinTypes.nothingType.toClassLikeSymbol(session))
        this.argumentList = buildResolvedArgumentList(LinkedHashMap())
        this.typeRef = builtinTypes.nothingType
        this.calleeReference = buildResolvedNamedReference {
            this.name = nothingSymbol.name
            this.resolvedSymbol = nothingSymbol
        }
    }
