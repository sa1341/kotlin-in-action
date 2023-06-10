package exercise.nio.reactor;

import java.io.IOException;

public interface EventHandler {
    void handle() throws IOException;
}
