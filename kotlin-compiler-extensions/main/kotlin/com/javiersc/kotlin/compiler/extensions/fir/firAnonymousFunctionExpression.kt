@file:Suppress("NOTHING_TO_INLINE")

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

public inline fun createFirAnonymousFunctionExpression(
    anonymousFunction: FirAnonymousFunction,
    builder: FirAnonymousFunctionExpressionBuilder.() -> Unit = {},
): FirAnonymousFunctionExpression = buildAnonymousFunctionExpression {
    this.anonymousFunction = anonymousFunction
    builder()
}

context(session: FirSession)
public inline fun nothingFirAnonymousFunctionExpression(
    key: GeneratedDeclarationKey
): FirAnonymousFunctionExpression = nothingFirAnonymousFunctionExpression(origin = key.origin)

context(session: FirSession)
public inline fun nothingFirAnonymousFunctionExpression(
    origin: FirDeclarationOrigin
): FirAnonymousFunctionExpression =
    createFirAnonymousFunctionExpression(
        anonymousFunction =
            buildAnonymousFunction {
                this.moduleData = session.moduleData
                this.origin = origin
                this.symbol = FirAnonymousFunctionSymbol()
                this.isLambda = false
                this.hasExplicitParameterList = false
                this.returnTypeRef = buildResolvedTypeRef {
                    this.coneType = session.builtinTypes.nothingType.coneType
                }
            },
    )
