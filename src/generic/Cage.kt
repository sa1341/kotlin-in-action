package generic

fun main() {
    // 공변
    val goldFishCage = Cage2<GoldFish>()
    goldFishCage.put(GoldFish("잉어"))

    val fishCage = Cage2<Fish>()
    fishCage.moveFrom(goldFishCage)

    // 반공변
    val fishCage2 = Cage2<Fish>()
    val goldFish2 = Cage2<GoldFish>()
    goldFish2.put(GoldFish("금붕어"))

    goldFish2.moveTo(fishCage2)


    val fishCage3 = Cage3<Fish>()
    val animalCage: Cage3<Animal> = fishCage3

    val cage5 = Cage5(mutableListOf(Eagle(), Sparrow()))
    cage5.printAfterSorting()
}

class Cage {
    private val animal: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animal.first()
    }

    fun put(animal: Animal) {
        this.animal.add(animal)
    }

    fun moveFrom(cage: Cage) {
        this.animal.addAll(cage.animal)
    }
}

/**
 * 사용지점 변성 예제코드
 */
class Cage2<T : Any> {
    private val animal: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animal.first()
    }

    fun put(animal: T) {
        this.animal.add(animal)
    }

    fun moveFrom(otherCage: Cage2<out T>) {
        this.animal.addAll(otherCage.animal)
    }

    fun moveTo(otherCage: Cage2<in T>) {
        otherCage.animal.addAll(this.animal)
    }
}

/**
 * 선언 지점 변성 예제
 */
class Cage3<out T> {
    private val animal: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animal.first()
    }

    fun getAll(): List<T> {
        return this.animal
    }
}

class Cage4<in T> {
    private val animal: MutableList<T> = mutableListOf()

    fun put(animal: T) {
        this.animal.add(animal)
    }

    fun putAll(animals: List<T>) {
        this.animal.addAll(animals)
    }
}

class Cage5<T>(
    private val animal: MutableList<T> = mutableListOf(),
) where T : Animal, T : Comparable<T> {

    fun printAfterSorting() {
        this.animal.sorted()
            .map { it.name }
            .let(::println)
    }

    fun getFirst(): T {
        return animal.first()
    }

    fun put(animal: T) {
        this.animal.add(animal)
    }

    fun getAll(): List<T> {
        return this.animal
    }

    fun moveFrom(otherCage: Cage5<T>) {
        this.animal.addAll(otherCage.animal)
    }

    fun moveTo(otherCage: Cage5<in T>) {
        otherCage.animal.addAll(this.animal)
    }
}

abstract class Bird(
    name: String,
    private val size: Int,
) : Animal(name), Comparable<Bird> {

    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }
}

class Sparrow : Bird("참새", 100)
class Eagle : Bird("독수리", 500)

inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean {
    return this.any { it is T }
}

fun <T> List<T>.hasIntersection(other: List<T>): Boolean {
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}

open class CageV1<T : Animal> {
    open fun addAnimal(animal: T) {
    }
}

class CageV2<T : Animal> : CageV1<T>()

class GoldFishCageV2 : CageV1<GoldFish>() {
    override fun addAnimal(animal: GoldFish) {
        super.addAnimal(animal)
    }
}
