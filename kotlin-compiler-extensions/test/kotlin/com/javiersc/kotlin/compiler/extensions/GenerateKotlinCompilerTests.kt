@file:OptIn(ExperimentalCompilerApi::class)

package com.javiersc.kotlin.compiler.extensions

import com.javiersc.kotlin.compiler.extensions.fir.FakeFirExtensionRegistrar
import com.javiersc.kotlin.compiler.extensions.ir.FakeIrExtension
import com.javiersc.kotlin.compiler.extensions.shared.compilerExtensionsTestDir
import com.javiersc.kotlin.compiler.test.generateKotlinCompilerTests
import com.javiersc.kotlin.compiler.test.runners.JvmBoxTest
import com.javiersc.kotlin.compiler.test.runners.JvmDiagnosticTest
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule

fun main() {
    generateKotlinCompilerTests<AbstractFakeDiagnosticTest, AbstractFakeBoxTest>()
}

private fun ExtensionStorage.allExtensions() {
    compilerExtensionsTestDir.deleteRecursively()
    FirExtensionRegistrarAdapter.registerExtension(FakeFirExtensionRegistrar())
    IrGenerationExtension.registerExtension(FakeIrExtension())
}

open class AbstractFakeDiagnosticTest : JvmDiagnosticTest() {

    override fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    ) {
        allExtensions()
    }
}

open class AbstractFakeBoxTest : JvmBoxTest() {

    override fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    ) {
        allExtensions()
    }
}
