package com.javiersc.kotlin.compiler.test.services

import com.javiersc.kotlin.stdlib.AnsiColor
import com.javiersc.kotlin.stdlib.ansiColor
import java.io.File
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoot
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.RuntimeClasspathProvider
import org.jetbrains.kotlin.test.services.TestServices

public abstract class MetaRuntimeClasspathProvider(
    testServices: TestServices,
    private val isLoggingEnabled: Boolean = false,
) : RuntimeClasspathProvider(testServices) {

    public abstract val jarPaths: List<String>

    public val classpathConfigurator: ClasspathConfigurator
        get() =
            ClasspathConfigurator(
                jarPaths = jarPaths,
                testServices = testServices,
                isLoggingEnabled = isLoggingEnabled,
            )

    override fun runtimeClassPaths(module: TestModule): List<File> = classpathConfigurator.jarFiles
}

public class ClasspathConfigurator(
    public val jarPaths: List<String>,
    testServices: TestServices,
    private val isLoggingEnabled: Boolean,
) : EnvironmentConfigurator(testServices) {

    public val jarFiles: List<File>
        get() = jarPaths.map(::File)

    override fun configureCompilerConfiguration(
        configuration: CompilerConfiguration,
        module: TestModule,
    ) {
        for (jar: File in jarFiles) {
            if (isLoggingEnabled) println("Jar found: $jar".ansiColor(AnsiColor.Foreground.Cyan))
            configuration.addJvmClasspathRoot(jar)
        }
    }
}
