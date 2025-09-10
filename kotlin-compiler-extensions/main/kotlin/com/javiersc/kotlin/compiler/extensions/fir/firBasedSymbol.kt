@file:Suppress("NOTHING_TO_INLINE")

package com.javiersc.kotlin.compiler.extensions.fir

import kotlin.contracts.contract
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
import org.jetbrains.kotlin.fir.getOwnerLookupTag
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

public inline val FirBasedSymbol<*>.packageFqName: FqName
    get() =
        when (this) {
            is FirClassLikeSymbol<*> -> classId.packageFqName
            is FirCallableSymbol<*> -> callableId?.packageName ?: FqName.ROOT
            else -> TODO("Not implemented")
        }

public inline val FirBasedSymbol<*>.isTopLevel: Boolean
    get() = !hasOwner

public inline val FirBasedSymbol<*>.isNested: Boolean
    get() = !isTopLevel

public inline val FirBasedSymbol<*>.hasOwner: Boolean
    get() = runCatching { getOwnerLookupTag() }.getOrNull() != null

public inline fun FirBasedSymbol<*>.isProperty(): Boolean {
    contract { returns(true) implies (this@isProperty is FirPropertySymbol) }
    return this is FirPropertySymbol
}

public inline fun FirBasedSymbol<*>.isFunction(): Boolean {
    contract { returns(true) implies (this@isFunction is FirFunctionSymbol) }
    return this is FirFunctionSymbol
}

public inline val FirBasedSymbol<*>.name: Name
    get() =
        when (val fir = this.fir) {
            is FirMemberDeclaration -> fir.name
            else -> TODO("Not implemented")
        }
