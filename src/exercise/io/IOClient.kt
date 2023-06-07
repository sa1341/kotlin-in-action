package exercise.io

import java.net.InetSocketAddress
import java.net.Socket

const val host = "localhost"
const val port = 8080

fun main() {

    Socket().use {
        runCatching {
            println("서버 소켓 연결 요청")
            it.connect(InetSocketAddress(host, port))
            val out = it.getOutputStream()

            val requestBody = "This is client"
            out.write(requestBody.toByteArray())
            out.flush()

            val inputStream = it.getInputStream()
            val responseBytes = ByteArray(size = 1024)
            inputStream.read(responseBytes)
            println("result = ${String(responseBytes).trim()}")
        }.getOrElse {
            throw RuntimeException(it)
        }
    }
}