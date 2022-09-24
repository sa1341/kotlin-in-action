package chapter04

fun main(args: Array<String>) {

    val person1 = Person
    val person2 = Person

    person1.name = "jean"
    person2.name = "lucas"

    println(person1 == person2)
    println("person1 = $person1, person2 = $person2")
    Person.name = "jeancalm"
    println(Person.goodBye())
}

interface User {
    val nickName: String
}

class PrivateUser(override val nickName: String): User

class SubScribingUser(val email: String): User {
    override val nickName: String
        get() = email.substringBefore("@")
}

// object는 싱글톤 방식으로 인스턴스를 생성함.
object Person {
    var name: String? = null

    fun goodBye(): String? = name
}