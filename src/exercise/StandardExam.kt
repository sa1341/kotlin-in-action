package exercise

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun main() {
    val basDt = "20230228"
    val currentDate = LocalDate.parse(
        basDt,
        DateTimeFormatter.ofPattern("yyyyMMdd")
    )
    val staDt = currentDate.dayOfMonth
    val month = YearMonth.from(currentDate)
    val dayOfWeek = currentDate.dayOfWeek.name

    println("staDt = $staDt")
    println("dayOfWeek = $dayOfWeek")
    println("lengthOfMonth = ${month.lengthOfMonth()}")

    val list = listOf<String>("a", "b", "c")
    val emptyList = emptyList<String>()

    val result = list.takeIf {
        it.isNotEmpty()
    }?.firstOrNull{
        it == "a"
    } ?: "Empty List"

    println("Result = $result")

}