package presentation.login_page;

import data.model.TaskModel;
import data.model.UserModel;
import data.repository.TaskRepository;
import data.repository.UserRepository;
import presentation.home_page.HomePage;
import presentation.register_page.RegisterPage;
import util.StringToArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends JFrame {
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JLabel messageLabel;
    private JLabel switchLabel;
    private JPanel panel;

    public LoginPage() {
        setTitle("Login Page");
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

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 160, 80, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);

        resetButton = new JButton("Register");
        resetButton.setBounds(190, 160, 100, 30);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(0, 215, 4));
        resetButton.setForeground(Color.WHITE);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(50, 200, 300, 25);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        switchLabel = new JLabel("Belum punya akun? Klik Register", SwingConstants.CENTER);
        switchLabel.setBounds(50, 220, 300, 25);
        switchLabel.setForeground(Color.WHITE);
        switchLabel.setFont(new Font("Arial", Font.BOLD, 14));


        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(resetButton);
        panel.add(messageLabel);
        panel.add(switchLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());

                String result = UserRepository.LoginUser(username, password);
                String[] resultArr = StringToArray.convert(result);

                if (result.equals("NOT FOUND")) {
                    messageLabel.setText("Username atau Password Salah");
                } else if (result.equals("ERROR")){
                    messageLabel.setText("Username atau Password Salah");
                } else {
                    messageLabel.setText("Login Berhasil");
                    HomePage.run(resultArr[0]);
                    setVisible(false);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPage.run();
                setVisible(false);
            }
        });

    }


    public static void run() {
        LoginPage page = new LoginPage();
        page.setVisible(true);
    }
}
