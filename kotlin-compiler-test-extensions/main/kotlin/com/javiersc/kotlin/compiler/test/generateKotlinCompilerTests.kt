package com.javiersc.kotlin.compiler.test

import com.javiersc.kotlin.compiler.test.runners.JvmBoxTest
import com.javiersc.kotlin.compiler.test.runners.JvmDiagnosticTest
import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5

public inline fun <
    reified D : JvmDiagnosticTest,
    reified B : JvmBoxTest,
> generateKotlinCompilerTests() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(testDataRoot = "test-data", testsRoot = "test-gen/java") {
            testClass<D> { model("diagnostics") }
            testClass<B> { model("box") }
        }
    }
}
