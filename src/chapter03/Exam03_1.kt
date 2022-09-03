package chapter03

import java.util.*

fun main(args: Array<String>) {

    val strings = listOf("first", "second", "fourteenth")

    println(strings.last())

    val numbers = setOf(1, 14, 2)
    println(numbers.max())

    val collection = listOf(1, 2, 3)

    println(collection.joinToString(separator = ",", prefix = "(", postfix = ")"))
}

