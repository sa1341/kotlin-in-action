package chapter05

fun Person.isAdult() = age >= 21

fun main(args: Array<String>) {

    val predicate = Person::isAdult
    val add = { a: Int, b: Int -> a +b }
    println(calculate(3, 4, add))

    val people = listOf<Person>(
        Person("jean", 30),
        Person("seunghee", 10)
    )

    val result = people.filter(predicate)
    result.forEach { println(it) }

    /**
     * sequence를 사용하면 컬렉션의 원소의 개수가 많고, map, filter 등 연쇄적인 연산을
     * 사용하는 경우 중간 컬렉션 생성 없이 효율적으로 한 컬렉션에서 연산 처리가 가능함.
     * 지연연산을 사용하기 때문에 최종 연산을 사용하기 전까지는 중간 연산이 실행되지 않음.
     */
    val list = people.asSequence()
        .map(Person::name)
        .filter { it.startsWith("j") }
        .toList()

    list.forEach { println("person = $it") }
}

fun calculate(a: Int, b: Int, op: (Int, Int) -> Int) = op(a, b)