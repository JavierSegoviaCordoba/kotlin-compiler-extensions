package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class FakeFirExtensionRegistrar : FirExtensionRegistrar() {

    override fun ExtensionRegistrarContext.configurePlugin() {
        +::FakeFirDeclarationGeneration
    }
}
