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
