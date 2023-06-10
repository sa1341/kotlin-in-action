package exercise.nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventLoop implements Runnable {
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);
    private final ServerSocketChannel serverSocket;
    private final Selector selector;
    private final EventHandler acceptor;

    public EventLoop(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", port));
        serverSocket.configureBlocking(false);
        acceptor = new Acceptor(selector, serverSocket);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT)
                .attach(acceptor);
    }

    @Override
    public void run() {
        executorService.submit(() -> {
            while (true) {
                // 이벤트가 하나라도 완료되기 전까진 blocking 상태
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();

                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    dispatch(key); // demultiflexing
                }
            }
        });
    }

    private void dispatch(SelectionKey selectionKey) throws IOException {
        EventHandler eventHandler = (EventHandler) selectionKey.attachment();

        if (selectionKey.isReadable() || selectionKey.isAcceptable()) {
            eventHandler.handle();
        }
    }
}
