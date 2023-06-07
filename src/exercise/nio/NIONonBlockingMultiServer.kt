package exercise.nio

import exercise.io.host
import exercise.io.port
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

private val count = AtomicInteger(0)

fun main() {
    println("Start main")
    ServerSocketChannel.open().use {
        it.bind(InetSocketAddress(host, port))
        it.configureBlocking(false)

        while (true) {
            val clientSocket = it.accept()

            if (clientSocket == null) {
                TimeUnit.MILLISECONDS.sleep(100)
                println("wait accept")
                continue
            }
            CompletableFuture.runAsync {
                handleRequest(clientSocket = clientSocket)
            }
        }
    }
}

private fun handleRequest(clientSocket: SocketChannel) {
    val requestByteBuffer = ByteBuffer.allocateDirect(1024)

    while (clientSocket.read(requestByteBuffer) == 0) {
        println("Reading...")
    }

    requestByteBuffer.flip()
    val requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString()

    println("request = $requestBody")
    TimeUnit.MILLISECONDS.sleep(10)

    val responseByteBuffer = ByteBuffer.wrap("This is sever".toByteArray())
    clientSocket.write(responseByteBuffer)
    clientSocket.close()
    val count = count.incrementAndGet()
    println("count = $count")
}