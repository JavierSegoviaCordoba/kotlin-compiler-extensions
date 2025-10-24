package com.javiersc.kotlin.compiler.test.services

import org.jetbrains.kotlin.test.directives.model.SimpleDirective
import org.jetbrains.kotlin.test.directives.model.SimpleDirectivesContainer

public object AdditionalFilesDirectives : SimpleDirectivesContainer() {

    public val SOME_FILE_DIRECTIVE: SimpleDirective by
        directive(
            description =
                """
                Adds common context annotations
                See file ./test-data/additional-files/SomeFile.kt
                """
                    .trimIndent()
        )
}
