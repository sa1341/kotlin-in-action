package chapter07

import java.time.LocalDate

fun main(args: Array<String>) {

    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)

    println("vacation = $vacation")
    println(now.plusWeeks(1) in vacation)

    val n = 9
    println(0..(n + 1))

    (0..n).forEach { print("$it ") }
    println()

    val newYear = LocalDate.ofYearDay(2022, 1)
    val daysOff = newYear.minusDays(1)..newYear

    for (dayOff in daysOff) {
        println(dayOff)
    }
}

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =

    object : Iterator<LocalDate> {

        var current = start

        override fun hasNext() = current <= endInclusive

        override fun next() = current.apply {
            current = plusDays(1)
        }

    }

