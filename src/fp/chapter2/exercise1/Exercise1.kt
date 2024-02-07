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

    val case1 = listOf(1, 2, 3, 4, 5)
    val case2 = listOf("a", "c", "e", "d")
    println("isSorted: ${isSorted(case1) { a, b -> a <= b }}")
    println("isSorted: ${isSorted(case2) { a, b -> a <= b }}")
}

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    fun go(first: A, second: List<A>): Boolean =
        when {
            second.isEmpty() -> true
            !order(first, second.head) -> false
            else -> go(second.head, second.tail)
        }

    return aa.isEmpty() || go(aa.head, aa.tail)
}

fun <A, B, C> partial(a: A, f: (A, B) -> C): (B) -> C =
    { b: B -> f(a, b) }

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C =
    { a: A -> { b: B -> f(a, b) } }

fun <A, B, C> uncurry(f: (A) -> (B) -> (C)): (A, B) -> C =
    { a: A, b: B -> f(a)(b) }

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C =
    { a: A -> f(g(a)) }

fun plus(num1: Int, numb2: Int): Int =
    num1 + numb2
