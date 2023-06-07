package exercise.io

import java.net.InetSocketAddress
import java.net.ServerSocket

fun main() {
    println("Start main")
    ServerSocket().use {
        it.bind(InetSocketAddress(host, port))

        while (true) {
            val clientSocket = it.accept()
            val requestBytes = ByteArray(size = 1024)
            val inputStream = clientSocket.getInputStream()
            inputStream.read(requestBytes)
            println("request = ${String(requestBytes).trim()}")

            val out = clientSocket.getOutputStream()
            val response = "This is server"
            out.write(response.toByteArray())
            out.flush()
        }
    }
}
