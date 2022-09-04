package chapter06

fun main(args: Array<String>) {
    for (i in args.indices) {
        println("Argument $i is: ${args[i]}")
    }

    val letters = Array<String>(26) { i -> ('a' + i).toString() }

    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))

    args.forEachIndexed { index, element ->
        println("Argument $index is: $element")
    }
}