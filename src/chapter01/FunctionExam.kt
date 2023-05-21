package chapter01

fun main() {
    greeting()
    greeting("니하오마")
    log(message = "인포 로그") // named argument 사용
}
fun log(level: String = "INFO", message: String) {
    println("[$level]$message")
}

fun greeting(message: String = "안녕하세요~") {
    println(message)
}