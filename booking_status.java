import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.sql.*;
import java.util.Stack;

public class booking_status extends JFrame {

    private JTextField PNRfield;


    public booking_status() {
        // Set up the JFrame and panels
        JFrame frame = new JFrame("booking status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setBounds(250,250,820,800);
        setSize(300, 300);




        JPanel loginPanel = new JPanel(new GridLayout(5, 2));
        JTextField textField = new JTextField();
        //textField.setPreferredSize(new Dimension(200, 300));
        loginPanel.setBackground(Color.lightGray);
        loginPanel.setForeground(Color.BLACK);
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        //loginPanel.setBounds(200,200,400,600);




        JLabel PNRLabel = new JLabel("Enter PNR:");

        PNRfield = new JTextField();

        JButton eneterButton = new JButton("Enter");
        JButton logoutButton = new JButton("Logout");
        JButton previousButton = new JButton("Previous");


        eneterButton.setBackground(Color.black);
        eneterButton.setForeground(Color.pink);
        logoutButton.setBackground(Color.black);
        logoutButton.setForeground(Color.pink);
        previousButton.setBackground(Color.black);
        previousButton.setForeground(Color.pink);
        //ImageIcon imageIcon = new ImageIcon("C:\\Users\\DEVESH\\OneDrive\\Desktop\\java project\\air-india-cargo.jpg");
        //Image i1 = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        //ImageIcon i2 = new ImageIcon(i1);
        //JLabel img = new JLabel(i2);
        //loginPanel.add(img);
        ////add(i2);




        eneterButton.addActionListener(new ButtonListener());
        logoutButton.addActionListener(new ButtonListener());
        previousButton.addActionListener(new ButtonListener());

        add(loginPanel);

        loginPanel.add(PNRLabel);
        loginPanel.add(PNRfield);

        loginPanel.add(logoutButton);
        loginPanel.add(eneterButton);
        loginPanel.add(previousButton);

        //  loginPanel.add(textField);

        setContentPane(loginPanel);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                JButton clickedButton = (JButton) e.getSource();

                if (clickedButton.getText().equals("Enter")) {
                    String pnr = PNRfield.getText();


                    // Establish a database connection
                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123")) {
                        String q = "Select * from pnr where pnr='"+pnr+"'";
                        Statement stm = connection.createStatement();
                        ResultSet rs = stm.executeQuery(q);
                        // Execute login query


                        if (rs.next()) {
                            JOptionPane.showMessageDialog(booking_status.this,"Your Ticket is Already Booked");

                            //multiple_pageoption Page = new multiple_pageoption();
                           // Page.main();
                            // Close the login page
                        } else {
                            // Login failed, show an error message
                            JOptionPane.showMessageDialog(booking_status.this, "Invalid PNR");
                        }

                        rs.close();
                        stm.close();
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Show an error message
                        JOptionPane.showMessageDialog(booking_status.this, "INVALID PNR. Please try again.");
                    }


                } else if (clickedButton.getText().equals("Previous")) {
                    multiple_pageoption multiplePage = new multiple_pageoption();
                    multiplePage.setVisible(true);
                    dispose(); // Close the login page

                }
                else if(clickedButton.getText().equals("Logout")){
                    dispose();

                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            booking_status loginPage = new booking_status();
            loginPage.setVisible(true);
        });
    }
}








