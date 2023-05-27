package com.javiersc.kotlin.compiler.test

import com.javiersc.kotlin.compiler.test.runners.BoxTest
import com.javiersc.kotlin.compiler.test.runners.DiagnosticTest
import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5

public inline fun <reified D : DiagnosticTest, reified B : BoxTest> generateKotlinCompilerTests() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(testDataRoot = "test-data", testsRoot = "test-gen/java") {
            testClass<D> { model("diagnostics") }
            testClass<B> { model("box") }
        }
    }
}
