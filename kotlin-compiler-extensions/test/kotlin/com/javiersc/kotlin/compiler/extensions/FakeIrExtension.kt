package com.javiersc.kotlin.compiler.extensions

import com.javiersc.kotlin.compiler.extensions.ir.IrTreeNode
import com.javiersc.kotlin.compiler.extensions.ir.buildIrReturn
import com.javiersc.kotlin.compiler.extensions.ir.name
import com.javiersc.kotlin.compiler.extensions.ir.treeNode
import com.javiersc.kotlin.compiler.extensions.ir.asIr
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrReturn
import org.jetbrains.kotlin.ir.interpreter.toIrConst

class FakeIrExtension : IrGenerationExtension {

    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        val builtIns = pluginContext.irBuiltIns
        val irElements: List<IrTreeNode> = moduleFragment.files.flatMap { it.treeNode }
        val greetingsIrCall: IrCall? =
            irElements
                .firstOrNull { it.value.asIr<IrCall>()?.name?.toString() == "greetings" }
                ?.value
                .asIr<IrCall>()
        val greetingsIrSimpleFunction: IrSimpleFunction = greetingsIrCall?.symbol?.owner ?: return
        val hiIrExpression: IrConst<*> = "hi".toIrConst(builtIns.stringType)
        val hiIrReturn: IrReturn = greetingsIrSimpleFunction.buildIrReturn(hiIrExpression)
        greetingsIrSimpleFunction.body.asIr<IrBlockBody>()?.statements?.apply {
            clear()
            add(hiIrReturn)
        }
    }
}
