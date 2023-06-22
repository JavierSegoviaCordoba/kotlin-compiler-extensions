package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.containingClassForStaticMemberAttr
import org.jetbrains.kotlin.fir.declarations.FirConstructor
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder
import org.jetbrains.kotlin.fir.declarations.builder.buildPrimaryConstructor
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl.Companion.DEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol

public fun FirClassSymbol<*>.toPrimaryConstructor(
    session: FirSession,
    origin: FirDeclarationOrigin,
    block: FirPrimaryConstructorBuilder.(FirClassSymbol<*>) -> Unit = {},
): FirConstructor {
    val classSymbol = this@toPrimaryConstructor
    return buildPrimaryConstructor {
            this.resolvePhase = FirResolvePhase.BODY_RESOLVE
            this.moduleData = session.moduleData
            this.origin = origin
            this.returnTypeRef = classSymbol.classId.toFirTypeRef()
            this.status = DEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS
            this.symbol = FirConstructorSymbol(classSymbol.classId)
            block(this, classSymbol)
        }
        .apply { this.containingClassForStaticMemberAttr = ConeClassLikeLookupTagImpl(classSymbol.classId) }
}
