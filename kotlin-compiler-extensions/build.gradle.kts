import com.android.build.gradle.internal.tasks.factory.dependsOn
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
                    gradleVersion.mapIfKotlinVersionIsProvided(getKotlinPluginVersion())
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

fun GradleVersion.mapIfKotlinVersionIsProvided(kotlinVersion: String): String {
    val major: Int = major
    val minor: Int = minor
    val patch: Int = patch
    check(kotlinVersion.isKotlinDevVersion()) {
        """ |Kotlin version: $kotlinVersion
            |Requirements to use a specific Kotlin version:  
            | - It must be a dev version, for example: `1.9.20-dev-5788`
            |Check the Kotlin dev versions on the bootstrap repository:
            |  - https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap/org/jetbrains/kotlin/ 
        """
            .trimMargin()
    }
    check(isSnapshot) {
        """ |Current version: ${this@mapIfKotlinVersionIsProvided}
            |Kotlin version: $kotlinVersion
            |Requirements to use a specific Kotlin version:  
            | - Use a SNAPSHOT version with `-P semver.stage=snapshot`
            | - Clean repo or use `-P semver.checkClean=false`
        """
            .trimMargin()
    }
    return "$major.$minor.$patch+$kotlinVersion-SNAPSHOT"
}

fun String.isKotlinDevVersion(): Boolean =
    matches(Regex("""(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)-dev-(0|[1-9]\d*)"""))
