package exercise.nio

import exercise.io.host
import exercise.io.port
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets

fun main() {
    println("Start main")
    ServerSocketChannel.open().use {
        it.bind(InetSocketAddress(host, port))

        while (true) {
            val clientSocket = it.accept()
            val requestByteBuffer = ByteBuffer.allocateDirect(1024)
            clientSocket.read(requestByteBuffer)
            requestByteBuffer.flip()
            val requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString()

            println("request = $requestBody")

            val responseByteBuffer = ByteBuffer.wrap("This is sever".toByteArray())
            clientSocket.write(responseByteBuffer)
            clientSocket.close()
        }
    }
}