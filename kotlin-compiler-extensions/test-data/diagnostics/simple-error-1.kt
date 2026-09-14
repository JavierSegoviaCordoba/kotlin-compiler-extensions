package com.javiersc.kotlin.compiler.extensions.playground

fun simpleError(): String {
    val hello: Int <!INITIALIZER_TYPE_MISMATCH!>=<!> "Hello"
    return hello.toString()
}
