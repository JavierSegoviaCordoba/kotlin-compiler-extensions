package com.javiersc.kotlin.compiler.extensions.ir.context.receivers

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

public fun interface IrGeneration {

    context(IrModuleFragment, IrPluginContext)
    public fun generate()

    public fun getExtension(): IrGenerationExtension =
        object : IrGenerationExtension {
            override fun generate(
                moduleFragment: IrModuleFragment,
                pluginContext: IrPluginContext
            ) {
                with(moduleFragment) { with(pluginContext) { generate() } }
            }
        }
}
