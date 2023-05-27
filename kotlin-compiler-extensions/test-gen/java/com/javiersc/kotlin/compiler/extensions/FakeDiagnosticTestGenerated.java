

package com.javiersc.kotlin.compiler.extensions;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link com.javiersc.kotlin.compiler.extensions.GenerateKotlinCompilerTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("test-data/diagnostics")
@TestDataPath("$PROJECT_ROOT")
public class FakeDiagnosticTestGenerated extends AbstractFakeDiagnosticTest {
    @Test
    public void testAllFilesPresentInDiagnostics() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("test-data/diagnostics"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("playground-test.kt")
    public void testPlayground_test() throws Exception {
        runTest("test-data/diagnostics/playground-test.kt");
    }
}
