package com.javiersc.kotlin.compiler.extensions.ir

import com.github.adriankuta.datastructure.tree.TreeNode
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid

public typealias IrElementTree = TreeNode<IrElement>

public fun IrElement.toTree(): IrElementTree {
    val irTree: IrElementTree = IrElementTree(this)
    this.acceptChildrenVoid(
        object : IrElementVisitorVoid {
            override fun visitElement(element: IrElement) {
                irTree.addChild(element.toTree())
            }
        }
    )
    return irTree
}

public val IrElement.tree: IrElementTree
    get() = this.toTree()
