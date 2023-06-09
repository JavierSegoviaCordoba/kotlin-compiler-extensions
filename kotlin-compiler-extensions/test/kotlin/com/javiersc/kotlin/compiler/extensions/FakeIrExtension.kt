package com.javiersc.kotlin.compiler.extensions

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

class FakeIrExtension : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
//        val builtIns = pluginContext.irBuiltIns
//        val irElements: List<IrTreeNode> = moduleFragment.files.flatMap { it.treeNode }
//        val greetingsIrCall: IrCall? =
//            irElements
//                .firstOrNull { it.value.asIr<IrCall>()?.name?.toString() == "greetings" }
//                ?.value
//                .asIr<IrCall>()
//        val greetingsIrSimpleFunction: IrSimpleFunction = greetingsIrCall?.symbol?.owner ?: return
//        val hiIrExpression: IrConst<*> = "hi".toIrConst(builtIns.stringType)
//        val hiIrReturn: IrReturn = greetingsIrSimpleFunction.copyIrReturn(hiIrExpression)
//        greetingsIrSimpleFunction.body.asIr<IrBlockBody>()?.statements?.apply {
//            clear()
//            add(hiIrReturn)
//        }
    }
}
