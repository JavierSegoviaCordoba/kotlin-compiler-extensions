package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.FirModuleData
import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.declarations.builder.buildValueParameter
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol
import org.jetbrains.kotlin.name.Name

public fun FirContextReceiver.toValueParameter(
    moduleData: FirModuleData,
    origin: FirDeclarationOrigin,
    containingFunctionSymbol: FirFunctionSymbol<*>,
    name: Name = typeRefName ?: Name.special("<anonymous>"),
    defaultValue: FirExpression? = null,
): FirValueParameter = buildValueParameter {
    val contextReceiver = this@toValueParameter
    this.moduleData = moduleData
    this.origin = origin
    this.returnTypeRef = contextReceiver.typeRef
    this.name = name
    this.symbol = FirValueParameterSymbol(name)
    this.containingFunctionSymbol = containingFunctionSymbol
    this.defaultValue = defaultValue
    this.isCrossinline = false
    this.isNoinline = false
    this.isVararg = false
}
