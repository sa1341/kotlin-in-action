package chapter02

import java.lang.IllegalArgumentException

fun main() {
    failFast("예외발생")

    val b: String? = null
    val c = b ?: failFast("a is null")
    println(c.length)
}

fun failFast(message: String): Nothing {
    throw IllegalArgumentException(message)
}
