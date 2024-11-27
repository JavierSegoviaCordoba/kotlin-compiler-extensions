package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.builder.buildAnonymousFunction
import org.jetbrains.kotlin.fir.declarations.origin
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousFunctionExpressionBuilder
import org.jetbrains.kotlin.fir.expressions.builder.buildAnonymousFunctionExpression
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRef
import org.jetbrains.kotlin.fir.types.coneType

public inline fun createFirAnonymousFunctionExpression(
    anonymousFunction: FirAnonymousFunction,
    builder: FirAnonymousFunctionExpressionBuilder.() -> Unit = {},
): FirAnonymousFunctionExpression = buildAnonymousFunctionExpression {
    this.anonymousFunction = anonymousFunction
    builder()
}

public inline fun FirSession.nothingFirAnonymousFunctionExpression(
    key: GeneratedDeclarationKey
): FirAnonymousFunctionExpression = nothingFirAnonymousFunctionExpression(origin = key.origin)

public inline fun FirSession.nothingFirAnonymousFunctionExpression(
    origin: FirDeclarationOrigin
): FirAnonymousFunctionExpression =
    createFirAnonymousFunctionExpression(
        anonymousFunction =
            buildAnonymousFunction {
                val session: FirSession = this@nothingFirAnonymousFunctionExpression
                this.moduleData = session.moduleData
                this.origin = origin
                this.symbol = FirAnonymousFunctionSymbol()
                this.isLambda = false
                this.hasExplicitParameterList = false
                this.returnTypeRef = buildResolvedTypeRef {
                    this.coneType = builtinTypes.nothingType.coneType
                }
            }
    )
