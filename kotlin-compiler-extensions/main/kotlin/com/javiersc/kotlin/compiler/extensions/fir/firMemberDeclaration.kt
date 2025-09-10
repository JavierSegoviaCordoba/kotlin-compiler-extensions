package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
import org.jetbrains.kotlin.fir.declarations.FirClass
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias
import org.jetbrains.kotlin.fir.declarations.utils.classId
import org.jetbrains.kotlin.name.Name

public inline val FirMemberDeclaration.name: Name
    get() =
        when (this) {
            is FirCallableDeclaration -> this.symbol.callableId?.callableName
            is FirClass -> this.classId.shortClassName
            is FirTypeAlias -> this.name
        } ?: Name.special("<anonymous>")
