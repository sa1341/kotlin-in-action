package chapter01

var x = 5
fun main() {
    val a: Int = 1
    val b = 1

    // 타입 명시필요
    val c: Int
    c = 3

    val d: String?
    d = null

    val e: String = "Hello"
    //e = "World" val(value)는 한번 값을 선언하면 변경 불가(read-only)

    println(x)
}