package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef
import org.jetbrains.kotlin.fir.declarations.builder.buildTypeParameter
import org.jetbrains.kotlin.fir.declarations.origin
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.toRegularClassSymbol
import org.jetbrains.kotlin.fir.types.type
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.Variance

public fun ConeTypeProjection.toFirTypeParameter(
    session: FirSession,
    key: GeneratedDeclarationKey,
    containingDeclarationSymbol: FirBasedSymbol<*>,
    isReified: Boolean = false,
    variance: Variance = Variance.INVARIANT,
    name: Name = this.type?.toRegularClassSymbol(session)?.name ?: Name.special("<anonymous>")
): FirTypeParameterRef =
    toFirTypeParameter(session, key.origin, containingDeclarationSymbol, isReified, variance, name)

public fun ConeTypeProjection.toFirTypeParameter(
    session: FirSession,
    origin: FirDeclarationOrigin,
    containingDeclarationSymbol: FirBasedSymbol<*>,
    isReified: Boolean = false,
    variance: Variance = Variance.INVARIANT,
    name: Name = this.type?.toRegularClassSymbol(session)?.name ?: Name.special("<anonymous>")
): FirTypeParameterRef = buildTypeParameter {
    this.moduleData = session.moduleData
    this.origin = origin
    this.name = name
    this.symbol = FirTypeParameterSymbol()
    this.containingDeclarationSymbol = containingDeclarationSymbol
    this.isReified = isReified
    this.variance = variance
}
