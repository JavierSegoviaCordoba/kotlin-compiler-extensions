package com.javiersc.kotlin.compiler.test.services

import java.io.File
import java.io.FilenameFilter
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoot
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.RuntimeClasspathProvider
import org.jetbrains.kotlin.test.services.TestServices
import org.jetbrains.kotlin.test.services.assertions

internal class PluginAnnotationsConfigurator(
    testServices: TestServices,
) : EnvironmentConfigurator(testServices) {

    companion object {

        private const val ANNOTATIONS_JAR_DIR = "../annotations/build/libs/"

        private val ANNOTATIONS_JAR_FILTER = FilenameFilter { _, name ->
            name.startsWith("annotations") &&
                name.endsWith(".jar") &&
                !name.endsWith("-javadoc.jar") &&
                !name.endsWith("-sources.jar")
        }
        val libDir: File
            get() = File(ANNOTATIONS_JAR_DIR)

        private val failMessage = {
            "Jar with annotations does not exist. Please run :annotations:jar"
        }

        fun jar(testServices: TestServices) =
            libDir.listFiles(ANNOTATIONS_JAR_FILTER)?.firstOrNull()
                ?: testServices.assertions.fail(failMessage)
    }

    override fun configureCompilerConfiguration(
        configuration: CompilerConfiguration,
        module: TestModule
    ) {

        testServices.assertions.assertTrue(libDir.exists() && libDir.isDirectory, failMessage)
        val jar = jar(testServices)
        println("found jar: $jar")
        configuration.addJvmClasspathRoot(jar)
    }
}

internal class MetaRuntimeClasspathProvider(
    testServices: TestServices,
) : RuntimeClasspathProvider(testServices) {

    override fun runtimeClassPaths(module: TestModule): List<File> {
        return listOf(PluginAnnotationsConfigurator.jar(testServices))
    }
}
