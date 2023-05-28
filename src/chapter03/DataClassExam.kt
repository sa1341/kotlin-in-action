package chapter03

data class Person(
    var name: String,
    val age: Int
)

fun main() {
    val person1 = Person(name = "jean", age = 12)
    val person2 = Person(name = "jean", age = 12)
    println(person1.toString())
    println("이름 = ${person1.component1()}, 나이 = ${person1.component2()}")
    val (name, age) = person1
    println("이름 = $name, 나이 = $age")
    println(person1 == person2)

    // data class copy 예제 코드
    println("******* copy 메서드 예제코드 *******")
    val set = hashSetOf(person1)
    println(set.contains(person1))

    val copiedPerson = person1.copy(name = "stranger")
    println(copiedPerson)
    println(set.contains(person1))
}