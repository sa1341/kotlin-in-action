package chapter04

fun String.first() = this[0]

fun String.addFirst(char: Char) = char + this.substring(0)

class MyClass {
    fun printMessage() = println("클래스 출력")
}

fun MyClass?.printNullOrNotNull() {
    if (this == null) println("널인 경우에만 출력")
    else println("널이 아닌 경우에만 출력")
}

fun MyClass.printMessage() = println("확장 출력")
fun MyClass.printMessage(message: String) = println(message)

fun main() {
    println("ABCD".first())
    println("ABCD".addFirst('Z'))
    MyClass().printMessage() // 동일한 시그니처가 존재하기 때문에 기존 보유하고 있는 메서드가 우선순위로 호출 됨(유의 필요)
    MyClass().printMessage("jean")

    var myClass: MyClass? = null
    myClass.printNullOrNotNull() // 내부적으로 null 체크를 하고 있어서 컴파일 에러가 발생하지 않음

    myClass = MyClass()
    myClass.printNullOrNotNull()
}