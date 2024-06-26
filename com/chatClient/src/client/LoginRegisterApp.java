package client;

import javax.swing.*;

import dao.UserDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginRegisterApp extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;
    private UserDAO userDAO;

    public LoginRegisterApp() {
        userDAO = new UserDAO();

        setTitle("Login and Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        // Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        // Username Field
        gbc.gridx = 1;
        userField = new JTextField(20);
        panel.add(userField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        // Password Field
        gbc.gridx = 1;
        passField = new JPasswordField(20);
        panel.add(passField, gbc);

        // Login Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        // Register Button
        gbc.gridx = 1;
        registerButton = new JButton("Register");
        panel.add(registerButton, gbc);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> login()).start();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> register()).start();
            }
        });
    }

    private void login() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        try {
            if (userDAO.validateUser(username, password)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Login successful");
                    Main.showConnectServerScreen(username);
                    this.dispose();
                });
            } else {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Invalid username or password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Login failed"));
        }
    }

    private void register() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        try {
            if (userDAO.registerUser(username, password)) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Registration successful"));
            } else {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Username already exists"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, "Registration failed"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginRegisterApp().setVisible(true));
    }
}
