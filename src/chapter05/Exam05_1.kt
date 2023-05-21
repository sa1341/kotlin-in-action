package chapter05

fun salute() = println("Salute!!")

fun main(args: Array<String>) {

    /**
     * 최상위에 선언된 함수나 프로퍼티를 참조 할 수도 있습니다.
     * :: 참조를 바로 시작합니다. run 라이브러리 함수에 참조를 넘겨서 람다를 호출합니다.
     */
    run(::salute)

    /**
     * kotlin 고차 함수 예제
     */
    val func = { a: Int, b: Int ->
        a + b
    }

    val sum = highFunc(1, 3, func)
    println("sum = $sum")

    /**
     * kotlin 표준 라이브러리 메서드인 joinToString 메서드 예제코드 실습
     */
    val people = listOf(
        Person("jean", 32),
        Person("lol", 31)
    )

    val names = people.joinToString(" ") { it.name }
    println("names = $names")

    /**
     * Collection의 maxBu 메서드는 해당 요소를 람다 파라미터로 받기 때문에
     * kotlin 컴파일러가 타입 추론이 가능함. 따라서 파라미터인 Person 생략이 가능함
     */
    val getAge = { p: Person -> p.age}
    val maxPerson = people.maxBy(getAge)
    println("person = $maxPerson")

    /**
     * 제네릭을 이용한 고차 함수 예제
     */
    val printObj = { obj: Person ->
        println(obj)
    }

    val crew = Person("jean", 31)
    printLog(crew, printObj)
}

data class Person(
    val name: String,
    val age: Int
)

fun highFunc(num1: Int, num2: Int
             , sum: (Int, Int) -> Int): Int {

    return sum(num1, num2)
}

inline fun <T>printLog(obj: T, block: (T) -> Unit) {
    block(obj)
}