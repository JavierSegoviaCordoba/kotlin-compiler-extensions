hubdle {
    config {
        analysis()
        coverage()
        documentation { api() }
        explicitApi()
        // TODO: https://github.com/facebook/ktfmt/pull/400
        //  https://github.com/facebook/ktfmt/issues/314
        format.isEnabled.set(false)
        publishing()
    }

    kotlin {
        jvm {
            features {
                compiler {
                    mainClass.set(
                        "com.javiersc.kotlin.compiler.extensions.GenerateKotlinCompilerTestsKt"
                    )
                }
                contextReceivers()
            }

            main {
                dependencies {
                    api(hubdle.github.adriankuta.treeStructure)
                    api(hubdle.jetbrains.kotlin.kotlinCompiler)
                }
            }

            test {
                dependencies {
                    compileOnly(hubdle.jetbrains.kotlin.kotlinCompilerInternalTestFramework)
                    implementation(projects.kotlinCompilerTest)
                }
            }
        }
    }
}
