package chapter04

import kotlin.random.Random

fun main(args: Array<String>) {
/*    for (i in 1..100) {
        val range = Random.nextDouble(-999.99, 999.99)
        println("value = $range")
    }*/

    val input = "view_theme"

    val theme = SearchTheme.values().filter {
        it.value == input
    }.first()


    println("theme: $theme")
}

enum class SearchTheme(val value: String) {
    VIEW_THEME("view_theme"),
    SHARE_PRICE_INCREASE_THEME("share_price_increase_theme"),
    SHARE_PRICE_DECREASE_THEME("share_price_decrease_theme"),
    TRADING_VOLUME_THEME("trading_volume_theme");
}