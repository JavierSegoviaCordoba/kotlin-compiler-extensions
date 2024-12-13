package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.AdditionalFilesProvider
import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider

public abstract class DiagnosticTest : BaseTestRunner() {

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public open val additionalFilesProvider: Constructor<AdditionalFilesProvider>? = null

    public abstract fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    override fun configure(builder: TestConfigurationBuilder): Unit =
        with(builder) { configuration() }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider {
        return EnvironmentBasedStandardLibrariesPathProvider
    }

    private fun TestConfigurationBuilder.configuration() {
        commonPluginConfiguration(
            classpathProvider = runtimeClasspathProvider,
            additionalFilesProvider = additionalFilesProvider,
            registerCompilerExtensions = { module, configuration ->
                registerExtensions(module, configuration)
            },
        )
    }
}
