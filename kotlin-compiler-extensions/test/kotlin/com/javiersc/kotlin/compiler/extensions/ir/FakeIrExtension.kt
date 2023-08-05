package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.compiler.extensions.shared.compilerExtensionsTestDir
import java.io.File
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

class FakeIrExtension : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        val irTxtFile: File =
            compilerExtensionsTestDir
                .apply(File::mkdirs)
                .resolve("ir.txt")
                .apply(File::createNewFile)
        irTxtFile.writeText("FakeIrExtension has been called")
    }
}
