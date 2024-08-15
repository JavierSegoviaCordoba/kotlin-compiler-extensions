package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.stdlib.tree.TreeNode
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.render
import org.jetbrains.kotlin.fir.renderWithType
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid

public typealias FirTreeNode = TreeNode<FirElement>

public fun FirElement.toFirTreeNode(): FirTreeNode {
    val firTree: TreeNode<FirElement> = FirTreeNode(this)
    this.acceptChildren(
        object : FirVisitorVoid() {
            override fun visitElement(element: FirElement) {
                firTree.addChild(element.toFirTreeNode())
            }
        })
    return firTree
}

public val FirElement.treeNode: FirTreeNode
    get() = this.toFirTreeNode()

public fun FirTreeNode.render(): String = value.render()

public fun FirTreeNode.renderWithType(): String = value.renderWithType()
