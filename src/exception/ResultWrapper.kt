package exception

import kotlin.reflect.KClass

fun main() {
    runCatching { logic(10) }
        .onError(AException::class, BException::class) {
            println("A 또는 B 예외가 발생했습니다. ")
        }
        .onError(CException::class) {
            println("C 예외가 발생했습니다. ")
        }
}

fun logic(n: Int) {
    when {
        n > 0 -> throw AException()
        n == 0 -> throw BException()
    }
    throw CException()
}


class AException : RuntimeException()
class BException : RuntimeException()
class CException : RuntimeException()

class ResultWrapper<T>(
    private val result: Result<T>,
    private val knownExceptions: MutableList<KClass<out Throwable>>,
) {

    fun toResult(): Result<T> {
        return this.result
    }

    fun onError(vararg exceptions: KClass<out Throwable>, action: (Throwable) -> Unit): ResultWrapper<T> {
        this.result.exceptionOrNull()?.let {
            if (it::class in exceptions && it::class !in this.knownExceptions) {
                action(it)
            }
        }
        return this
    }
}

/**
 * *예외처리*를 위한 커스텀 함수입니다.
 * - @param T 처리 결과 타입
 *
 */
fun <T> Result<T>.onError(vararg exceptions: KClass<out Throwable>, action: (Throwable) -> Unit): ResultWrapper<T> {
    exceptionOrNull()?.let {
        if (it::class in exceptions) {
            action(it)
        }
    }
    return ResultWrapper(this, exceptions.toMutableList())
}
