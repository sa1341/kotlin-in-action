package chapter05
fun plus(pair: Pair<Int, Int>) = pair.first + pair.second

fun main() {
    val plus = plus(Pair(1, 3))
    println(plus)

    // pair 내부적으로 프로퍼티 값들이 불변으로 선언됨.
    val pair = Pair("A", 1)
    val newPair = pair.copy(first = "B")
    println(newPair)

    val second = newPair.component2()
    println(second)

    // list는 immutable한 형태임
    val list = newPair.toList()
    println(list)

    val triple = Triple("A", "B", "C")
    println(triple)
    println("${triple.first}, ${triple.second}, ${triple.third}")

    // 구조적 문법 할당
    val (a: String, b: String, c: String) = triple
    println("구조적 문법할당 = $a, $b, $c")

    // list도 구조적 문법할당이 가능함 (내부적으로 componentN 메서드를 가지고 있음)
    val list3 = triple.toList()
    val (a1, a2, a3) = list3
    println("list 구조적 문법할당 = $a1, $a2, $a3")
}