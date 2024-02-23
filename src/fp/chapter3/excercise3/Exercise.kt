package fp.chapter3.excercise3

import java.time.LocalDate
import java.time.Month

data class Account(
    val name: String,
    val accountNumber: String,
    val age: Int,
    val joinDate: LocalDate,
)

/**
 * 코틀린 컬렉션 표준라이브러리
 */
fun main() {
    accountList.take(1).forEach {
        println(it)
    }

    // takewhile
    accountList.takeWhile { account ->
        LocalDate.of(2023, Month.JANUARY, 1).isAfter(account.joinDate)
    }.onEach {
        println(it)
    }
}

val accountList = buildList {
    add(
        Account(
            name = "임준영",
            accountNumber = "02000162898",
            age = 33,
            joinDate = LocalDate.of(2022, Month.AUGUST, 3),
        ),
    )
    add(
        Account(
            name = "임종수",
            accountNumber = "02000173298",
            age = 60,
            joinDate = LocalDate.of(2021, Month.APRIL, 23),
        ),
    )
    add(
        Account(
            name = "노영희",
            accountNumber = "02000151290",
            age = 56,
            joinDate = LocalDate.of(2024, Month.JANUARY, 2),
        ),
    )
}
