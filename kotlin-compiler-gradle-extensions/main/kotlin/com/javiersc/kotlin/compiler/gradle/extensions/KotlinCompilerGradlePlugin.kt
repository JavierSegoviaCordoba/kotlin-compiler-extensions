package com.javiersc.kotlin.compiler.gradle.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.exclude
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin

public interface KotlinCompilerGradlePlugin : KotlinCompilerPluginSupportPlugin {

    override fun apply(target: Project) {
        super.apply(target)
        val compilerClasspath: Configuration = target.createCompilerClasspath()
        target.configureFreeCompilerArgs(compilerClasspath)
    }

    public fun DependencyHandlerScope.compilerClasspath(dependency: Any) {
        "compilerClasspath"(dependency.dependencyToString())
    }

    public fun Project.configureFreeCompilerArgs(compilerClasspath: Configuration) {
        pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
            configure<KotlinMultiplatformExtension> {
                val xpluginArg: Provider<String> = provider {
                    val pathDependencies: String =
                        compilerClasspath.asPath.replace(";", ",").replace(":", ",")
                    "-Xplugin=$pathDependencies"
                }
                compilerOptions.freeCompilerArgs.add(xpluginArg)
            }
        }
    }

    private fun Project.createCompilerClasspath(): Configuration =
        configurations.create("compilerClasspath").apply {
            exclude("org.jetbrains")
            exclude("org.jetbrains.kotlin")
            exclude("org.jetbrains.kotlinx")
        }

    private fun Any.dependencyToString(): String =
        when (val dep = this) {
            is Provider<*> -> dep.get().dependencyToString()
            is Dependency -> "${dep.group}:${dep.name}:${dep.version}"
            is String -> dep
            else -> error("The type of $dep is not supported: ${dep::class}")
        }
}
