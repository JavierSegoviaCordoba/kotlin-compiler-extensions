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
                    val mappedVersion = gradleVersion.copy(metadata = getKotlinPluginVersion())
                    "$mappedVersion"
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
                    compileOnly(hubdle.jetbrains.kotlin.kotlinCompiler)
                    compileOnly(hubdle.jetbrains.kotlin.kotlinCompilerInternalTestFramework)
                    implementation(hubdle.junit.jupiter.junitJupiterApi)
                }
            }
        }
    }
}
