package com.javiersc.kotlin.compiler.extensions.fir

import org.jetbrains.kotlin.fir.declarations.FirContextReceiver
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType
import org.jetbrains.kotlin.fir.types.coneType
import org.jetbrains.kotlin.name.Name

public val FirContextReceiver.typeRefName: Name?
    get() = (typeRef.coneType as? ConeLookupTagBasedType)?.lookupTag?.name
