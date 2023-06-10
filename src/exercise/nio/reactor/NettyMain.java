package exercise.nio.reactor;

import java.io.IOException;
import java.util.List;

public class NettyMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Start main");
        List<EventLoop> eventLoops = List.of(
                new EventLoop(8080),
                new EventLoop(8080)
        );
        eventLoops.forEach(EventLoop::run);
        System.out.println("End main");
    }
}
