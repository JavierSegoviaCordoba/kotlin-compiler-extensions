package foo.bar

fun box(): String {
    val hello = greetings()
    return if (hello == "hi") "OK" else "Fail"
}

fun greetings(): String {
    return "hello" // replaced with "hi"
}
