package function

fun main() {
    // SAM 생성자
    val filter = StringFilter { s -> s.startsWith(s) }
    consumeFilter(filter)
}

fun consumeFilter(filter: StringFilter) {}

/**
 * SAM Interface 예제코드
 */
fun interface StringFilter {
    fun predicate(str: String): Boolean
}
