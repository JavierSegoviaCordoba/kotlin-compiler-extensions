package com.javiersc.kotlin.compiler.extensions.fir

import kotlin.contracts.contract
import org.jetbrains.kotlin.fir.getOwnerLookupTag
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.name.FqName

public fun FirBasedSymbol<*>.packageFqName(): FqName? {
    return when (this) {
        is FirClassLikeSymbol<*> -> classId.packageFqName
        is FirCallableSymbol<*> -> callableId.packageName
        else -> null
    }
}

public val FirBasedSymbol<*>.isTopLevel: Boolean
    get() = !hasOwner

public val FirBasedSymbol<*>.isNested: Boolean
    get() = !isTopLevel

public val FirBasedSymbol<*>.hasOwner: Boolean
    get() = runCatching { getOwnerLookupTag() }.getOrNull() != null

public fun FirBasedSymbol<*>.isProperty(): Boolean {
    contract { returns(true) implies (this@FirBasedSymbol is FirPropertySymbol) }
    return this is FirPropertySymbol
}

public fun FirBasedSymbol<*>.isFunction(): Boolean {
    contract { returns(true) implies (this@FirBasedSymbol is FirFunctionSymbol) }
    return this is FirFunctionSymbol
}
