package chapter06

import java.lang.IllegalArgumentException

data class Address(
    val streetAddress: String,
    val zipCode: Int,
    val city: String,
    val country: String
)

data class Company(
    val name: String,
    val address: Address?
)

data class Person(
    val name: String,
    val company: Company?
)

fun main(args: Array<String>) {

    val address = Address("Seungnam",
        130, "Jeungja", "Korea")

    val kakaopaysec = Company("Kakaopaysec", address)

    val person = Person("jeancalm", null)


    printShippingLabel(person)
}

fun printShippingLabel(person: Person) {

    val address = person.company?.address
        ?: throw IllegalArgumentException("No address")

    with(address) {
        println(streetAddress)
        println("$zipCode, $city, $country")
    }
}