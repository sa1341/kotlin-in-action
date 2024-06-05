package delegate

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.system.measureTimeMillis

/**
 * lateinit 변수는 컴파일러 단계에서 nullable 변수로 바뀌고, 변수에 접근하려 할 때 null이면 예외가 발생함.
 * lateinit은 primitive 타입은 사용이 불가능함.(lateinit 변수는 컴파일 시에 nullable로 변환되기 때문에..)
 */
class Person {

    @Deprecated("age를 사용하세요!", ReplaceWith("age"))
    var num: Int = 0

    var age: Int by this::num

    private lateinit var name: String

    val isLim: Boolean
        get() = this.name.startsWith("임")

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }
}

class LazyPerson {
    val name: String by lazy {
        TimeUnit.SECONDS.sleep(2)
        "임준영"
    }
}

/**
 * 위임패턴 적용, LazyInitPerson getter 호출 시, 곧바로 LazyInitProperty getter를 호출합니다.
 */
class LazyInitPerson {
    private val delegateProperty = LazyInitProperty {
        TimeUnit.SECONDS.sleep(2)
        "임준영"
    }

    val name: String
        get() = delegateProperty.value
}

class LazyInitProperty<T>(val init: () -> T) {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}

class LazyInitPropertyV2<T>(val init: () -> T) : ReadOnlyProperty<Any, T> {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}

class Person2 {
    var age: Int by Delegates.observable(20) { _, oldValue, newValue ->
        println("이전 값: $oldValue, 새로운 값: $newValue")
    }
}

class Person3 {
    val name by DelegateProvider("임준영")
    val country by DelegateProvider("한국")
}

class DelegateProvider(
    private val initValue: String
) : PropertyDelegateProvider<Any, DelegateProperty> {
    override fun provideDelegate(thisRef: Any, property: KProperty<*>): DelegateProperty {
        if (property.name != "name") {
            throw IllegalArgumentException("${property.name}은 안되요! name만 연결 가능합니다!")
        }

        return DelegateProperty(initValue)
    }
}

class DelegateProperty(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

fun main() {
    val p1 = Person()
    println(p1.num)
    println(p1.age)

    val p2 = Person2()
    p2.age = 30

    val p3 = Person3()
    println("name = ${p3.name}")

    val lazyPerson = LazyPerson()
    val elapsedTime1 = measureTimeMillis {
        println(lazyPerson.name)
    }

    println("elapsedTime1 = $elapsedTime1")

    val elapsedTime2 = measureTimeMillis {
        println(lazyPerson.name)
    }
    println("elapsedTime2 = $elapsedTime2")
}
