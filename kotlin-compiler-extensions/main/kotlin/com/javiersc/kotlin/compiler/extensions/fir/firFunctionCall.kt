package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.toClassLikeSymbol
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.expressions.buildResolvedArgumentList
import org.jetbrains.kotlin.fir.expressions.builder.buildFunctionCall
import org.jetbrains.kotlin.fir.references.builder.buildResolvedNamedReference
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol

context(session: FirSession)
public inline val nothingFirFunctionCall: FirFunctionCall
    get() = buildFunctionCall {
        val nothingSymbol: FirClassLikeSymbol<*> =
            session.builtinTypes.nothingType.toClassLikeSymbol(session)!!
        argumentList = buildResolvedArgumentList(null, LinkedHashMap())
        calleeReference = buildResolvedNamedReference {
            this.name = nothingSymbol.name
            resolvedSymbol = nothingSymbol
        }
    }
