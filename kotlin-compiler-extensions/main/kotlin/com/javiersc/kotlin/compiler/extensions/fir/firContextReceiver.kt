package com.javiersc.kotlin.compiler.extensions.fir

// import org.jetbrains.kotlin.fir.declarations.FirRegularClass
// import org.jetbrains.kotlin.fir.declarations.FirSimpleFunction
// import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol
// import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
// import org.jetbrains.kotlin.fir.types.coneType
// import org.jetbrains.kotlin.name.Name
//
// public inline val FirContextReceiver.typeRefName: Name?
//    get() = (typeRef.coneType as? ConeLookupTagBasedType)?.lookupTag?.name
//
// public inline val FirBasedSymbol<*>.contextReceivers: List<FirContextReceiver>
//    get() =
//        when (val fir = this.fir) {
//            is FirSimpleFunction -> fir.contextReceivers
//            is FirRegularClass -> fir.contextReceivers
//            else -> emptyList()
//        }
