package chapter09

import kotlin.reflect.KClass

interface FiledValidator<in T> {
    fun validate(input: T): Boolean
}

class DefaultValidator: FiledValidator<String> {
    override fun validate(input: String) = input.isEmpty()
}


fun main(args: Array<String>) {
    val validators = mutableMapOf<KClass<*>, FiledValidator<*>>()
    validators[String::class] = DefaultValidator()
    val stringValidator = validators[String::class] as FiledValidator<String>
    println(stringValidator.validate("hello"))

    Validators.registerValidator(String::class, stringValidator)
    val validator = Validators[String::class]
    println(validator.validate(""))
}

object Validators {

    /**
     * 제네릭 클래스의 타입 인자가 어떤 타입인지 정보가 없거나 타입 인자가 어떤
     * 타입인지가 중요하지 않을 때 스타 프로젝션(*) 구문을 사용할 수 있습니다.
     */

    private val validators =
        mutableMapOf<KClass<*>, FiledValidator<*>>()

    fun <T: Any> registerValidator(
        kClass: KClass<T>, filedValidator: FiledValidator<T>
    ) {
        validators[kClass] = filedValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T: Any> get(kClass: KClass<T>): FiledValidator<T> {
        return validators[kClass] as? FiledValidator<T>
            ?: throw IllegalArgumentException(
                "No validator for ${kClass.simpleName}")
    }
}


