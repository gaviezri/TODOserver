import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class SizeHandler extends basicHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> request = ReadRequest(exchange);
        String response;
        if (! TODO.Status.isCorrect(request.get("status"))
                &&
                (! request.get("status").equals("ALL"))) {
            response = GenerateResponse("bad request", true);
            exchange.sendResponseHeaders(400, response.length());
        }
        else {
            response = TODO.getCountByStatus(request.get("status"));
            exchange.sendResponseHeaders(200, response.length());
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}

