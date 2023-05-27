package chapter02

enum class PaymentStatus(
    val label: String
): Payable {
    UNPAID("미지급") {
        override fun isPayable() = true
    },
    PAID("지급완료") {
        override fun isPayable() = false
    },
    FAILED("지급실패") {
        override fun isPayable() = false
    },
    RETURNED("환불") {
        override fun isPayable() = false
    }; // 메서드 정의를 위해서 세미콜론을 선언해줘야 합니다.
}

interface Payable {
    fun isPayable(): Boolean
}

fun main() {
    println(PaymentStatus.UNPAID.isPayable())
    if (PaymentStatus.UNPAID.isPayable()) {
        println("결제 가능한 상태")
    }

    val paymentStatus = PaymentStatus.valueOf("PAID")
    println(paymentStatus.label)

    if (paymentStatus == PaymentStatus.PAID) {
        println("결제 완료 상태 ")
    }

    for (status in PaymentStatus.values()) {
        println("[$status](${status.label}) : ${status.ordinal}")
    }
}
