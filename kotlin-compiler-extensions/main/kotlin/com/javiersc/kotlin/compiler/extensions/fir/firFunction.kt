package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.compiler.extensions.common.toCallableId
import com.javiersc.kotlin.compiler.extensions.common.toName
import org.jetbrains.kotlin.descriptors.EffectiveVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirFunction
import org.jetbrains.kotlin.fir.declarations.builder.buildSimpleFunction
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.types.impl.FirImplicitNothingTypeRef
import org.jetbrains.kotlin.name.StandardClassIds

public fun FirSession.nothingFirFunction(origin: FirDeclarationOrigin): FirFunction =
    buildSimpleFunction {
        val session = this@nothingFirFunction
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
