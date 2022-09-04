package chapter07

import java.math.BigDecimal

fun main(args: Array<String>) {

    // 변경 가능한 컬렉션
    // 메모리에 있는 객체 생태를 변화시킴
    val list = arrayListOf(1,2)
    list += 3

    // 읽기 전용에서는 변경을 적용한 복사본을 반환함.
    val newList = list + listOf(4, 5)

    println(list)

    println(newList)

    var bd = BigDecimal.ZERO
    println("bd = ${++bd}")
}