package chapter05

fun main(args: Array<String>) {

    val func = { a: Int, b: Int ->
        a + b
    }

    val sum = highFunc(1, 3, func)
    println("sum = $sum")

    val people = listOf(Person("jean", 32), Person("lol", 31))

    val names = people.joinToString(" ") { it.name }

    println("names = $names")

    val getAgpe = { p: Person -> p.age}
    val maxPerson = people.maxBy(getAgpe)
    println("person = $maxPerson")
}

data class Person(
    val name: String,
    val age: Int
)

fun highFunc(num1: Int, num2: Int
             , sum: (Int, Int) -> Int): Int {

    return sum(num1, num2)
}