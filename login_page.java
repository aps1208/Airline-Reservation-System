import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login_page extends JFrame {

    private JTextField EmailField;
    private JPasswordField passwordField;

    public login_page() {
        // Set up the JFrame and panels
        JFrame frame = new JFrame("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setSize(300, 300);
        JTextField textField = new JTextField();


        JPanel loginPanel = new JPanel(new GridLayout(3, 4));
        frame.setBackground(Color.CYAN);


        JLabel usernameLabel = new JLabel("EmailID:");
        JLabel passwordLabel = new JLabel("Password:");
        EmailField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Signup");
        loginButton.setForeground(Color.PINK);
        signupButton.setForeground(Color.PINK);
        loginButton.setBackground(Color.WHITE);
        signupButton.setBackground(Color.WHITE);


        loginButton.addActionListener(new ButtonListener());
        signupButton.addActionListener(new ButtonListener());

        loginPanel.add(usernameLabel);
        loginPanel.add(EmailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(signupButton);

        setContentPane(loginPanel);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                JButton clickedButton = (JButton) e.getSource();
                if (clickedButton.getText().equals("Login")) {
                    String username = EmailField.getText();
                    String password = new String(passwordField.getPassword());

                    // Establish a database connection
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123")) {
                        // Execute login query
                        PreparedStatement statement = connection.prepareStatement("SELECT * FROM passenger_details WHERE e_mail = ? AND password = ?");
                        statement.setString(1, username);
                        statement.setString(2, password);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            // Login successful, open the new UI page
                            multiple_pageoption Page = new multiple_pageoption();
                            Page.setVisible(true);
                            // Close the login page
                        } else {
                            // Login failed, show an error message
                            JOptionPane.showMessageDialog(login_page.this, "Invalid username or password.");
                        }
                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Show an error message
                        JOptionPane.showMessageDialog(login_page.this, "Login failed. Please try again.");
                    }


                } else if (clickedButton.getText().equals("Signup")) {
                    register_page signupPage = new register_page();
                    signupPage.setVisible(true);
                    dispose(); // Close the login page

                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login_page loginPage = new login_page();
            loginPage.setVisible(true);
        });

    }
}
