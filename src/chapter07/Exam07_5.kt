package chapter07

/**
 * 구조 분해 선언과 component 함수 예제코드 작성
 * data 클래스의 주 생성자에 들어있는 프로퍼티에 대해서는 컴파일러가 자동으로 ComponentN 함수를 만들어줍니다.
 */
fun main(args: Array<String>) {
  val nameComponents = splitFilename("example.kt")
  println("nameComponents = $nameComponents")
}

data class NameComponents(
    val name: String,
    val extension: String
)

fun splitFilename(fullName: String): NameComponents {
    val (name, ext) = fullName.split('.', limit = 2)
    return NameComponents(name, ext)
}


