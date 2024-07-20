import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class refund_ststus extends JFrame {

    private JTextField PNRfield;


    public refund_ststus() {
        // Set up the JFrame and panels
        JFrame frame = new JFrame("refund status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Refunnd Status");
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
                        // Execute login query
                        PreparedStatement statement = connection.prepareStatement( "Select * from pnr where pnr="+pnr);
                        statement.setString(1, pnr);

                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(refund_ststus.this,"YOUR AMOUNT WILL REFUNDED IN 3 Days");

                            //multiple_pageoption Page = new multiple_pageoption();
                            // Page.main();
                            // Close the login page
                        } else {
                            // Login failed, show an error message
                            JOptionPane.showMessageDialog(refund_ststus.this, "Invalid PNR");
                        }


                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Show an error message
                        JOptionPane.showMessageDialog(refund_ststus.this, "INVALID PNR. Please try again.");
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
            refund_ststus loginPage = new refund_ststus();
            loginPage.setVisible(true);
        });
    }
}


