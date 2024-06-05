package function

import java.time.LocalDate

data class Point(
    val x: Int,
    val y: Int,
) {

    fun zeroPointSymmetry(): Point = Point(-x, -y)

    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    operator fun inc(): Point {
        return Point(x + 1, y + 1)
    }
}

data class Days(val day: Long)

operator fun LocalDate.plus(days: Days): LocalDate {
    return this.plusDays(days.day)
}

val Int.d: Days
    get() = Days(this.toLong())

fun main() {
    var point = Point(10, -20)
    println("zeroPointSymmetry = ${point.zeroPointSymmetry()}")
    println("unaryMinus = ${-point}")
    println("inc = ${++point}")

    val after3Day = LocalDate.of(2024, 1, 1) + Days(3)
    println("after3Day = $after3Day")

    val after2Day = LocalDate.of(2024, 1, 1) + 2.d
    println("after2Day = $after2Day")
}
