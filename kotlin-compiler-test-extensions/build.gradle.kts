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
            optIn("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
        }
        publishing()
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
            features { //
                contextReceivers()
            }

            main {
                dependencies {
                    compileOnly(hubdle.jetbrains.kotlin.compiler)
                    compileOnly(hubdle.jetbrains.kotlin.compiler.internal.test.framework)
                    implementation(hubdle.junit.jupiter.junit.jupiter.api)
                }
            }
        }
    }
}

fun String.isKotlinDevVersion(): Boolean =
    matches(Regex("""(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)-dev-(0|[1-9]\d*)"""))
