package chapter04

fun main(args: Array<String>) {
    val result = eval(Expr.Sum(Expr.Num(3), Expr.Num(5)))
    println("result = $result")
}

sealed class Expr {
    class Num(val value: Int): Expr()
    class Sum(val left: Expr, val right: Expr): Expr()
}

fun eval(e: Expr): Int =
        when (e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.right) + eval(e.left)
        }