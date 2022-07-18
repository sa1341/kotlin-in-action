package chapter01

import java.io.BufferedReader
import java.io.StringReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun main(args: Array<String>) {

    // kotlin에서는 별도로 checked, unchecked 예외를 구별하지 않는다.
    val number = 103

    val persentage =
            if (number in 1..100)
                number
            else
                throw IllegalArgumentException(
                    "A percentage value must be between 0 and 100: $number"
                )

    println("percentage: $persentage")

    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
}

fun readNumber(reader: BufferedReader) {

    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}