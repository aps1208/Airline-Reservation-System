/*import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class passenger_details extends JFrame {

    private JLabel nameLabel, ageLabel, genderLabel, emailLabel;
    private JTextField nameTextField, ageTextField, genderTextField, emailTextField;
    private JButton payNowButton, previousButton,EnterButton;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public passenger_details() {
        super("Passenger Details");

        // Create labels
        nameLabel = new JLabel("Name");
        ageLabel = new JLabel("Age");
        genderLabel = new JLabel("Gender");
        emailLabel = new JLabel("Email");

        // Create textfields
        nameTextField = new JTextField(5);
        ageTextField = new JTextField(10);
        genderTextField = new JTextField(10);
        emailTextField = new JTextField(5);

        // Create buttons
        EnterButton = new JButton("Enter");
        previousButton = new JButton("Previous");
        payNowButton = new JButton("Pay Now");

        // Add listeners to buttons
        EnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get passenger details
                String name = nameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                String gender = genderTextField.getText();
                String email = emailTextField.getText();

                // Connect to database
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123");
                    statement = connection.createStatement();

                    // Get flight number
                    String flightNumber = JOptionPane.showInputDialog("Enter flight number:");

                    // Get price of flight
                   // resultSet = statement.executeQuery("SELECT price FROM flight WHERE flight_number = '" + flightNumber + "'");
                    String q2 = "Select price from flight where flight_no="+flightNumber;
                    Statement stm = connection.createStatement();
                    ResultSet set1 = stm.executeQuery(q2);
                    if (set1.next()) {
                        int price = set1.getInt("price");

                        // Display price
                        JOptionPane.showMessageDialog(null, "The price of the flight is RS"+ price);
                    } else {
                        JOptionPane.showMessageDialog(null, "The flight number is not valid.");
                    }

                    // Close connection
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                air_booking no = new air_booking();
                no.setVisible(true);
                passenger_details no1 = new passenger_details();
                no1.setVisible(false);

            }
        });
        payNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new payment();

            }
        });

        // Add components to the frame
        getContentPane().setLayout(new GridLayout(9, 3));
        getContentPane().add(nameLabel);
        getContentPane().add(nameTextField);
        getContentPane().add(ageLabel);
        getContentPane().add(ageTextField);
        getContentPane().add(genderLabel);
        getContentPane().add(genderTextField);
        getContentPane().add(emailLabel);
        getContentPane().add(emailTextField);
        getContentPane().add(payNowButton);
        getContentPane().add(previousButton);
        getContentPane().add(EnterButton);

        // Set the frame's size and location
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Set the frame's visibility
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the frame
        passenger_details frame = new passenger_details();
    }
}*/





