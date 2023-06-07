package exercise.nio

import java.net.InetSocketAddress
import java.net.Socket
import java.nio.channels.SocketChannel
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

const val host = "localhost"
const val port = 8080

fun main() {
    val futures = mutableListOf<CompletableFuture<Void>>()
    val millis = measureTimeMillis {
        for (i in 1..1000) {
            val future = CompletableFuture.runAsync {
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
            futures.add(future)
        }
        CompletableFuture.allOf(*futures.toTypedArray()).join()
    }
    println("mills = $millis")
    println("duration = ${millis / 1000.0}")
}