package function

/**
 * inline 함수는 본인만 인라이닝 되는게 아니라 알 수 있는 함수 파라미터도 인라이닝 시키고 non-local return 역시 쓸 수 있게 해줍니다.
 * crossinline 키워드를 사용하면 inline 함수 호출 시 람다식으로 파라미터 전달 시
 * non-local(비지역적) 반환을 사용하지 못하도록 할 수 있습니다.
 */

inline fun repeat(
    repeat: Int,
    crossinline exec: () -> Unit,
) {
    for (i in 1..repeat) {
        exec()
    }
}
fun main() {
    repeat(3) { println("Hello World!") }
}

/**
 * inline 프로퍼티
 * uppercaseName getter 호출 시 uppercase() 함수 본문이 인라이닝 되는 개념입니다.
 */
class Person(val name: String) {
    inline val uppercaseName: String
        get() = this.name.uppercase()
}