package chapter08

import java.lang.StringBuilder

fun String.filter(predicate: (Char) -> Boolean): String {

    val sb = StringBuilder()

    for (index in 0 until length) {
        val element = get(index)
        println("element = $element")
        if (predicate(element)) sb.append(element)
    }

    return sb.toString()
}

fun <T> Collection<T>.joinToString(
    seperator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null
): String {

    val result = StringBuilder()

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(seperator)
        val str = transform?.invoke(element) ?: element.toString()
        result.append(str)
    }

    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    println("ab1c".filter { it in 'a'..'z' })

    val letters = listOf<String>("Apple", "Beta", "Delta")
    println(letters.joinToString())
    println(letters.joinToString(separator = "! "
        , prefix = "! "
        , transform = { it.toUpperCase() }))
}