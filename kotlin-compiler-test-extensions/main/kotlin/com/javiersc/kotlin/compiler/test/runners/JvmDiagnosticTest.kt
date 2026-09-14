package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.AdditionalFilesProvider
import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import org.jetbrains.kotlin.cli.jvm.compiler.setupIdeaStandaloneExecution
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.configuration.baseFirDiagnosticTestConfiguration
import org.jetbrains.kotlin.test.directives.CodegenTestDirectives
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives
import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives
import org.jetbrains.kotlin.test.directives.configureFirParser
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.runners.AbstractKotlinCompilerTest
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider

public abstract class JvmDiagnosticTest : AbstractKotlinCompilerTest() {

    private companion object {
        init {
            // This runner reaches the IntelliJ core application environment through
            // FirFrontendFacade, which does not prepare it. The CLI facades that JvmBoxTest goes
            // through call this themselves, so both runners end up with the same properties.
            setupIdeaStandaloneExecution()
        }
    }

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public open val additionalFilesProvider: Constructor<AdditionalFilesProvider>? = null

    public open val parser: FirParser = FirParser.LightTree

    context(extensionStorage: ExtensionStorage)
    public abstract fun registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    override fun configure(builder: TestConfigurationBuilder) {
        with(builder) {
            baseFirDiagnosticTestConfiguration()
            configureFirParser(parser)
            configuration()
        }
    }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider =
        EnvironmentBasedStandardLibrariesPathProvider

    context(testConfigurationBuilder: TestConfigurationBuilder)
    private fun configuration() {
        testConfigurationBuilder.defaultDirectives {
            +FirDiagnosticsDirectives.FIR_DUMP
            +JvmEnvironmentConfigurationDirectives.FULL_JDK
            +CodegenTestDirectives.IGNORE_DEXING
        }

        diagnosticPluginConfiguration(
            classpathProvider = runtimeClasspathProvider,
            additionalFilesProvider = additionalFilesProvider,
            registerCompilerExtensions = { module, configuration ->
                registerExtensions(module, configuration)
            },
        )
    }
}
