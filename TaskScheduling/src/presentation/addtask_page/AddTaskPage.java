package presentation.addtask_page;

import data.model.TaskModel;
import data.repository.TaskRepository;
import data.repository.UserRepository;
import presentation.home_page.HomePage;
import util.StringToArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPage extends JFrame {
    private JTextField titleField;
    private JTextField descriptionField;
    private JComboBox<String> priorityBox;
    private JButton saveButton;
    private JButton cancelButton;

    private TaskModel taskModel;

    public AddTaskPage(String usedId) {
        setTitle("Add Task Page - " + StringToArray.convert(UserRepository.GetUserByID(usedId))[1]);
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 63, 65));
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setForeground(Color.WHITE);
        titleField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setForeground(Color.WHITE);
        descriptionField = new JTextField();

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setForeground(Color.WHITE);
        String[] priorities = {"Low", "Medium", "High"};
        priorityBox = new JComboBox<>(priorities);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0, 120, 215));
        saveButton.setForeground(Color.WHITE);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(priorityLabel);
        panel.add(priorityBox);
        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskRepository.PostTask(new TaskModel(
                        usedId,
                        titleField.getText(),
                        descriptionField.getText(),
                        priorities[priorityBox.getSelectedIndex()]
                ));
                setVisible(false);
                HomePage.run(usedId);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomePage.run(usedId);
            }
        });
    }

    public static void run(String userId) {
        AddTaskPage page = new AddTaskPage(userId);
        page.setVisible(true);
    }
}

