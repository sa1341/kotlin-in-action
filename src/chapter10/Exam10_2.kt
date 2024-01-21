package chapter10

import kotlin.reflect.full.memberProperties

fun main(args: Array<String>) {
    val person = Person("jean", 31)
    val kClass = person.javaClass.kotlin

    println(kClass.simpleName)
    kClass.memberProperties.forEach { println(it.name) }
    val kFunction = person::introduce
    println(kFunction.call())
}
