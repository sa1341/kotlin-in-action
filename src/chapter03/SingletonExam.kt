package chapter03

import java.time.LocalDateTime

object Singleton {
    val a = 1234
    fun printA() = println(a)
}

object DateTimeUtils {
    val now : LocalDateTime
        get() = LocalDateTime.now()

    const val DEFAULT_FORMAT = "YYYY-MM-DD"
    fun same(a: LocalDateTime, b: LocalDateTime): Boolean {
        return a == b
    }
}

fun main() {
    println(Singleton.a)
    Singleton.printA()

    // DateTimeUtils object 클래스 테스트
    println(DateTimeUtils.now)
    println(DateTimeUtils.now)
    println(DateTimeUtils.now)

    println(DateTimeUtils.DEFAULT_FORMAT)

    val now = LocalDateTime.now()
    println(DateTimeUtils.same(now, now))
}