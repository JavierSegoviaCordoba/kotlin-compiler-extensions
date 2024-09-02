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
    }

    kotlin { //
        jvm {
            main { //
                dependencies { //
                    implementation(gradleKotlinDsl())
                    compileOnly(hubdle.jetbrains.kotlin.gradle.plugin)
                }
            }
        }
    }
}
