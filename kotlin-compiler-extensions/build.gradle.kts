import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.javiersc.gradle.properties.extensions.getStringProperty
import com.javiersc.gradle.version.GradleVersion
import com.javiersc.gradle.version.isSnapshot
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

hubdle {
    config {
        analysis()
        coverage()
        documentation { //
            api()
        }
        explicitApi()
        languageSettings { //
            experimentalContracts()
        }
        publishing()
        projectConfig()
        versioning {
            semver {
                mapVersion { gradleVersion ->
                    val kotlinVersion = getKotlinPluginVersion()
                    val metadata =
                        gradleVersion.metadata?.let { "$kotlinVersion-$it" } ?: kotlinVersion
                    "${gradleVersion.copy(metadata = metadata)}"
                }
            }
        }
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
                contextReceivers()
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
            "Compiler extensions tests directory does not exist: $compilerExtensionTestsDir"
        }
        check(firTxtFile.get().asFile.run { exists() && isFile }) {
            "Fir file does not exist: $firTxtFile"
        }
        check(irTxtFile.get().asFile.run { exists() && isFile }) {
            "Ir file does not exist: $irTxtFile"
        }
    }
}

tasks.named("test").configure {
    inputs.files(compilerExtensionTestsDir)
    outputs.files(firTxtFile, irTxtFile)
    finalizedBy(checkCompilerExtensionsAreCalled)
}

tasks.check.dependsOn(checkCompilerExtensionsAreCalled)

fun String.isKotlinDevVersion(): Boolean =
    matches(Regex("""(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)-dev-(0|[1-9]\d*)"""))
