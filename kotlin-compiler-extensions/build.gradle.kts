import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

hubdle {
    config {
        analysis()
        coverage()
        documentation { //
            api()
        }
        explicitApi()
        // TODO: https://github.com/facebook/ktfmt/pull/400
        //  https://github.com/facebook/ktfmt/issues/314
        format.isEnabled.set(false)
        languageSettings { //
            experimentalContracts()
        }
        publishing()
        projectConfig()
        versioning {
            semver {
                mapVersion { gradleVersion ->
                    val mappedVersion = gradleVersion.copy(metadata = getKotlinPluginVersion())
                    "$mappedVersion"
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
                    testDependencies(hubdle.javiersc.kotlin.kotlinStdlib)
                }
                contextReceivers()
            }

            main { //
                dependencies { //
                    api(hubdle.jetbrains.kotlin.kotlinCompiler)
                }
            }

            test {
                dependencies {
                    compileOnly(hubdle.jetbrains.kotlin.kotlinCompilerInternalTestFramework)
                    implementation(projects.kotlinCompilerTestExtensions)
                }
            }
        }
    }
}

val compilerExtensionTestsDir: File = buildDir.resolve("compiler-extensions-tests")
val firTxtFile = compilerExtensionTestsDir.resolve("fir.txt")
val irTxtFile = compilerExtensionTestsDir.resolve("ir.txt")

val checkCompilerExtensionsAreCalled = tasks.register("checkCompilerExtensionsAreCalled")

checkCompilerExtensionsAreCalled.configure {
    inputs.files(compilerExtensionTestsDir, firTxtFile, irTxtFile)
    outputs.files(firTxtFile, irTxtFile)
    doLast {
        check(compilerExtensionTestsDir.exists() && compilerExtensionTestsDir.isDirectory) {
            "Compiler extensions tests directory does not exist: $compilerExtensionTestsDir"
        }
        check(firTxtFile.exists() && firTxtFile.isFile) { "Fir file does not exist: $firTxtFile" }
        check(irTxtFile.exists() && irTxtFile.isFile) { "Ir file does not exist: $irTxtFile" }
    }
}

tasks.named("test").configure {
    inputs.files(compilerExtensionTestsDir)
    outputs.files(firTxtFile, irTxtFile)
    finalizedBy(checkCompilerExtensionsAreCalled)
}

tasks.named("allTestsReport").configure { dependsOn(checkCompilerExtensionsAreCalled) }

tasks.check.dependsOn(checkCompilerExtensionsAreCalled)
