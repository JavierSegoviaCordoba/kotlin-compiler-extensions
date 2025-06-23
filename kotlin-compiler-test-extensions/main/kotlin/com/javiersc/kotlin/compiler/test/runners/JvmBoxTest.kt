package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.AdditionalFilesProvider
import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.backend.BlackBoxCodegenSuppressor
import org.jetbrains.kotlin.test.backend.handlers.IrTextDumpHandler
import org.jetbrains.kotlin.test.backend.handlers.IrTreeVerifierHandler
import org.jetbrains.kotlin.test.backend.handlers.JvmBoxRunner
import org.jetbrains.kotlin.test.backend.ir.JvmIrBackendFacade
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.builders.irHandlersStep
import org.jetbrains.kotlin.test.builders.jvmArtifactsHandlersStep
import org.jetbrains.kotlin.test.configuration.configureDumpHandlersForCodegenTest
import org.jetbrains.kotlin.test.directives.CodegenTestDirectives
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives
import org.jetbrains.kotlin.test.directives.JvmEnvironmentConfigurationDirectives
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.runners.codegen.AbstractFirBlackBoxCodegenTestBase
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider

public abstract class JvmBoxTest : AbstractFirBlackBoxCodegenTestBase(FirParser.LightTree) {

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public open val additionalFilesProvider: Constructor<AdditionalFilesProvider>? = null

    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)

        with(builder) { configuration() }
    }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider =
        EnvironmentBasedStandardLibrariesPathProvider

    public abstract fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    private fun TestConfigurationBuilder.configuration() {
        defaultDirectives { //
            +CodegenTestDirectives.DUMP_IR
            +FirDiagnosticsDirectives.FIR_DUMP
            +JvmEnvironmentConfigurationDirectives.FULL_JDK
            +CodegenTestDirectives.IGNORE_DEXING
        }

        commonPluginConfiguration(
            classpathProvider = runtimeClasspathProvider,
            additionalFilesProvider = additionalFilesProvider,
            registerCompilerExtensions = { module, configuration ->
                registerExtensions(module, configuration)
            },
        )
        irHandlersStep { useHandlers(::IrTextDumpHandler, ::IrTreeVerifierHandler) }
        facadeStep(::JvmIrBackendFacade)
        jvmArtifactsHandlersStep { useHandlers(::JvmBoxRunner) }

        useAfterAnalysisCheckers(::BlackBoxCodegenSuppressor)
        configureDumpHandlersForCodegenTest()
    }
}
