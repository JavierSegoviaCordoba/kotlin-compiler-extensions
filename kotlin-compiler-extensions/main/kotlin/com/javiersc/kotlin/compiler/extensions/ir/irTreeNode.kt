package com.javiersc.kotlin.compiler.extensions.ir

import com.javiersc.kotlin.stdlib.tree.TreeNode
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
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

public val IrElement.treeNode: IrTreeNode
    get() = this.toIrTreeNode()

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

public val IrTreeNode.irFile: IrFile?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irFile: IrFile? = node.value.asIr()
            val parent: TreeNode<IrElement>? = parent
            when {
                irFile != null -> irFile
                parent != null -> parent.irFile
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.parentIrClass: IrClass?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irClass: IrClass? = node.value.asIr()
            val parent: TreeNode<IrElement>? = parent
            when {
                irClass != null -> irClass
                parent != null -> parent.parentIrClass
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.parentIrDeclaration: IrDeclaration?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irDeclaration: IrDeclaration? = node.value.asIr()
            val parent: TreeNode<IrElement>? = parent
            when {
                irDeclaration != null -> irDeclaration
                parent != null -> parent.parentIrDeclaration
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.parentIrDeclarationWithName: IrDeclarationWithName?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irDeclaration: IrDeclarationWithName? = node.value.asIr()
            val parent: TreeNode<IrElement>? = parent
            when {
                irDeclaration != null -> irDeclaration
                parent != null -> parent.parentIrDeclarationWithName
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.parentIrFunction: IrFunction?
    get() {
        val recursive = DeepRecursiveFunction { node: TreeNode<IrElement> ->
            val irDeclaration: IrFunction? = node.value.asIr()
            val parent: TreeNode<IrElement>? = parent
            when {
                irDeclaration != null -> irDeclaration
                parent != null -> parent.parentIrFunction
                else -> null
            }
        }
        return recursive(this)
    }

public val IrTreeNode.lineNumber: Int?
    get() = irFile?.fileEntry?.getLineNumber(value.startOffset)?.plus(1)
