package exercise.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TcpEventHandler implements EventHandler {

    private final ExecutorService executorService = Executors.newFixedThreadPool(50);
    private final Selector selector;
    private final SocketChannel clientSocket;

    public TcpEventHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.selector = selector;
        this.clientSocket = socketChannel;
        this.clientSocket.configureBlocking(false);
        this.clientSocket.register(this.selector, SelectionKey.OP_READ)
                .attach(this);
    }

    @Override
    public void handle() throws IOException {
        String requestBody = handleRequest(this.clientSocket);
        System.out.println("requestBody: " + requestBody);
        sendResponse(this.clientSocket, requestBody);
    }

    private String handleRequest(SocketChannel clientSocket) throws IOException {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        requestByteBuffer.flip();
        return StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
    }

    private void sendResponse(SocketChannel clientSocket, String requestBody) {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                String content = "received: " + requestBody;
                ByteBuffer responseByteBuffer = ByteBuffer.wrap(content.getBytes());
                clientSocket.write(responseByteBuffer);
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executorService);
    }
}
