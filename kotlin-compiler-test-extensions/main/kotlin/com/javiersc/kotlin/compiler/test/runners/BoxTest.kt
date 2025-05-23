package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.AdditionalFilesProvider
import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.TargetBackend
import org.jetbrains.kotlin.test.backend.BlackBoxCodegenSuppressor
import org.jetbrains.kotlin.test.backend.handlers.IrTextDumpHandler
import org.jetbrains.kotlin.test.backend.handlers.IrTreeVerifierHandler
import org.jetbrains.kotlin.test.backend.handlers.JvmBoxRunner
import org.jetbrains.kotlin.test.backend.ir.JvmIrBackendFacade
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.builders.fir2IrStep
import org.jetbrains.kotlin.test.builders.irHandlersStep
import org.jetbrains.kotlin.test.builders.jvmArtifactsHandlersStep
import org.jetbrains.kotlin.test.configuration.configureDumpHandlersForCodegenTest
import org.jetbrains.kotlin.test.directives.CodegenTestDirectives.DUMP_IR
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.runners.RunnerWithTargetBackendForTestGeneratorMarker

public abstract class BoxTest : BaseTestRunner(), RunnerWithTargetBackendForTestGeneratorMarker {

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public open val additionalFilesProvider: Constructor<AdditionalFilesProvider>? = null

    override val targetBackend: TargetBackend = TargetBackend.JVM_IR

    public abstract fun ExtensionStorage.registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    override fun configure(builder: TestConfigurationBuilder): Unit =
        with(builder) { configuration() }

    private fun TestConfigurationBuilder.configuration() {
        defaultDirectives { //
            +DUMP_IR
        }

        commonPluginConfiguration(
            classpathProvider = runtimeClasspathProvider,
            additionalFilesProvider = additionalFilesProvider,
            registerCompilerExtensions = { module, configuration ->
                registerExtensions(module, configuration)
            },
        )
        fir2IrStep()
        irHandlersStep { useHandlers(::IrTextDumpHandler, ::IrTreeVerifierHandler) }
        facadeStep(::JvmIrBackendFacade)
        jvmArtifactsHandlersStep { useHandlers(::JvmBoxRunner) }

        useAfterAnalysisCheckers(::BlackBoxCodegenSuppressor)
        configureDumpHandlersForCodegenTest()
    }
}
