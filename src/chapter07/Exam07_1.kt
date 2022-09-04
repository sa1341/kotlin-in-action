package chapter07

import java.lang.IndexOutOfBoundsException

/**
 * 산술 연산자 오버로딩
 * 코틀린에서 관례를 사용하는 가장 단순한 산술 연산자
 * 클래스에 대한 일반 산술 연산자를 정의할 수 있는 예제코드
 */
fun main(args: Array<String>) {

    val p1 = Point(20, 40)
    val p2 = Point(10, 20)

    println(p1 + p2)
    println(p1 * p2)
    println(p1 / p2)
    println(p1 % p2)

    println('a' * 3)

    val p = Point(10, 20)
    println(-p)

    println(p[0])
    println(p[1])

    val (x, y) = p
    println("x = $x, y = $y")
}

/**
 * 이항 산술 연산자 함수 명은 아래 네이밍을 따라야 합니다.
 */
data class Point(val x: Int, val y: Int) {

    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

    operator fun times(other: Point): Point {
        return Point(x * other.x, y * other.y)
    }

    operator fun div(other: Point): Point {
        return Point(x / other.x, y / other.y)
    }

    operator fun rem(other: Point): Point {
        return Point(x % other.x, y % other.y)
    }
}

// 결과 타입이 피연산자 타입과 다른 연산자 정의하는 확장함
operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

operator fun Point.unaryMinus(): Point {
    return Point(-x, - y)
}

operator fun Point.get(index: Int): Int {
    return when(index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class MutablePoint(
    var x: Int, var y: Int
)

operator fun MutablePoint.set(index: Int, value: Int) {

    when(index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}