package delegate

import kotlin.random.Random
import kotlin.system.measureTimeMillis

var fruits: MutableList<FruitInfo> = mutableListOf()

fun setUp() {
    (1..2_000_000).forEach { _ -> fruits.add(FruitInfo.random()) }
}

fun main() {
    setUp()
    val sequenceMeasuredTime = measureTimeMillis {
        fruits.asSequence()
            .filter { it.name == "포도" }
            .map { it.price }
            .take(10_000)
            .average()
    }

    val iteratorMeasuredTime = measureTimeMillis {
        fruits
            .filter { it.name == "포도" }
            .map { it.price }
            .take(10_000)
            .average()
    }

    println("Elapsed SequenceTime = $sequenceMeasuredTime")
    println("Elapsed IteratorTime = $iteratorMeasuredTime")
}

data class FruitInfo(
    val name: String,
    val price: Long,
) {
    companion object {
        private val NAME_CANDIDATES = listOf("사과", "바나나", "수박", "포도", "오렌지")
        fun random(): FruitInfo {
            val random1 = Random.nextInt(0, 5)
            val random2 = Random.nextLong(10000, 20001)
            return FruitInfo(
                name = NAME_CANDIDATES[random1],
                price = random2,
            )
        }
    }
}

