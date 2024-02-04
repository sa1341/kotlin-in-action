package fp.chapter1.model

class CreditCard {
    fun charge(price: Float): Unit = TODO()
}

data class Coffee(val price: Float = 2.50F)

fun buyCoffee(cc: CreditCard): Coffee {
    val cup = Coffee()
    cc.charge(cup.price)
    return cup
}
