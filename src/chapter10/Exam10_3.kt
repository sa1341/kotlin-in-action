package chapter10

import io.kotest.matchers.shouldBe
import java.math.BigDecimal

fun main() {
    // given
    val number = BigDecimal.TEN

    // when
    val result = kotlinScopeFunction(number) {
        add(BigDecimal.ONE)
    }

    // then
    result shouldBe BigDecimal(11)
}

private inline fun kotlinScopeFunction(
    number: BigDecimal,
    block: BigDecimal.() -> BigDecimal,
): BigDecimal {
    return number.block()
}
