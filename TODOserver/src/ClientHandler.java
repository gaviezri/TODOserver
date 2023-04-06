import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ClientHandler implements Runnable {
    public class TODO {
         enum Status {
            PENDING, LATE, DONE
        }
        private static int totalTODOScreated = 1;
        private final int id;
        private String title;
        private String content;
        private Status status;
        private int dueDate;

        public TODO(String title, String content, int dueDate) {
            id = totalTODOScreated++;
            this.title = title;
            this.content = content;
            this.dueDate = dueDate;
            status = Status.PENDING;
        }
        @Override
        public String toString() {
            return "TODO [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + ", dueDate="
                    + dueDate + "ms]";
        }
    }
    private Socket socket;
    private ArrayList<TODO> TODOs = new ArrayList<TODO>();
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Client:" + socket.toString() + " is connected");
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
