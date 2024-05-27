package util;

import data.model.TaskModel;

import java.util.*;

public class QueueSorting {
    public static Queue<TaskModel> sortByPriority(Queue<TaskModel> taskQueue) {
        List<TaskModel> taskList = new ArrayList<>(taskQueue);

        taskList.sort(new Comparator<TaskModel>() {
            private final List<String> priorityOrder = Arrays.asList("High", "Medium", "Low");

            @Override
            public int compare(TaskModel t1, TaskModel t2) {
                return Integer.compare(priorityOrder.indexOf(t1.priority), priorityOrder.indexOf(t2.priority));
            }
        });

        System.out.println("Sorted tasks:");
        for (TaskModel task : taskList) {
            System.out.println(task.title + " - " + task.priority);
        }

        return new ArrayDeque<>(taskList);
    }

    public static Queue<TaskModel> sortByTitle(Queue<TaskModel> taskQueue){
        List<TaskModel> taskList = new ArrayList<>(taskQueue);
        taskList.sort(Comparator.comparing(TaskModel::getTitle));

        return new ArrayDeque<>(taskList);
    }

    public static Queue<TaskModel> sortByDate(Queue<TaskModel> taskQueue){
        List<TaskModel> taskList = new ArrayList<>(taskQueue);
        taskList.sort(Comparator.comparing(TaskModel::getDate));

        return new ArrayDeque<>(taskList);
    }
}
