package presentation.register_page;

import data.model.UserModel;
import data.repository.UserRepository;
import presentation.login_page.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel messageLabel;
    private JPanel panel;

    public RegisterPage() {
        setTitle("Register Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        panel = new JPanel();
        panel.setBackground(new Color(60, 63, 65));
        panel.setLayout(null);
        add(panel);


        userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setBounds(50, 50, 100, 25);

        userTextField = new JTextField();
        userTextField.setBounds(150, 50, 200, 25);
        userTextField.setFont(new Font("Arial", Font.PLAIN, 14));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setBounds(50, 100, 100, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        loginButton = new JButton("Create Account");
        loginButton.setBounds(70, 160, 160, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 215, 4));
        loginButton.setForeground(Color.WHITE);

        resetButton = new JButton("Back");
        resetButton.setBounds(240, 160, 80, 30);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(220, 53, 69));
        resetButton.setForeground(Color.WHITE);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(50, 210, 300, 25);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));


        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(resetButton);
        panel.add(messageLabel);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                String result = UserRepository.PostUser(new UserModel(username, password));

                if (result.equals("Insert data success")) {
                    messageLabel.setText(result);
                    setVisible(false);
                    LoginPage.run();
                } else {
                    messageLabel.setText(result);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginPage.run();
            }
        });
    }

    public static void run() {
        RegisterPage page = new RegisterPage();
        page.setVisible(true);
    }
}
