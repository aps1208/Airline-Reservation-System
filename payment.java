import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class payment extends JFrame implements ActionListener {

    private JTextField cardNumberField;
    private JTextField cardTypeField;
    private JPasswordField cvvField;
    private JButton payButton;
    private JButton logoutButton;


    public payment() {
        setTitle("Payment Page");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

       // JLabel nameLabel = new JLabel("Name:");
        //nameField = new JTextField();
        //JLabel ageLabel = new JLabel("Age:");
        //ageField = new JTextField();
        //JLabel genderLabel = new JLabel("gender:");
        //genderField = new JTextField();
        //JLabel emailidLabel = new JLabel("Email-ID:");
        //emailidField = new JTextField();

        JLabel cardNumberLabel = new JLabel("Card Number(only numeric):");
        cardNumberField = new JTextField();
        cardNumberField.addKeyListener(new IntegerKeyListener());

        JLabel cardTypeLabel = new JLabel("Card Type:");
        cardTypeField = new JTextField();

        JLabel cvvLabel = new JLabel("CVV(only numeric):");
        cvvField = new JPasswordField();
        cvvField.addKeyListener(new IntegerKeyListener());

        //JTextArea textArea = new JTextArea("PAYMENT = $10000");
        //textArea.setEditable(false); // Set the text area as read-only







        payButton = new JButton("Submit");
        payButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);


       // add(nameLabel);
        //add(nameField);
        //add(ageLabel);
        //add(ageField);
        //add(genderLabel);
        //add(genderField);
        //add(emailidLabel);
        //add(emailidField);
        add(cardNumberLabel);
        add(cardNumberField);
        add(cardTypeLabel);
        add(cardTypeField);
        add(cvvLabel);
        add(cvvField);
        add(new JLabel()); // Empty label for spacing
        add(payButton);
        add(logoutButton);



        setVisible(true);
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

    public void main() {
        new payment();
    }
    /* private void showPaymentSuccess(double amount) {
         JFrame successFrame = new JFrame("Payment Successful");
         successFrame.setSize(300, 200);
         successFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         successFrame.setLayout(new FlowLayout());

         JLabel successLabel = new JLabel("Payment Successful!");
         JLabel amountLabel = new JLabel("Payment Amount: $" + amount);

         successFrame.add(successLabel);
         successFrame.add(amountLabel);

         successFrame.setVisible(true);
     }*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
           // String name = nameField.getText();
            //int age = Integer.parseInt(ageField.getText());
            //String gender = genderField.getText();
            //String email = emailidField.getText();
            String cardNumber = cardNumberField.getText();
            String cardType = cardTypeField.getText();
            String cvv = cvvField.getText();
            String pnr = generatePNR();
            if(cardNumber.isEmpty() || cvv.isEmpty() || cardType.isEmpty()){

                JOptionPane.showMessageDialog(null,"All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
               // JOptionPane.showMessageDialog(null, "PAYMENT SUCCESFULL!");
                // Clear the fields
                cardNumberField.setText("");
                cvvField.setText("");
                cardNumberField.setText("");
                //EmailField.setText("");
               // passwordField.setText("");
            }

           storeData( pnr);

            JOptionPane.showMessageDialog(null, "Payment successful! PNR: " + pnr);

            // Close the payment page after successful payment
            dispose();
        } else if (e.getSource() == logoutButton) {
            dispose();

        }


    }

   // private void storeData(String pnr) {
     //   storeData(pnr);



    private String generatePNR() {
        // Generate a random PNR (Passenger Name Record) number
        return "PNR" + (int) (Math.random() * 100000);
    }
    private void storeData(String pnr) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123");

            // Create the SQL statement
            String sql = "insert into  pnr VALUES ('"+pnr+"')";

            // Prepare the statement
            PreparedStatement pstmt = conn.prepareStatement(sql);
           // pstmt.setString(1,pnr);
           // pstmt.setString(1, name);
            //pstmt.setInt(2, age);
            //pstmt.setString(3, gender);
            //pstmt.setString(4, email);
            //pstmt.setString(5, pnr);

            // Execute the statement
            pstmt.executeUpdate();

            // Close the statement and database connection
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String amount = "1000"; // Replace with your actual payment amount
                payment paymentPage = new payment();
                paymentPage.setVisible(true);
            }
        });
    }


}




