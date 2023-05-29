package chapter04

class MyGeneric<out T>(
    val t: T
)

class Bag<T> {
    fun saveAll(
        to: MutableList<in T>,
        from: MutableList<T>
    ) {
        to.addAll(from)
    }
}

fun main() {
    // 제네릭을 사용한 클래스의 인스턴스를 만들려면 타입아규먼트를 제공
    val generics = MyGeneric<String>("테스트")
    val charGenerics: MyGeneric<CharSequence> = generics

    val bag = Bag<String>()
    bag.saveAll(mutableListOf<CharSequence>("1", "2"), mutableListOf<String>("3", "4"))

    // 변수의 타입에 제네릭을 사용한 경우
    val  list1: MutableList<String> = mutableListOf()

    // 타입 아규먼트를 생성자에서 추가
    val list2 = mutableListOf<String>()

    val list3: List<*> = listOf<String>("테스트")
    val list4: List<*> = listOf<Int>(1, 2, 3, 4)

    // PECS(Producer extends, Consumer Super)
}