package chapter03

class MyClass private constructor(){
    companion object {
        val a = 1234

        fun newInstance() = MyClass()
    }
}

fun main() {
    println(MyClass.a)
    println(MyClass.newInstance())
}