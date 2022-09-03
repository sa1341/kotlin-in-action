package chapter03

// Kotlin 확장함수 예제 String은 수신객체타입, "Kotlin"은 수신객체를 의미함.
// 확장함수는 어떤 클래스의 맴버 메서드인 것처럼 호출하는 것이 컨셉임.
// 비공개 멤버나 보호된 멤버를 사용할 수 없음
fun String.lastChar(): Char = get(length - 1) // 본문에 this는 생략가능함.

fun main(args: Array<String>) {
    println("Kotlin".lastChar())

    val list = listOf("args", args)
    println(list)

    val member = Member("jean", 31)
    member.getHobby()
}

data class Member(
    val userName: String,
    val age: Int
)

fun Member.getHobby() = println("$userName - 농구")