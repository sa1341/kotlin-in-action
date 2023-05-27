package chapter03

fun main() {
    val currencyList = listOf("달러", "유료", "원")

    // mutable
    val mutableCurrencyList = mutableListOf<String>().apply {
        add("달러")
        add("유료")
        add("원")
    }

    // immutable set
    val numberSet = setOf(1, 2, 3, 4)

    // mutable set
    val mutableSet = mutableSetOf<Int>().apply {
        add(1)
        add(2)
        add(3)
    }

    // immutable map
    val numberMap = mapOf("core" to 1, "two" to 2)

    // mutable map
    val mutableMap = mutableMapOf<String, Int>()
    mutableMap["one"] = 1
    mutableMap["two"] = 2
    mutableMap["three"] = 3

    // 컬렉션 빌더는 내부에선 mutable 반환은 immutable
    // val numberList: List<Int> = buildList { add(1) }

}