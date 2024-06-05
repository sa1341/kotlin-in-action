package function

fun main() {
    val num1 = 2
    val num2 = 3

    // 람다식
    val result1 = compute(num1, num2) { a, b -> a + b }
    println("result1 = $result1")

    // 익명함수
    val result2 = compute(num1, num2, fun(a, b) = a + b)
    println("result2 = $result2")

    iterate(listOf(1, 2, 3, 4, 5), fun(number) {
        if (number == 3) {
            return
        }
        println("number: $number")
    })

    val result3 = calculate(1, 3, Operator.PLUS)
    println("result3 = $result3")
}

fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

fun iterate(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}

fun calculate(num1: Int, num2: Int, operator: Operator) = operator(num1 = num1, num2 = num2)

enum class Operator(
    private val op: Char,
    val calcFun: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('*', { a, b -> a * b }),
    DIVIDE('/', { a, b ->
        if (b == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다.")
        } else {
            a / b
        }
    }),
    ;
    operator fun invoke(num1: Int, num2: Int): Int {
        return this.calcFun(num1, num2)
    }
}
