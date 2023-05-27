package com.javiersc.kotlin.compiler.test.runners

// import org.jetbrains.kotlin.test.FirParser
// import org.jetbrains.kotlin.test.directives.configureFirParser
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider

public abstract class DiagnosticTest : BaseTestRunner() {

    public val shouldUseAnnotations: Boolean = false

    public abstract fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    override fun TestConfigurationBuilder.configuration() {
        commonFirWithPluginFrontendConfiguration(shouldUseAnnotations) { module, configuration ->
            registerExtensions(module, configuration)
        }
        //        configureFirParser(FirParser.Psi)
    }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider {
        return EnvironmentBasedStandardLibrariesPathProvider
    }
}
