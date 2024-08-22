package com.javiersc.kotlin.compiler.test.services

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices

internal class ExtensionRegistrarConfigurator(
    testServices: TestServices,
    private val registerCompilerExtensions:
        ExtensionStorage.(TestModule, CompilerConfiguration) -> Unit,
) : EnvironmentConfigurator(testServices) {

    override fun ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    ) {
        this@ExtensionRegistrarConfigurator.registerCompilerExtensions(this, module, configuration)
    }
}
