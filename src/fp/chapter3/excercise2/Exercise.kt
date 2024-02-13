package fp.chapter3.excercise2

sealed class List<out A> {

    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(ints: List<Int>): Int =
            when (ints) {
                is Nil -> 0
                is Cons -> ints.head + sum(ints.tail)
            }

        fun product(doubles: List<Double>): Double =
            when (doubles) {
                is Nil -> 1.0
                is Cons ->
                    if (doubles.head == 0.0) {
                        0.0
                    } else {
                        doubles.head * product(doubles.tail)
                    }
            }

        // smart casting 개념을 알아야한다.
        fun <A> tail(xs: List<A>): List<A> =
            when (xs) {
                is Cons -> xs.tail
                is Nil -> throw IllegalStateException("Nil cannot have a tail")
            }

        fun <A> setHead(xs: List<A>, x: A): List<A> =
            when (xs) {
                is Cons -> Cons(head = x, tail = xs.tail)
                is Nil -> throw IllegalStateException("Nil cannot have a tail")
            }

        fun <A> drop(l: List<A>, n: Int): List<A> =
            if (n == 0) {
                l
            } else {
                when (l) {
                    is Cons -> drop(l.tail, n - 1)
                    is Nil -> throw IllegalStateException("Nil cannot have a tail")
                }
            }

        fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> =
            when (l) {
                is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
                is Nil -> throw IllegalStateException("Nil cannot have a tail")
            }
    }
}

object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
}

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()
