package fp.chapter2.exercise1

import fp.chapter2.exercise1.Exercise2.abs
import fp.chapter2.exercise1.Exercise2.factorial
import fp.chapter2.exercise1.Exercise2.formatResult

val exercise1 = {

    fun abs(num: Int): Int =
        if (num < 0) {
            -num
        } else {
            num
        }

    fun formatAbs(num: Int): String {
        val msg = "The absolute value of %d is %d"
        return msg.format(num, abs(num))
    }

    fun factorial(i: Int): Int {
        // 관례적으로 도우미 함수라고 칭합니다.
        tailrec fun go(n: Int, acc: Int): Int =
            if (n <= 0) {
                acc
            } else {
                go(n - 1, n * acc)
            }
        return go(i, 1)
    }

    fun fib(i: Int): Int {
        fun go(cnt: Int, curr: Int, nxt: Int): Int =
            if (cnt == 1) {
                curr
            } else {
                go(cnt - 1, nxt, curr + nxt)
            }
        return go(i, 0, 1)
    }
}

object Exercise2 {

    fun abs(num: Int) =
        if (num < 0) {
            -num
        } else {
            num
        }

    fun factorial(num: Int): Int {
        fun go(n: Int, acc: Int): Int =
            if (n <= 0) {
                acc
            } else {
                go(n - 1, n * acc)
            }
        return go(num, 1)
    }

    fun formatAbs(x: Int): String {
        val msg = "The absolute value of %d is %d"
        return msg.format(x, abs(x))
    }

    fun formatFactorial(x: Int): String {
        val msg = "The factorial of %d is %d"
        return msg.format(x, factorial(x))
    }

    fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
        val msg = "The %s of %d is %d."
        return msg.format(name, n, f(n))
    }
}

fun main() {
    println(Exercise2.formatAbs(-42))
    println(Exercise2.formatFactorial(7))
    println(formatResult("factorial", 7, ::factorial))
    println(formatResult("absolute", -42, ::abs))
    println(formatResult("absolute", -42) { if (it < 0) -it else it })
}
