package com.javiersc.kotlin.compiler.test.runners

import com.javiersc.kotlin.compiler.test.services.AdditionalFilesDirectives
import com.javiersc.kotlin.compiler.test.services.AdditionalFilesProvider
import com.javiersc.kotlin.compiler.test.services.ExtensionRegistrarConfigurator
import com.javiersc.kotlin.compiler.test.services.MetaRuntimeClasspathProvider
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar.ExtensionStorage
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.FirParser
import org.jetbrains.kotlin.test.TargetBackend
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives
import org.jetbrains.kotlin.test.directives.configureFirParser
import org.jetbrains.kotlin.test.initIdeaConfiguration
import org.jetbrains.kotlin.test.model.DependencyKind
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.runners.AbstractKotlinCompilerTest
import org.jetbrains.kotlin.test.runners.baseFirDiagnosticTestConfiguration
import org.jetbrains.kotlin.test.services.AbstractEnvironmentConfigurator
import org.jetbrains.kotlin.test.services.EnvironmentBasedStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.KotlinStandardLibrariesPathProvider
import org.jetbrains.kotlin.test.services.configuration.CommonEnvironmentConfigurator
import org.jetbrains.kotlin.test.services.configuration.JvmEnvironmentConfigurator
import org.junit.jupiter.api.BeforeAll

public abstract class BaseTestRunner : AbstractKotlinCompilerTest() {

    public companion object {

        @BeforeAll
        @JvmStatic
        public fun setUp() {
            initIdeaConfiguration()
        }
    }

    override fun createKotlinStandardLibrariesPathProvider(): KotlinStandardLibrariesPathProvider {
        return EnvironmentBasedStandardLibrariesPathProvider
    }
}

internal fun TestConfigurationBuilder.commonPluginConfiguration(
    classpathProvider: Constructor<MetaRuntimeClasspathProvider>?,
    registerCompilerExtensions: ExtensionStorage.(TestModule, CompilerConfiguration) -> Unit
) {
    globalDefaults {
        targetBackend = TargetBackend.JVM_IR
        targetPlatform = JvmPlatforms.defaultJvmPlatform
        dependencyKind = DependencyKind.Binary
    }

    baseFirDiagnosticTestConfiguration()

    configureFirParser(FirParser.Psi)

    defaultDirectives {
        //        +ConfigurationDirectives.WITH_STDLIB
        +FirDiagnosticsDirectives.ENABLE_PLUGIN_PHASES
        +FirDiagnosticsDirectives.FIR_DUMP
        +AdditionalFilesDirectives.SOME_FILE_DIRECTIVE
    }

    val configurators: List<Constructor<AbstractEnvironmentConfigurator>> = buildList {
        if (classpathProvider != null) {
            add { classpathProvider(it).classpathConfigurator }
        }
        add { ExtensionRegistrarConfigurator(it, registerCompilerExtensions) }

        add(::CommonEnvironmentConfigurator)
        add(::JvmEnvironmentConfigurator)
    }

    if (classpathProvider != null) {
        useCustomRuntimeClasspathProviders(classpathProvider)
    }
    useConfigurators(*configurators.toTypedArray())
    useAdditionalSourceProviders(::AdditionalFilesProvider)
}
