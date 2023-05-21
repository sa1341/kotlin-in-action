package chapter05

fun main(args: Array<String>) {

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.toUpperCase() })

    /**
     * 컬렉션에 대해 자주 수행하는 연산으로 컬렉션의 모든 원소가 어떤 조건을 만족하는지 판단하는
     * 연산이 있는데 all, any과 대표적입니다.
     * 아래와 같이 부정을 나타내는 연산을 사용할때는 all보다 any를 사용하는게 더 낫습니다.
     */
    val list = listOf(1, 2, 3)
    println(!list.all { it == 3 })
    println(list.any { it != 3})

    val canBeInClub27 = { p:Person -> p.age >= 27 }
    val people = listOf(
        Person("jean", 31),
        Person("seunghee", 26)
    )
    /**
     * people.filter(canBeInClub27).size로도 해당 술어를 만족하는
     * 컬렉션의 요소 수를 구할 수 있지만,
     * count는 조건을 만족하는 원소의 개수만을 추적합니다.
     * 조건을 만족하는 원소를 따로 저장하지 않기 때문에 더 효율적입니다.
     */
    println("Count: ${people.count(canBeInClub27)}")
    // 술어를 만족하는 원소를 하나 찾고 싶으면 find 메서드를 사용합니다.
    println("Person: ${people.find(canBeInClub27)}")

    val tradeList = listOf<TradeInfo>(
        TradeInfo("20220901", "jean", 300000),
        TradeInfo("20220902", "tommy", 510000),
        TradeInfo("20220903", "alice", 520000)
    )

    val groupByTradeDate = tradeList.groupBy { it.tradeDt }

    groupByTradeDate.forEach { (t, u) ->
        println("key = $t")
        u.forEach { println("TradeInfo = $it") }
    }

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val books = listOf(
        Book("아프니까 청춘이다", listOf("김난도", "임준영")),
        Book("TDD로 시작하기", listOf("김진", "임준영", "진민완"))
    )

    val authors = books.flatMap { it.authors }.toSet()

    authors.forEach { println("Author = $it") }

    val flattedList = listOf(
        listOf(1, 3, 5),
        listOf(2, 4, 6)
    )
    println(flattedList.flatten())
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