package chapter05

import java.lang.StringBuilder

/**
 * with, apply 수신객체 지정 람다
 */

fun main(args: Array<String>) {

    val result = with(StringBuilder()) {

        for (letter in 'A'..'Z') {
            append(letter)
        }

        append("\nNow I know the alphabet!")
        toString()
    }

    println("result = $result")

    println(alphabetV1())
    println(alphabetV2())
}

fun alphabetV1() = StringBuilder().apply {

    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\nNow I know the alphabet!")
}.toString()

fun alphabetV2() = buildString {

    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\nNow I know the alphabet!")
}