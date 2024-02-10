package fp.chapter3.excercise1

sealed class List<out A>

object Nil : List<Nothing>()

data class Cons<out A>(
    val head: A,
    val tail: List<A>,
) : List<A>()
