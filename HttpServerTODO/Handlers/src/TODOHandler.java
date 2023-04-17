import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Map;

public class TODOHandler extends basicHandler implements HttpHandler {
    private static final File TODO_PATH  = new File("./TODOs");
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        String method = exchange.getRequestMethod();
        try {
            switch (method) {
                case "POST":
                    response = handlePost(exchange);
                    break;
                case "PUT":
                    response = handlePut(exchange);
                    break;
                case "DELETE":
                    response = handleDelete(exchange);
                    break;
                default:
                    response = GenerateResponse("Method Not Allowed", true);
                    exchange.sendResponseHeaders(409, response.length());
                    break;
            }
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        catch (Exception e)
        {
            response = GenerateResponse("Internal Server Error", true);
            exchange.sendResponseHeaders(500, response.length());
            OutputStream os = exchange.getResponseBody();
            os.close();
        }
    }

    private String handlePost(HttpExchange exchange) throws IOException
    {
        Map<String, String> data = ReadRequest(exchange);
        if (TODO.isTitleInvalid(data.get("title")))
        {
            String response = GenerateResponse(String.format("TODO with the title %s already exists in the system",
                                                             data.get("title")),
                                          true);
            exchange.sendResponseHeaders(409, response.length());
            return response;
        }
        else if (utils.isDueDateInvalid(data.get("dueDate")))
        {
            String response = GenerateResponse("Can't create new TODO that is due date is in the past", true);
            exchange.sendResponseHeaders(409, response.length());
            return response;
        }
        else
        {
            TODO todo = new TODO(Long.parseLong(data.get("dueDate")), data.get("title"), data.get("content"));
            String response = GenerateResponse(TODO.getTotalTODOsInSystem().toString(), false);
            exchange.sendResponseHeaders(200, response.length());
            return response;
        }
    }

    private String handlePut(HttpExchange exchange) throws IOException
    {
        Map<String, String> request = ReadRequest(exchange);
        String response;
        if (Integer.parseInt(request.get("id")) > TODO.getTotalTODOsInSystem() ||
                TODO.getTODObyId(Integer.parseInt(request.get("id"))) == null)
        {
            response = GenerateResponse("No such TODO with id " + request.get("id"), true);
            exchange.sendResponseHeaders(404, response.length());
        }
        else if (!TODO.Status.isCorrect(request.get("status")))
        {
            response = GenerateResponse("Bad Request", true);
            exchange.sendResponseHeaders(400, response.length());
        }
        else
        {
            TODO todo = TODO.getTODObyId(Integer.parseInt(request.get("id")));
            TODO.Status oldstatus = todo.getStatus();
            todo.setStatus(TODO.Status.valueOf(request.get("status").toUpperCase()));
            response = GenerateResponse(oldstatus.name(), false);
            exchange.sendResponseHeaders(200, response.length());
        }
        return response;
    }

    private String handleDelete(HttpExchange exchange) throws IOException
    {
        Map<String, String> request = ReadRequest(exchange);
        String response;
        if (Integer.parseInt(request.get("id")) > TODO.getTotalTODOsInSystem() ||
                TODO.getTODObyId(Integer.parseInt(request.get("id"))) == null)
        {
            response = GenerateResponse("no such TODO with id " + request.get("id"), true);
            exchange.sendResponseHeaders(404, response.length());
        }
        else
        {
            TODO.allTODOs.set(Integer.parseInt(request.get("id")) - 1, null);
            response = GenerateResponse(TODO.getTotalTODOsInSystem().toString(), false);
            exchange.sendResponseHeaders(200, response.length());
        }

        return response;

    }




}
