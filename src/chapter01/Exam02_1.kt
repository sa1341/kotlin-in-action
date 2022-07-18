package chapter01

import java.util.*

fun main(args: Array<String>) {

    // 맵에 대한 이터레이션
    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    // 컬렉션 구조분해 구문
    val list = listOf("10", "11", "1001")

    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }

    println(isLetter('q'))
    println(isNotDigit('9'))

    println("Kotlin" in setOf("Java","Scala"))
}

fun isLetter(c: Char) = c in 'a'.. 'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'

// when에서 in 사용하기
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}