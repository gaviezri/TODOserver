import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static class Server {
        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(8496);
                System.out.println("Server started");
                while (true) {
                    new Thread(new ClientHandler(serverSocket.accept())).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
