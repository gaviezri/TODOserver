import java.io.IOException;
import java.util.List;

public class utils {
    public static boolean isDueDateInvalid(String dueDate) throws IOException {
        return Long.parseLong(dueDate) < System.currentTimeMillis();
    }

}
