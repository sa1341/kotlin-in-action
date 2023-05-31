package chapter06

import java.lang.Exception

fun getStr(): Nothing = throw Exception("예외 발생 기본 값으로 초기화")

fun main() {
    val result = runCatching {
        getStr()
    }.getOrElse {
        println(it.message)
        "기본값"
    }

    println("result = $result")

    val result2 = runCatching { getStr() }
        .getOrNull()

    println("result2 = $result2")

    val result3 = runCatching { "성공" }
        .getOrNull()

    println("result3 = $result3")

    val result4 = runCatching { getStr() }
        .getOrDefault("기본 값")

    println("result4 = $result4")

    val result5 = runCatching { getStr() }
        .getOrElse {
            println(it.message)
            "기본 값"
        }

    println("result5 = $result5")

    val result6 = runCatching { "안녕" }
        .map { "${it}하세요" }
        .getOrThrow()

    println("result6 = $result6")

    val result7 = runCatching { "안녕" }
        .mapCatching {
            throw Exception("예외")
        }.getOrDefault("기본 값")

    println("result7 = $result7")

    // recover는 복구를 위해 사용함.
    val result8 = runCatching { getStr() }
        .recoverCatching {
            throw Exception("예외") // 실패 시 복구를 위한 로직 작성 시에 사용함.
        }.getOrDefault("복구")

    println("result8 = $result8")
}