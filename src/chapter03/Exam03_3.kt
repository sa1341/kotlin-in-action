package chapter03

import chapter03.lastChar as last

fun main(args: Array<String>) {
    val name = "jean".last()
    println("name = $name")

    // 확장함수 사용예제
    val member = Member("jun", 31)
    member.getHobby()

    // 3중 따옴표 문자열 - 줄 바꿈이 들어있어도 텍스트를 쉽게 문자열로 변환 가능함.
    val kotlinLogo = """| //
            .| //
            .| /\"""


    println("kotlinLogo = $kotlinLogo")
    val price = """${'$'}99.9"""
    println("price = $price")
}