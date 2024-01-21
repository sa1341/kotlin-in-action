package chapter06

fun main(args: Array<String>) {
    var input: String? = null
    input = ""

    // 코틀린은 null이 될 수 있는 타입의 확장함수를 제공한다.
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    } else {
        println("input = $input")
    }

    println(printHashCode("str"))
}

fun <T : Any> printHashCode(t: T) {
    println(t.hashCode())
}
