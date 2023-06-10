package exercise.nio.reactor;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements EventHandler {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void handle() throws IOException {
        SocketChannel clientSocket = serverSocketChannel.accept();
        System.out.println("client: " + clientSocket);
        new HttpEventHandler(selector, clientSocket);
    }
}
