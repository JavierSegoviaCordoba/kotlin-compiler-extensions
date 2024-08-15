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
