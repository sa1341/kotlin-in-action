package fp.chapter1.exercise

import exercise.Charge
import exercise.CreditCard

val exercise1 = {
    class CreditCard {
        fun charge(price: Float): Unit = TODO()
    }

    data class Coffee(val price: Float = 2.50F)

    class Cafe {
        fun buyCoffee(cc: CreditCard): Coffee {
            val cup = Coffee()

            cc.charge(cup.price)

            return cup
        }
    }
}

val exercise2 = {
    data class Coffee(val price: Float = 2.95F)

    class CreditCard

    class Payments {
        fun charge(cc: CreditCard, price: Float): Unit = TODO()
    }

    // tag::init2[]
    class Cafe {
        fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
            val cup = Coffee()
            p.charge(cc, cup.price)
            return cup
        }
    }
    // end::init2[]
}

val exercise3 = {

    class CreditCard

    data class Coffee(val price: Float = 2.50F)

    data class Charge(val cc: CreditCard, val amount: Float)

    // tag::init3[]
    class Cafe {
        fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
            val cup = Coffee()
            return Pair(cup, Charge(cc, cup.price))
        }
    }
    // end::init3[]
}

val exercise4 = {

    class CreditCard

    data class Charge(val cc: CreditCard, val amount: Float) {

        fun combine(other: Charge): Charge =
            if (cc == other.cc) {
                Charge(cc, amount + other.amount)
            } else {
                throw Exception(
                    "Cannot combine charges to different cards",
                )
            }
    }
    // end::init3[]
}

val exercise5 = {
    class CreditCard

    data class Coffee(val price: Float = 2.50F)

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = TODO()
    }

    class Cafe {

        fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> = TODO()

        fun buyCoffees(
            cc: CreditCard,
            n: Int,
        ): Pair<List<Coffee>, Charge> {
            val purchases: List<Pair<Coffee, Charge>> =
                List(n) { buyCoffee(cc) }

            /**
             * 코틀린은 객체의 구조 분해를 허용합니다.
             * 예시1) val (left, right) = Pair(1, 2)
             * 예시2) val (_, right) = Pair(1, 2) 불필요한 값은 _를 써서 무시할 수 있습니다.
             */
                val (coffees, charges) = purchases.unzip()

            return Pair(
                coffees,
                charges.reduce { c1, c2 -> c1.combine(c2) },
            )
        }
    }
}

val exercise6 = {
    class CreditCard

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = TODO()
    }

    fun List<Charge>.coalesce(): List<Charge> =
        this.groupBy { it.cc }.values
            .map { it.reduce { a, b -> a.combine(b) } }
}

/**
 * 카드사 별로 총 청구비용을 구하기 위해서 groupBy와 reduce를 사용한 예제코드 작성
 */
fun main() {
    class CreditCard

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = Charge(cc, amount + other.amount)
    }

    val sinhan = CreditCard()
    val kakaopay = CreditCard()
    val nh = CreditCard()

    val charges: List<Charge> = buildList {
        add(Charge(sinhan, 3000F))
        add(Charge(kakaopay, 4000F))
        add(Charge(nh, 2000F))
        add(Charge(nh, 1000F))
        add(Charge(sinhan, 2000F))
    }

    val totalCharges = charges.groupBy { it.cc }.values
        .map { it.reduce { a, b -> a.combine(b) } }

    totalCharges.forEach {
        println("charge: ${it.amount}")
    }
}
