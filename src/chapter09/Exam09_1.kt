package chapter09


fun main(args: Array<String>) {

    val list = listOf(1, 3)
    println("sum = ${list.sum()}")
}

fun <T: Number> List<T>.sum(): T = this[0]