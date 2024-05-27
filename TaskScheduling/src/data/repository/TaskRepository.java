package data.repository;

import data.model.TaskModel;
import util.StringToArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Queue;

public class TaskRepository {
    private static final String route = "D:\\CODE\\Java OOP\\TaskScheduling\\src\\data\\database\\TaskTable.txt";

    public static String PostTask(TaskModel task){
        try(BufferedWriter bfWriter = new BufferedWriter(new FileWriter(route, true))){
            bfWriter.write(task.getStringArray());
            bfWriter.newLine();
            return "Insert data success";
        } catch (Exception e){
            return e.getLocalizedMessage();
        }
    }

    public static Queue<TaskModel> GetTasks(Queue<TaskModel> queue){
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(route));
            String line;

            while((line = bfReader.readLine()) != null){
                String[] arr = StringToArray.convert(line);
                queue.add(new TaskModel(arr[0], arr[1], arr[2], arr[3]));
            }

            return queue;
        } catch (Exception e){
            return queue;
        }
    }

    public static Queue<TaskModel> GetTasksByUserID(String userId, Queue<TaskModel> queue){
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(route));
            String line;

            while((line = bfReader.readLine()) != null){
                if(line.contains(userId)){
                    String[] arr = StringToArray.convert(line);
                    queue.add(new TaskModel(
                            arr[0],
                            arr[1],
                            arr[2],
                            arr[3],
                            arr[4],
                            arr[5]));
                }
            }

            return queue;
        } catch (Exception e){
            return queue;
        }
    }

    public static String PutTasks(Queue<TaskModel> queue){
        try(BufferedWriter bfWriter = new BufferedWriter(new FileWriter(route, false))) {
            for (TaskModel task : queue) {
                bfWriter.write(task.getStringArray());
                bfWriter.newLine();
            }
            return "Save data success";
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }
}
