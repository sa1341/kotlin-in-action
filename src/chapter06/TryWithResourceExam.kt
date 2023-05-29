package chapter06

import java.io.FileWriter
fun main() {
    FileWriter("test.txt")
        .use {
            it.write("Hello Kotlin")
        }
}