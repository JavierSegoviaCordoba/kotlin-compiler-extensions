package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol
import org.jetbrains.kotlin.name.FqName

public fun FirBasedSymbol<*>.packageFqName(): FqName? {
    return when (this) {
        is FirClassLikeSymbol<*> -> classId.packageFqName
        is FirCallableSymbol<*> -> callableId.packageName
        else -> null
    }
}
