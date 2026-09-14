package com.javiersc.kotlin.compiler.test.services

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.AbstractEnvironmentConfigurator
import org.jetbrains.kotlin.test.services.AdditionalSourceProvider
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices

internal fun TestConfigurationBuilder.configurePlugin(
    classpathProvider: Constructor<MetaRuntimeClasspathProvider>?,
    additionalFilesProvider: Constructor<AdditionalFilesProvider>?,
    registerCompilerExtensions: ExtensionStorage.(TestModule, CompilerConfiguration) -> Unit,
) {
    val configurators: List<Constructor<AbstractEnvironmentConfigurator>> = buildList {
        add { testServices: TestServices ->
            ExtensionRegistrarConfigurator(testServices, registerCompilerExtensions)
        }
        if (classpathProvider != null) {
            useCustomRuntimeClasspathProviders(classpathProvider)
            add { testServices: TestServices ->
                classpathProvider(testServices).classpathConfigurator
            }
        }
    }
    useConfigurators(*configurators.toTypedArray())

    val filesProvider: Constructor<AdditionalSourceProvider> = { testServices: TestServices ->
        additionalFilesProvider?.invoke(testServices) ?: AdditionalFilesProvider(testServices)
    }
    useAdditionalSourceProviders(filesProvider)
}

private class ExtensionRegistrarConfigurator(
    testServices: TestServices,
    private val registerCompilerExtensions:
        ExtensionStorage.(TestModule, CompilerConfiguration) -> Unit,
) : EnvironmentConfigurator(testServices) {

    override fun ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    ) {
        registerCompilerExtensions(this, module, configuration)
    }
}
