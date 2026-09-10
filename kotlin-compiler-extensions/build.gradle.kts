import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.gradle.internal.config.LanguageFeature

hubdle {
    config {
        analysis()
        coverage()
        documentation { //
            api()
        }
        explicitApi()
        languageSettings { //
            enableLanguageFeatures(LanguageFeature.ContextParameters)
            experimentalContracts()
        }
        publishing()
        projectConfig()
    }

    kotlin {
        jvm {
            features {
                compiler {
                    mainClass.set(
                        "com.javiersc.kotlin.compiler.extensions.GenerateKotlinCompilerTestsKt"
                    )
                    addExtensionDependencies(false)
                    generateTestOnSync(false)
                    testDependencies(hubdle.javiersc.kotlin.stdlib)
                }
            }

            main { //
                dependencies { //
                    api(hubdle.jetbrains.kotlin.compiler)
                }
            }

            test {
                dependencies {
                    compileOnly(hubdle.jetbrains.kotlin.compiler.internal.test.framework)
                    implementation(projects.kotlinCompilerTestExtensions)
                }
            }
        }
    }
}

// Kotlin 2.4.20 needs the kotlin-reflect jar to run generated box test code.
// EnvironmentBasedStandardLibrariesPathProvider reads the path from this system property.
val kotlinReflectForTests: NamedDomainObjectProvider<out Configuration> =
    configurations.resolvable("kotlinReflectForTests") {
        isTransitive = false
        dependencies.addLater(hubdle.jetbrains.kotlin.reflect)
    }

tasks.withType<Test>().configureEach {
    val reflectJar: Provider<String> =
        kotlinReflectForTests.flatMap { it.elements }.map { it.single().asFile.absolutePath }
    jvmArgumentProviders.add(
        CommandLineArgumentProvider {
            listOf("-Dorg.jetbrains.kotlin.test.kotlin-reflect=${reflectJar.get()}")
        }
    )
}

val compilerExtensionTestsDir: Provider<Directory> =
    layout.buildDirectory.dir("compiler-extensions-tests")
val firTxtFile: Provider<RegularFile> = compilerExtensionTestsDir.map { it.file("fir.txt") }
val irTxtFile = compilerExtensionTestsDir.map { it.file("ir.txt") }

val checkCompilerExtensionsAreCalled = tasks.register("checkCompilerExtensionsAreCalled")

checkCompilerExtensionsAreCalled.configure {
    inputs.files(compilerExtensionTestsDir, firTxtFile, irTxtFile)
    outputs.files(firTxtFile, irTxtFile)
    doLast {
        check(compilerExtensionTestsDir.get().asFile.run { exists() && isDirectory }) {
            "Compiler extensions tests directory does not exist: ${compilerExtensionTestsDir.orNull?.asFile}"
        }
        check(firTxtFile.get().asFile.run { exists() && isFile }) {
            "Fir file does not exist: ${firTxtFile.orNull?.asFile}"
        }
        check(irTxtFile.get().asFile.run { exists() && isFile }) {
            "Ir file does not exist: ${irTxtFile.orNull?.asFile}"
        }
    }
}

tasks.named("test").configure {
    inputs.files(compilerExtensionTestsDir)
    outputs.files(firTxtFile, irTxtFile)
    finalizedBy(checkCompilerExtensionsAreCalled)
}

tasks.check.dependsOn(checkCompilerExtensionsAreCalled)
