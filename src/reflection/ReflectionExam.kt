package reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.cast
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation

fun main() {
    val kClass: KClass<Reflection> = Reflection::class

    val ref = Reflection()
    val kClass2: KClass<out Reflection> = ref::class

    kClass.java // Class<Reflection >
    kClass.java.kotlin // KClass<Reflection>

    // inner class
    // inline function

    // KType, 타입을 표현. Int?
    val kType: KType = GoldFish::class.createType()
    println("kType = $kType")

    val goldFish = GoldFish("금붕이")
    goldFish::class.members
        .firstOrNull { it.name == "print" }?.call(goldFish)
        ?: IllegalArgumentException("해당 메서드는 존재하지 않습니다.")

    executeAll(Reflection())

    val callable = ::add
    println("result = ${callable(1, 3)}")
}

fun add(a: Int, b: Int) = a + b

@Target(AnnotationTarget.CLASS)
annotation class Executable

@Executable
class Reflection() {
    fun a() {
        println("A 입니다.")
    }

    fun b(n: Int) {
        println("B 입니다.")
    }
}
fun executeAll(obj: Any) {
    val kClass: KClass<out Any> = obj::class
    if (!kClass.hasAnnotation<Executable>()) {
        return
    }

    val callableFunctions = kClass.members.filterIsInstance<KFunction<*>>()
        .filter { it.returnType == Unit::class.createType() }
        .filter { it.parameters.size == 1 && it.parameters[0].type == kClass.createType() }


    callableFunctions.forEach { function ->
        function.call(obj) // obj가 생성자를 가지지 않은 클래스인 경우에는 kClass.createInstance() 사용가능함.
    }
}

class GoldFish(val name: String) {
    fun print() = println("금붕어 이름은 $name 입니다.")
}

fun castToGoldFish(obj: Any): GoldFish {
    return GoldFish::class.cast(obj)
}

