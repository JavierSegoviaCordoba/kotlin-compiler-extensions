package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.model.FrontendKind
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider

public abstract class DiagnosticTest : BaseTestRunner() {

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public abstract fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    public open fun commonServicesConfiguration(): ((FrontendKind<*>) -> Unit)? = null

    override fun TestConfigurationBuilder.configuration() {
        commonPluginConfiguration(
            classpathProvider = runtimeClasspathProvider,
            registerCompilerExtensions = { module, configuration ->
                registerExtensions(module, configuration)
            },
            commonServicesConfiguration = commonServicesConfiguration(),
        )
    }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider {
        return EnvironmentBasedStandardLibrariesPathProvider
    }
}
