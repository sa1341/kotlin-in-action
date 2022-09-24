package chapter09

import javax.print.attribute.standard.Destination

/**
 * interface Transform<T> {
 *   fun transform(t: T 인 위치): T (아웃 위치)
 * }
 *
 * out: 공변성
 *  - 클래스 타입 파라미터 T 앞에 out 키워드를 붙이면 클래스 안에서 T를 사용하는 메서드가
 *    아웃 위치에서만 T를 사용하게 허용하고 인 위치에서는 T를 사용하지 못하게 막습니다.
 *
 * in: 반공변성
 *  - 타입 파라미터 T를 소비하는데 사용 함.
 *
 * 무공변성: MutableList<T>
 *  - 하위타입 관계가 성립되지 않고
 *    T를 아무곳에서나 사용 가능함.
 */

fun main(args: Array<String>) {

    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()

    copyData(ints, anyItems)

    anyItems.forEach {
        println("item = $it")
    }
}


fun <T:R, R> copyData(source: MutableList<T>,
                      destination: MutableList<R>) {
    for (item in source) {
        destination.add(item)
    }
}