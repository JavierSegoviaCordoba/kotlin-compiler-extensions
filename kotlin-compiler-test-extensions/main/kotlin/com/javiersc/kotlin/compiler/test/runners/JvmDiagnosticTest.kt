package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.AdditionalFilesDirectives
import com.javiersc.kotlin.compiler.test.services.AdditionalFilesProvider
import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import com.javiersc.kotlin.compiler.test.services.configurePlugin
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.CodegenTestDirectives
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives
import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives
import org.jetbrains.kotlin.test.directives.TestPhaseDirectives
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.runners.AbstractFirPhasedDiagnosticTest
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.TestPhase

public abstract class JvmDiagnosticTest : AbstractFirPhasedDiagnosticTest(FirParser.LightTree) {

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public open val additionalFilesProvider: Constructor<AdditionalFilesProvider>? = null

    context(extensionStorage: ExtensionStorage)
    public abstract fun registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    override fun configure(builder: TestConfigurationBuilder): Unit =
        with(builder) {
            super.configure(builder)

            defaultDirectives {
                +FirDiagnosticsDirectives.FIR_DUMP
                +JvmEnvironmentConfigurationDirectives.FULL_JDK
                +FirDiagnosticsDirectives.DISABLE_GENERATED_FIR_TAGS
                +CodegenTestDirectives.IGNORE_DEXING
                +AdditionalFilesDirectives.SOME_FILE_DIRECTIVE
                TestPhaseDirectives.RUN_PIPELINE_TILL with TestPhase.FRONTEND
            }

            configurePlugin(
                classpathProvider = runtimeClasspathProvider,
                additionalFilesProvider = additionalFilesProvider,
                registerCompilerExtensions = { module, configuration ->
                    registerExtensions(module, configuration)
                },
            )
        }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider =
        EnvironmentBasedStandardLibrariesPathProvider
}
