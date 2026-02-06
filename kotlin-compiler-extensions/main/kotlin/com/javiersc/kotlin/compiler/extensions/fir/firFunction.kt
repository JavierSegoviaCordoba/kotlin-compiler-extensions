@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.compiler.extensions.common.toCallableId
import com.javiersc.kotlin.compiler.extensions.common.toName
import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.descriptors.EffectiveVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.builder.buildNamedFunction
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.declarations.origin
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNothingTypeRef
import org.jetbrains.kotlin.name.StandardClassIds

context(session: FirSession)
public inline fun nothingFirFunction(key: GeneratedDeclarationKey): FirFunction =
    nothingFirFunction(key.origin)

context(session: FirSession)
public inline fun nothingFirFunction(origin: FirDeclarationOrigin): FirFunction =
    buildNamedFunction {
        this.moduleData = session.moduleData
        this.origin = origin
        this.status =
            FirResolvedDeclarationStatusImpl(
                visibility = Visibilities.Private,
                modality = Modality.FINAL,
                effectiveVisibility = EffectiveVisibility.PrivateInFile,
            )
        this.returnTypeRef = FirImplicitNothingTypeRef(null)
        this.name = "Nothing".toName()
        this.symbol = FirNamedFunctionSymbol(StandardClassIds.Nothing.toCallableId())
    }
