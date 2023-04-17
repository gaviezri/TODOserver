import java.util.ArrayList;

public class TODO {
    public enum Status {
        PENDING, DONE, LATE;
        public static boolean isCorrect(String status) {
            return PENDING.equals(status) || DONE.equals(status) || LATE.equals(status);
        }
    }
    public static ArrayList<TODO> allTODOs;
    private static int totalTODOsInSystem = 0;
    // attributes
    private final int id;
    private String title;
    private String content;
    private Status status = Status.PENDING;
    private long dueDate;
    private String path;



    public TODO(long dueDate, String title, String content) {
        id = ++totalTODOsInSystem;
        this.dueDate = dueDate;
        this.title = title;
        this.content = content;
        allTODOs.add(this);
    }

    public int getId() {
        return id;
    }

    public static TODO getTODObyId(int id) {
        return allTODOs.get(id - 1);
    }
    public static Integer getTotalTODOsInSystem() {
        return totalTODOsInSystem;
    }
    public static boolean isTitleInvalid(String title) {
        for (TODO todo : allTODOs) {
            if (todo.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
    public static String getCountByStatus(String status) {
        switch (status){
            case "ALL":
                return String.valueOf(totalTODOsInSystem);
            default:
                Integer qualifies = 0;
                for (TODO todo : allTODOs) {
                    if (todo.getStatus().equals(status)) {
                        qualifies++;
                    }
                }
                return qualifies.toString();
        }
    }
    public void removeTODO(int id) {
        allTODOs.set(id - 1, null);
        totalTODOsInSystem--;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Status getStatus() {
        return status;
    }
    public long getDueDate() {
        return dueDate;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

}
