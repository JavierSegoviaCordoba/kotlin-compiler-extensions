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
