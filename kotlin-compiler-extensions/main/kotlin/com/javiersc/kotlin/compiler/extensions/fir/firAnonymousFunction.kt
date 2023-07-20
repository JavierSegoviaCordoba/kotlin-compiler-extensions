package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousFunctionBuilder
import org.jetbrains.kotlin.fir.declarations.builder.buildAnonymousFunction
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol
import org.jetbrains.kotlin.fir.types.FirTypeRef

public fun FirSession.createFirAnonymousFunction(
    origin: FirDeclarationOrigin,
    returnTypeRef: FirTypeRef,
    symbol: FirAnonymousFunctionSymbol = FirAnonymousFunctionSymbol(),
    isLambda: Boolean = false,
    hasExplicitParameterList: Boolean = false,
    builder: FirAnonymousFunctionBuilder.() -> Unit = {},
): FirAnonymousFunction {
    val session: FirSession = this@createFirAnonymousFunction
    return buildAnonymousFunction {
        this.moduleData = session.moduleData
        this.origin = origin
        this.symbol = symbol
        this.isLambda = isLambda
        this.hasExplicitParameterList = hasExplicitParameterList
        this.returnTypeRef = returnTypeRef
        builder()
    }
}
