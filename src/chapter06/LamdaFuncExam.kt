package chapter06

fun main() {
    val list = listOf("a", "b", "c")

    // 후행전달 람다를 사용하여 고차함수 호출이 가능함.
    forEachStr(list) {
        println(it)
    }

    val innerFunc = outerFunc()
    innerFunc();

    val callReference = ::printHello
    callReference()

    val numberList = listOf("1", "2", "3")
    numberList.map(String::toInt).forEach(::println)
}

val printHello: () -> Unit = { println("Hello") }

fun forEachStr(collection: Collection<String>, action: (String) -> Unit) {
    for (item in collection) {
        action(item)
    }
}

fun outerFunc(): () -> Unit = { println("이것은 익명함수 입니다.") }
