package exercise.nio.reactor;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MsgCodec {
    public ByteBuffer encode(final String msg) {
        String body = "<html><body><h1>Hello, " + msg + " !</h1></body></html>";
        int contentLength = body.getBytes().length;

        String httpResponse = "HTTP/1.1 200 OK\n" +
        "Content-Type: text/html; charset=utf-8\n" +
        "Content-Length: " + contentLength + "\n\n" + body;

        return StandardCharsets.UTF_8.encode(httpResponse);
    }

    public String decode(final ByteBuffer buffer) {
        buffer.flip();
        String httpRequest = StandardCharsets.UTF_8.decode(buffer).toString().trim();
        String firstLine = httpRequest.split("\n")[0];
        String path = firstLine.split(" ")[1];
        URI uri = URI.create(path);

        String query = uri.getQuery() == null ? "" : uri.getQuery();

        Map<String, String> queryMap = Arrays.stream(query.split("&"))
                .map(s -> s.split("="))
                .filter(s -> s.length == 2)
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        return queryMap.getOrDefault("name", "world");
    }
}
