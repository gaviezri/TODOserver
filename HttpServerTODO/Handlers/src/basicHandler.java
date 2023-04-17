import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class basicHandler {
    public String GenerateResponse(String response, boolean Error) {
        Map<String,String> map = new HashMap();
        map.put(Error ? "errorMessage" : "result", response);
        return new Gson().toJson(map);
    }

    public Map<String, String> ReadRequest(HttpExchange exchange) {
        Gson gson = new Gson();
        // read the request body
        String content = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))
                .lines()
                .collect(Collectors.joining("\n"));
        return gson.fromJson(content, Map.class);

    }
}
