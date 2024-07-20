import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class register_page extends JFrame {

    private JTextField nameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField EmailField;


    private JPasswordField passwordField;

    public register_page() {
        // Set up the JFrame and panels
        //JFrame frame = new JFrame("Sign Up Page");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign Up Page");
        setSize(300, 200);
        //JFrame frame = new JFrame();

        JPanel signUpPanel = new JPanel(new GridLayout(8, 2));
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel genderLabel = new JLabel("gender:");
        JLabel EmailLabel = new JLabel("Email-ID:");

        JLabel passwordLabel = new JLabel("Password(only numeric digits accepted):");
        nameField = new JTextField();
        ageField = new JTextField();
        genderField = new JTextField();
        EmailField = new JTextField();
        EmailField.setInputVerifier(new EmailVerifier());
        passwordField = new JPasswordField(10);
        passwordField.addKeyListener(new IntegerKeyListener());
        JButton signUpButton = new JButton("Sign Up");
        signUpPanel.setBackground(Color.orange);
        signUpPanel.setForeground(Color.BLACK);
        signUpPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        signUpButton.setBackground(Color.black);
        signUpButton.setForeground(Color.pink);

        signUpButton.addActionListener(new SignUpButtonListener());

        signUpPanel.add(nameLabel);
        signUpPanel.add(nameField);
        signUpPanel.add(ageLabel);
        signUpPanel.add(ageField);
        signUpPanel.add(genderLabel);
        signUpPanel.add(genderField);
        signUpPanel.add(EmailLabel);
        signUpPanel.add(EmailField);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);
        signUpPanel.add(signUpButton);

        setContentPane(signUpPanel);

    }
    class IntegerKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            char input = e.getKeyChar();
            if (!Character.isDigit(input)) {
                e.consume(); // Discard the input
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // Not used
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Not used
        }
    }
    private static class EmailVerifier extends InputVerifier {
        @Override
        public boolean verify(JComponent input) {
            JTextField emailTextField = (JTextField) input;
            String text = emailTextField.getText();
            return isValidEmail(text);
        }

        @Override
        public boolean shouldYieldFocus(JComponent input) {
            boolean isValid = verify(input);
            if (!isValid) {
                JOptionPane.showMessageDialog(input, "Invalid email format. Please enter a valid email address in the format of user@example.com.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            }
            return isValid;
        }

        private boolean isValidEmail(String email) {
            String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
            return email.matches(emailRegex);
        }
    }




    private class SignUpButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String age = ageField.getText();
            String Emai = EmailField.getText();
            String gender = genderField.getText();
            if(name.isEmpty() || password.isEmpty() || age.isEmpty() || Emai.isEmpty() || gender.isEmpty()){

                JOptionPane.showMessageDialog(null,"All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Sign-up successful!");
                // Clear the fields
                nameField.setText("");
                ageField.setText("");
                genderField.setText("");
                EmailField.setText("");
                passwordField.setText("");
            }


            // Establish a database connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123")) {
                // Execute sign-up query
                PreparedStatement statement = connection.prepareStatement("INSERT INTO passenger_details VALUES (?,?,?,?,?)");
                statement.setString(1, name);
                statement.setString(2, age);
                statement.setString(3, gender);
                statement.setString(4, Emai);
                statement.setString(5, password);
                statement.executeUpdate();
                statement.close();
                connection.close();

                // Show a success message to the user
                JOptionPane.showMessageDialog(register_page.this, "Sign up successful!");
            } catch (Exception ex) {
                ex.printStackTrace();
                // Show an error message to the user
                JOptionPane.showMessageDialog(register_page.this, "Sign up failed. Please try again.");
            }
        }
    }

    public void main(String[]args) {
        SwingUtilities.invokeLater(() -> {
            register_page signUpPage = new register_page();
            signUpPage.setVisible(true);
        });
    }

}




