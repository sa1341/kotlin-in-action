package fp.chapter2.exercise1

val exercise1 = {

    fun abs(num: Int): Int =
        if (num < 0) {
            -num
        } else {
            num
        }

    fun formatAbs(num: Int): String {
        val msg = "The absolute value of %d is %d"
        return msg.format(num, abs(num))
    }

    fun factorial(i: Int): Int {
        // 관례적으로 도우미 함수라고 칭합니다.
        tailrec fun go(n: Int, acc: Int): Int =
            if (n <= 0) {
                acc
            } else {
                go(n - 1, n * acc)
            }
        return go(i, 1)
    }
}
