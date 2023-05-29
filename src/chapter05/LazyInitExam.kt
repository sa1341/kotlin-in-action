package chapter05

class HelloBot {
    lateinit var greeting: String

    val greetingInitialized: Boolean
        get() = this::greeting.isInitialized

    fun sayHello() = println(greeting)

    fun printGreeting() {
        println(greeting)
    }
}

fun getHello() = "안녕하세요"
fun main() {
    // lazy 초기화는 multi thread 환경에서도 safe 합니다.
    /* val helloBot = HelloBot()
    for (i in 1 .. 5) {
        Thread {
            helloBot.sayHello()
        }.start()
    }
    */

    val test = HelloBot()
    if(!test.greetingInitialized) {
        test.greeting = "하이요"
    }

    test.printGreeting()
}