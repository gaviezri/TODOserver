import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String args[]) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8496), 0);
        server.createContext("/todo/health", new HealthHandler());
        server.createContext("/todo", new TODOHandler());
        server.createContext("/todo/size", new SizeHandler());
        server.createContext("/todo/content", new ContentHandler());
        server.start();

    }
}
