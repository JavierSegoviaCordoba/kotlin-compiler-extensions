@file:OptIn(ExperimentalCompilerApi::class)

package com.javiersc.kotlin.compiler.extensions

import com.javiersc.kotlin.compiler.test.generateKotlinCompilerTests
import com.javiersc.kotlin.compiler.test.runners.BoxTest
import com.javiersc.kotlin.compiler.test.runners.DiagnosticTest
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.model.TestModule

fun main() {
    generateKotlinCompilerTests<AbstractFakeDiagnosticTest, AbstractFakeBoxTest>()
}

private fun ExtensionStorage.allExtensions(
    module: TestModule,
    configuration: CompilerConfiguration
) {
    IrGenerationExtension.registerExtension(FakeIrExtension())
}

open class AbstractFakeDiagnosticTest : DiagnosticTest() {

    override fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        allExtensions(module, configuration)
    }
}

open class AbstractFakeBoxTest : BoxTest() {

    override fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        allExtensions(module, configuration)
    }
}
