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
class Cage2<T> {
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