package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.stdlib.tree.TreeNode
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.name
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.ir.util.dumpKotlinLike
import org.jetbrains.kotlin.ir.util.render
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid

public typealias IrTreeNode = TreeNode<IrElement>

public fun IrElement.toIrTreeNode(): IrTreeNode {
    val irTree: TreeNode<IrElement> = IrTreeNode(this)
    this.acceptChildrenVoid(
        object : IrElementVisitorVoid {
            override fun visitElement(element: IrElement) {
                irTree.addChild(element.toIrTreeNode())
            }
        }
    )
    return irTree
}

public fun IrTreeNode.dump(): String = value.dump()

public fun IrTreeNode.dumpKotlinLike(): String = value.dumpKotlinLike()

public fun IrTreeNode.render(): String = value.render()

public inline fun <reified T : IrElement> IrTreeNode.firstIrOrNull(): T? =
    this.firstIrOrNull { true }

public inline fun <reified T : IrElement> IrTreeNode.firstIrOrNull(predicate: (T) -> Boolean): T? =
    this.firstOrNull {
            val asIr: T? = it.value.asIr<T>()
            if (asIr == null) false else predicate(asIr)
        }
        ?.value
        ?.asIr()

public inline fun <reified T : IrElement> Iterable<IrTreeNode>.firstIrOrNull(): T? =
    this.firstIrOrNull(predicate = { true })

public inline fun <reified T : IrElement> Iterable<IrTreeNode>.firstIrOrNull(
    predicate: (T) -> Boolean
): T? =
    this.firstOrNull {
            val asIr = it.value.asIr<T>()
            if (asIr == null) false else predicate(asIr)
        }
        ?.value
        ?.asIr()

public inline fun <reified T : IrElement> IrTreeNode.filterIrIsInstance(): List<T> =
    this.mapNotNull { it.value.asIr() }

public inline fun <reified T : IrElement> Iterable<IrTreeNode>.filterIrIsInstance(): List<T> =
    this.mapNotNull { it.value.asIr() }

public val IrElement.treeNode: IrTreeNode
    get() = this.toIrTreeNode()

public val IrTreeNode.fileName: String?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irFile: IrFile? = node.value.asIr<IrFile>()
            val parent: TreeNode<IrElement>? = parent
            when {
                irFile != null -> irFile.name
                parent != null -> parent.fileName
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.className: String?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irClass: IrClass? = node.value.asIr<IrClass>()
            val parent: TreeNode<IrElement>? = parent
            when {
                irClass != null -> "${irClass.name}"
                parent != null -> parent.className
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.functionName: String?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irFunction: IrFunction? = node.value.asIr<IrFunction>()
            val parent: TreeNode<IrElement>? = parent
            when {
                irFunction != null -> "${irFunction.name}"
                parent != null -> parent.functionName
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.lineNumber: Int?
    get() = root.firstIrOrNull<IrFile>()?.fileEntry?.getLineNumber(value.startOffset)?.plus(1)
