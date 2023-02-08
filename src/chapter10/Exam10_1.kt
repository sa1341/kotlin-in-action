package chapter10

import kotlin.reflect.full.memberProperties

class Person(
    val name: String,
    val age: Int
) {
    fun introduce(): String = "Hello My name is $name"
}

fun main(args: Array<String>) {

    val person = Person("jean", 31)
    val kClass = person.javaClass.kotlin

    println(kClass.simpleName)
    kClass.memberProperties.forEach { println(it.name) }
    val kFunction = person::introduce

    val str = """
        |jean,
        |woody
    """.trimMargin()
    println("str = $str")

    val name = "jean, woody, sean"
    name.trimStart(',')
    println("name = $name")
}