package chapter05

fun main(args: Array<String>) {

    val tradeList = listOf<TradeInfo>(
        TradeInfo("20220901", "jean", 300000),
        TradeInfo("20220902", "tommy", 510000),
        TradeInfo("20220903", "alice", 520000)
    )

    val groupByTradeDate = tradeList.groupBy { it.tradeDt }

    groupByTradeDate.forEach { t, u ->
        println("key = $t")
        u.forEach { println("TradeInfo = $it") }
    }

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val books = listOf<Book>(
        Book("아프니까 청춘이다", listOf("김난도")),
        Book("TDD로 시작하기", listOf("김진", "임준영", "진민완"))
    )

    val authors = books.flatMap { it.authors }.toSet()

    authors.forEach { println("Author = $it") }
}

data class Book(
    val title: String,
    val authors: List<String>
)


data class TradeInfo(
    val tradeDt: String,
    val name: String,
    val amount: Long
)