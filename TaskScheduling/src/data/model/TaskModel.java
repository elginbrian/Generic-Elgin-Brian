package data.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class TaskModel {
    private String taskID = UUID.randomUUID().toString();
    private String userID;
    public String title;
    public String description;
    public String date = LocalDateTime.now().toString();
    public String priority;

    public TaskModel(
            String userID,
            String title,
            String description,
            String priority
    ){
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public TaskModel(
            String taskID,
            String userID,
            String title,
            String description,
            String date,
            String priority
    ) {
        this.taskID = taskID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getUserID() {
        return userID;
    }

    public String getStringArray(){
        String[] taskArr = {taskID, userID, title, description, date, priority};
        String taskStringArr = Arrays.toString(taskArr);

        return taskStringArr;
    }
}
