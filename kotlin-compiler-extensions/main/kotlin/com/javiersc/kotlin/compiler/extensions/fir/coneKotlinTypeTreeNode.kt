package com.javiersc.kotlin.compiler.extensions.fir

import com.javiersc.kotlin.compiler.extensions.fir.ConeNode.ClassLike
import com.javiersc.kotlin.compiler.extensions.fir.ConeNode.TypeParameter
import com.javiersc.kotlin.stdlib.tree.TreeNode
import org.jetbrains.kotlin.fir.symbols.ConeClassLikeLookupTag
import org.jetbrains.kotlin.fir.symbols.ConeClassifierLookupTag
import org.jetbrains.kotlin.fir.types.ConeClassLikeType
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.name.ClassId

public typealias ConeKotlinTypeTreeNode = TreeNode<ConeNode>

public fun ConeKotlinType.toTreeNode(): ConeKotlinTypeTreeNode {
    val node: ConeNode =
        when (this) {
            is ConeClassLikeType -> ClassLike(this)
            is ConeTypeParameterType -> TypeParameter(this)
            else -> TODO("The `ConeKotlinType` $this is not supported yet")
        }
    val tree: ConeKotlinTypeTreeNode = ConeKotlinTypeTreeNode(node)
    for (argument: ConeTypeProjection in this.typeArguments) {
        if (argument is ConeKotlinType) tree.addChild(argument.toTreeNode())
        else TODO("The `ConeTypeProjection` $argument is not supported yet")
    }
    return tree
}

public inline val ConeKotlinType.treeNode: ConeKotlinTypeTreeNode
    get() = this.toTreeNode()

public sealed class ConeNode {

    public abstract val type: ConeKotlinType

    public abstract val lookupTag: ConeClassifierLookupTag

    public data class ClassLike(override val type: ConeClassLikeType) : ConeNode() {

        override val lookupTag: ConeClassLikeLookupTag = type.lookupTag

        public val classId: ClassId = type.lookupTag.classId
    }

    public data class TypeParameter(override val type: ConeTypeParameterType) : ConeNode() {

        override val lookupTag: ConeClassifierLookupTag = type.lookupTag
    }
}
