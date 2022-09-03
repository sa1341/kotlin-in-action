package chapter02

import chapter02.Color.*
import java.lang.IllegalArgumentException
import java.util.*

class GentleMan(
    val name: String,
    var isMarried: Boolean
)

class Rectangle(val height: Int, val weight: Int) {

    val isSquare: Boolean
        get() {
            return weight == height
        }

    fun createRandomRectangle(): Rectangle {
        val random = Random()
        return Rectangle(random.nextInt(), random.nextInt())
    }

    override fun toString(): String {
        return "Rectangle(height=$height, weight=$weight)"
    }
}


fun main(args: Array<String>) {
    println("Hello ${if (args.isNotEmpty()) args[0] else "someone"}")

    val gentleMan = GentleMan("jean", false)
    println("${gentleMan.isMarried}")

    // is로 시작하는 프로퍼티의 getter는 get이 붙지 않고 원래 이름을 그대로 사용함.
    gentleMan.isMarried = true
    println("${gentleMan.isMarried}")

    // Custom Accessor Exam
    val rectangle = Rectangle(30, 30)
    println("This Shape is ${rectangle.isSquare}")
    println(rectangle.createRandomRectangle())

    println("RGB: ${Color.BLUE.rgb()}")

    println("Mnemonic: ${getMnemonic(BLUE)}")
    println("warmth: ${getWarmth(BLUE)}")

    println("mix: ${mix(BLUE, VIOLET).rgb()}")
    println("mixOptimized: ${mixOptimized(BLUE, VIOLET).rgb()}")

    println("expression: ${eval(
        Sum(
            Sum(Num(3), Num(4)),
            Num(2)
        )
    )}")
    println("expression: ${evalWithLogging(
        Sum(
            Sum(Num(3), Num(4)),
            Num(2)
        )
    )}")

    for (i in 1..100) {
        println(fizzBuzz(i))
    }

}

enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    // enum 클래스는 메서드 정의할 경우 반드시 상수목록과 메서드 사이에 세미콜론을 넣어야함.
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}

fun getMnemonic(color: Color) =
    when (color) {
        RED -> "Richard"
        ORANGE -> "Of"
        YELLOW -> "York"
        GREEN -> "Gave"
        BLUE -> "Battle"
        INDIGO -> "In"
        VIOLET -> "Vain"
    }

fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {

    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO

    else -> throw Exception("Dirty Color")
}

fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) ||
                (c1 == YELLOW && c2 == YELLOW) ->
            ORANGE

        (c1 == YELLOW && c2 == BLUE) ||
                (c1 == BLUE && c2 == YELLOW) ->
            GREEN

        (c1 == BLUE && c2 == VIOLET) ||
                (c1 == VIOLET && c2 == BLUE) ->
            INDIGO

        else -> throw Exception("Dirty Color")
    }

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }

        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }

        else -> throw IllegalArgumentException("Unknown expression")
    }

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "fizzBuzz"
    i % 3 == 0 -> "fizz"
    i % 5 == 0 -> "buzz"
    else -> "$i"
}


