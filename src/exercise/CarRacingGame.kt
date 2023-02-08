package exercise

import java.util.*
import kotlin.random.Random

data class Car(
    val name: String,
    var position: Int = 0
) {
    init {
        require(name.length <= 5) { "자동차 이름은 5자 이하만 가능합니다.: $name" }
    }

    fun move(count: Int) {
        require(count > 0) { "이동할 회수는 0 이상이어야 합니다" }

        for (i in 1..count) {
            val randomValue = Random.nextInt(0, 10)
            if (randomValue >= 4) this.position = position.plus(1)
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (i in 1..position) builder.append("-")
        return builder.toString()
    }
}

fun main() {
    val sc = Scanner(System.`in`)
    print("경주할 자동차 이름을 입력해주세요: ")

    val carNameList = sc.nextLine()
        .replace(" ", "")
        .split(",")
        .toList()

    val cars = carNameList.map { Car(it) }

    print("시도할 회수를 입력하세요: ")
    val count = sc.nextInt()

    with(cars) {
        forEach {
            it.move(count)
            println("${it.name} : ${it.position}")
        }

        val carGroup = cars.groupBy { it.position }
        val winnerGroup = carGroup.maxBy {
            it.key
        }

        println("Winner is = ${winnerGroup?.value?.map { it.name }}")
    }
}