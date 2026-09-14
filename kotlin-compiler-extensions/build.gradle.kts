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

val compilerExtensionTestsDir: Provider<Directory> =
    layout.buildDirectory.dir("compiler-extensions-tests")
val firTxtFile: Provider<RegularFile> = compilerExtensionTestsDir.map { it.file("fir.txt") }
val irTxtFile = compilerExtensionTestsDir.map { it.file("ir.txt") }

val checkCompilerExtensionsAreCalled =
    tasks.register("checkCompilerExtensionsAreCalled") {
        description = "Checks that the compiler extensions are called during the test task."
    }

checkCompilerExtensionsAreCalled.configure {
    // The check reads what the whole suite produced, so it runs from `check` rather than
    // finalizing every test run. A filtered run produces only some of the marker files.
    dependsOn(tasks.named("test"))
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
    // The local holds the provider so that the lambda does not capture the script object.
    val testsDir: Provider<Directory> = compilerExtensionTestsDir
    doFirst { testsDir.get().asFile.deleteRecursively() }
}


tasks.check.dependsOn(checkCompilerExtensionsAreCalled)
