package chapter06

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)
    addValidNumbersFilterNotNull(numbers)
}

fun readNumbers(reader: BufferedReader): List<Int?> {

    val result = ArrayList<Int?>()

    for (line in reader.lineSequence()) {
        try {
            result.add(line.toInt())
        } catch (e: NumberFormatException) {
            result.add(null)
        }
    }

    return result
}

fun addValidNumbers(numbers: List<Int?>) {

    var sumOfValidNumbers = 0
    var invalidNumbers = 0

    for (number in numbers) {

        if (number != null) {
            sumOfValidNumbers += number
        } else {
            invalidNumbers++
        }
    }

    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
}

fun addValidNumbersFilterNotNull(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}