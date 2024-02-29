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

        fun <A> init(l: List<A>): List<A> =
            when (l) {
                is Cons -> if (l.tail == Nil) {
                    Nil
                } else {
                    Cons(l.head, init(l.tail))
                }
                is Nil -> throw IllegalStateException("cannot init Nil list")
            }

        fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> f(xs.head, foldRight(xs.tail, z, f))
            }

        fun sum2(ints: List<Int>): Int =
            foldRight(ints, 0) { a, b -> a + b }

        fun <A> length(xs: List<A>): Int =
            when (xs) {
                is Nil -> 0
                is Cons -> foldRight(xs, 0) { _, acc -> 1 + acc }
            }

        tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
            }

        fun sum3(ints: List<Int>): Int =
            foldLeft(ints, 0) { x, y -> x + y }

        fun product2(ints: List<Double>): Double =
            foldLeft(ints, 1.0) { x, y -> x * y }

        tailrec fun <A> startsWith(l1: List<A>, l2: List<A>) : Boolean =
            when (l1) {
                is Nil -> l2 == Nil
                is Cons -> when(l2) {
                    is Nil -> true
                    is Cons -> if (l1.head == l2.head)
                        startsWith(l1, l2)
                    else false
                }
            }

        tailrec fun <A> hasSubsequence(xs: List<A>, sub: List<A>) : Boolean =
            when (xs) {
                is Nil -> false
                is Cons ->
                    if (startsWith(xs, sub))
                        true
                    else hasSubsequence(xs.tail, sub)
            }
    }
}

object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
}

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()
