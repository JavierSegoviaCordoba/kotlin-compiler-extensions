package com.javiersc.kotlin.compiler.extensions.playground

fun box(): String {
    val hello: String = run { "Hello" }
    return if (hello == "Hello") "OK" else "Fail"
}
