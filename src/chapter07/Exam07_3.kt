package chapter07

fun main(args: Array<String>) {

   val name1 = "jean"
   val name2 = "jun"

    println(name1 == name2)

    val p1 = Point(10, 20)
    val p2 = Point(10, 20)

    println(p1 == p2)
    println(p1 === p2)

    val person1 = Person("Alice", "James")
    val person2 = Person("Bob", "Zibra")

    println(person1 < person2)
    println("abc" < "bac")
}

class Person(
    val firstName: String,
    val lastName: String
): Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other,
            Person::lastName, Person::firstName)
    }
}