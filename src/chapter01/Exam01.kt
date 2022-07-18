package chapter01

data class Person(
    val name: String,
    var age: Int? = null
)

fun main(args: Array<String>) {

    val persons = listOf(Person("jean", 31),
                         Person("wan"))

    // 영희의 나이는 null, 하지만 maxBy 메서드 내부에서 엘비스 연산자로 인해 null -> 0으로 변환
    val oldest = persons.maxBy { it.age ?: 0 }

    println("나이가 가장 많은 사람: $oldest")

    val answer: Int
    answer = 42
    println("answer: $answer")

    if (answer is Int) {
        println("해당 타입은 ${answer.javaClass.typeName}")
    } else {
        println("Int 타입이 아닙니다.")
    }
}