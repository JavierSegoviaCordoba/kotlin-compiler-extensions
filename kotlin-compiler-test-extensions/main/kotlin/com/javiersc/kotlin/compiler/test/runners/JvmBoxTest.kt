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
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.runners.codegen.AbstractJvmBlackBoxCodegenTestBase
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider

public abstract class JvmBoxTest : AbstractJvmBlackBoxCodegenTestBase(FirParser.LightTree) {

    public open val runtimeClasspathProvider: Constructor<MetaRuntimeClasspathProvider>? = null

    public open val additionalFilesProvider: Constructor<AdditionalFilesProvider>? = null

    override fun configure(builder: TestConfigurationBuilder) {
        super.configure(builder)

        with(builder) { configuration() }
    }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider =
        EnvironmentBasedStandardLibrariesPathProvider

    context(extensionStorage: ExtensionStorage)
    public abstract fun registerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration,
    )

    context(testConfigurationBuilder: TestConfigurationBuilder)
    private fun configuration(): Unit =
        with(testConfigurationBuilder) {
            defaultDirectives {
                +CodegenTestDirectives.DUMP_IR
                +FirDiagnosticsDirectives.FIR_DUMP
                +JvmEnvironmentConfigurationDirectives.FULL_JDK
                +CodegenTestDirectives.IGNORE_DEXING
                +AdditionalFilesDirectives.SOME_FILE_DIRECTIVE
            }

            configurePlugin(
                classpathProvider = runtimeClasspathProvider,
                additionalFilesProvider = additionalFilesProvider,
                registerCompilerExtensions = { module, configuration ->
                    registerExtensions(module, configuration)
                },
            )
        }
}
