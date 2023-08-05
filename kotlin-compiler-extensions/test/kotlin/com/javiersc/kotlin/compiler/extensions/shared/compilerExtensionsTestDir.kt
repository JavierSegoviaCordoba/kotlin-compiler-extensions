package com.javiersc.kotlin.compiler.extensions.shared

import com.javiersc.kotlin.kotlin.compiler.extensions.KotlinCompilerExtensionsProjectData.ProjectDirAbsolutePath
import java.io.File

internal val compilerExtensionsTestDir: File =
    File(ProjectDirAbsolutePath).resolve("build/compiler-extensions-tests/")
