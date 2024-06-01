package delegate

interface Fruit {
    val name: String
    val color: String
    fun bite()
}

class Apple : Fruit {
    override val name: String
        get() = "빨간색 사과"
    override val color: String
        get() = "빨간색"

    override fun bite() {
        println("빨간색 사과를 아삭아삭 씹는다.")
    }
}

class GreenApple(
    private val apple: Apple,
) : Fruit by apple {

    override val color: String
        get() = "초록색"
}

fun main() {
    val greenApple = GreenApple(Apple())
    println("name = ${greenApple.name}")
    println("color = ${greenApple.color}")
}
