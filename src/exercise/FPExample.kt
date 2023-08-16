package exercise

class Cafe {
    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> = TODO()

    fun buyCoffees(
        cc: CreditCard,
        n: Int
    ): Pair<List<Coffee>, Charge> {

        val purchase: List<Pair<Coffee, Charge>> =
            List(n) { buyCoffee(cc) }

        val (coffees, charges) = purchase.unzip()

        return Pair(
            coffees,
            charges.reduce { c1, c2 -> c1.combine(c2) }
        )
    }
}

data class Charge(
    val cc: CreditCard,
    val amount: Float
) {
    fun combine(other: Charge): Charge =
        if (cc == other.cc) {
            Charge(cc, amount + other.amount)
        } else throw Exception(
            "Cannot combine charges to different cards"
        )
}
data class Coffee(
    val name: String,
    val price: Int
)

data class CreditCard(
    val name: String
)

fun List<Charge>.coalesce(): List<Charge> =
    this.groupBy { it.cc }.values
        .map { it.reduce { a, b -> a.combine(b) } }