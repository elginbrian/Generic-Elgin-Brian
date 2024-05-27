package presentation.home_page;

import data.model.TaskModel;
import data.repository.TaskRepository;
import data.repository.UserRepository;
import presentation.addtask_page.AddTaskPage;
import presentation.login_page.LoginPage;
import util.DateTimeConverter;
import util.QueueSorting;
import util.StringToArray;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Queue;

public class HomePage extends JFrame {

    private JTable taskTable;
    private DefaultTableModel tableModel;
    private Queue<TaskModel> queue;
    private JComboBox<String> sortCriteriaComboBox;
    JPanel buttonPanel;
    JButton saveButton;

    public HomePage(Queue<TaskModel> tasks, String userId) {
        this.queue = tasks;
        setTitle("Home Page - " + StringToArray.convert(UserRepository.GetUserByID(userId))[1]);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Title", "Description", "Date Created", "Priority"};
        tableModel = new DefaultTableModel(columnNames, 0);
        taskTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(taskTable);
        add(scrollPane, BorderLayout.CENTER);

        loadTasks(this.queue);

        buttonPanel   = new JPanel();
        JButton logoutButton    = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);

        JButton addButton    = new JButton("Add Task");
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);

        JButton sortButton    = new JButton("Sort Tasks");
        sortButton.setBackground(new Color(0, 120, 215));
        sortButton.setForeground(Color.WHITE);

        JButton finishButton    = new JButton("Finish First Task");
        finishButton.setBackground(new Color(0, 120, 215));
        finishButton.setForeground(Color.WHITE);

        saveButton    = new JButton("Save Changes");
        saveButton.setBackground(new Color(136, 0, 215));
        saveButton.setForeground(Color.WHITE);
        saveButton.setVisible(false);

        String[] sortCriteria = {"Priority", "Title", "Date Created"};
        sortCriteriaComboBox = new JComboBox<>(sortCriteria);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { logout(); }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask(userId);
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { sortTaks(); }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { finishFirstTask(); }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
                saveButton.setVisible(false);
            }
        });

        buttonPanel.add(logoutButton);
        buttonPanel.add(addButton);
        buttonPanel.add(finishButton);
        buttonPanel.add(sortCriteriaComboBox);
        buttonPanel.add(sortButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadTasks(Queue<TaskModel> tasks) {
        tableModel.setRowCount(0);
        for (TaskModel task : tasks) {

            Object[] rowData = {
                    task.title,
                    task.description,
                    DateTimeConverter.convert(task.date),
                    task.priority
            };
            tableModel.addRow(rowData);
        }
    }

    private void logout(){
        LoginPage.run();
        setVisible(false);
    }

    private void addTask(String userId) {
        AddTaskPage.run(userId);
        setVisible(false);
    }

    private void sortTaks(){
        switch(sortCriteriaComboBox.getSelectedIndex()){
            case 0:
                this.queue = QueueSorting.sortByPriority(this.queue);
                break;
            case 1:
                this.queue = QueueSorting.sortByTitle(this.queue);
                break;
            case 2:
                this.queue = QueueSorting.sortByDate(this.queue);
        }
        saveButton.setVisible(true);
        loadTasks(this.queue);
    }

    private void finishFirstTask() {
        if (!this.queue.isEmpty()) {
            this.queue.poll();
            loadTasks(this.queue);
        }
        saveButton.setVisible(true);
    }

    private void saveChanges(){
        TaskRepository.PutTasks(this.queue);
    }

    public static void run(String userId) {
        Queue<TaskModel> tasks = TaskRepository.GetTasksByUserID(userId, new ArrayDeque<>());

        HomePage home = new HomePage(tasks, userId);
        home.setVisible(true);
    }
}
