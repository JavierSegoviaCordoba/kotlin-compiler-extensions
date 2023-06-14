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
    }

    kotlin {
        jvm {
            features {
                compiler {
                    addExtensionDependencies(false)
                    generateTestOnSync(false)
                    mainClass.set(
                        "com.javiersc.kotlin.compiler.extensions.GenerateKotlinCompilerTestsKt"
                    )
                }
                contextReceivers()
            }

            main {
                dependencies {
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
