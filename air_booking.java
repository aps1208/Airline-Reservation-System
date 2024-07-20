import java.awt.*;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



 class air_booking implements ActionListener {

     private JFrame frame;
     private JLabel sourceLabel, destinationLabel, flightNumberLabel;

     private JTextField sourceTextField, destinationTextField;//flightNumberTextField;
     private JTable flightTable;
     JButton searchButton, PayNowButton,NextButton;


     air_booking() {
         frame = new JFrame("Flight Search");
         frame.setBounds(650, 450, 900, 900);

         sourceLabel = new JLabel("Source");
         sourceLabel.setBounds(30, 90, 100, 25);

         destinationLabel = new JLabel("Destination");
         destinationLabel.setBounds(30, 150, 100, 25);
        // flightNumberLabel = new JLabel("Flight Number");
         //flightNumberLabel.setBounds(30, 280, 100, 25);
         sourceTextField = new JTextField();
         sourceTextField.setBounds(220, 90, 100, 25);
         //sourceTextField.setSize(50,50);
         destinationTextField = new JTextField();
         destinationTextField.setBounds(220, 150, 100, 25);
         //flightNumberTextField = new JTextField();
         //flightNumberTextField.setBounds(220, 280, 100, 25);


         flightTable = new JTable();
         flightTable.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"flight_no", "flight_name", "Start", "destination", "price", "class", "Time"}) {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
         });
         flightTable.setLocation(30, 480);

         searchButton = new JButton("Search");
         searchButton.addActionListener(this);
         searchButton.setBounds(100, 200, 100, 25);
         PayNowButton = new JButton("Pay Now");
         PayNowButton.addActionListener(this);
         PayNowButton.setBounds(100, 420, 100, 25);
         NextButton = new JButton("Next");
         NextButton.addActionListener(this);
         NextButton.setBounds(320, 420, 100, 25);
        /*enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource()==enterButton) {
                    passenger_details pas = new passenger_details();
                    pas.setVisible(true);

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==searchButton){
                    String from = sourceTextField.getText();
                    String to = destinationTextField.getText();

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT flight_no, flight_name, Start,destination,price,class,Time  from flight WHERE Start = '" + from + "' AND destination = '" + to + "'");

                        while (resultSet.next()) {
                            DefaultTableModel model = (DefaultTableModel) flightTable.getModel();
                            model.addRow(new Object[]{resultSet.getString("flight_no"), resultSet.getString("flight_name"), resultSet.getString("Start"), resultSet.getString("destination"), resultSet.getInt("price"), resultSet.getString("class"), resultSet.getString("Time")});
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else if (e.getSource()==enterButton) {
                    passenger_details pas = new passenger_details();
                    pas.setVisible(true);



                }
            }
        });*/


         //public void actionPerformed(ActionEvent e5) {
         //  JButton source = (JButton) e5.getSource();


         // if (e5.getSource() == enterButton) {
         // Perform action for air booking button
         // Show the next UI page for air booking
         //     new passenger_details();
         // }

         //airBookingPage.setVisible(true);

         //enterButton.addActionListener(new ActionListener() {
         //@Override
         // public void actionPerformed(ActionEvent e) {

         // }

         //  try {
         //    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123");
         //  Statement statement = connection.createStatement();
         //ResultSet resultSet = statement.executeQuery("SELECT * from flight WHERE flight_number = '" + flightNumber + "'");

         //if (resultSet.next()) {
         //  frame.dispose();
         //FlightDetails flightDetails = new FlightDetails(resultSet);
         // } else {
         //   JOptionPane.showMessageDialog(frame, "Invalid flight number.");
         //}
         //} catch (SQLException e1) {
         //  e1.printStackTrace();
         // }
         //}
         //});

        /*JPanel panel = new JPanel();
        panel.add(sourceLabel);
        panel.add(sourceTextField);
        panel.add(destinationLabel);
        panel.add(destinationTextField);
        panel.add(flightNumberLabel);
        panel.add(flightNumberTextField);
        panel.add(searchButton);
        panel.add(enterButton);*/
         frame.add(sourceLabel);
         frame.add(sourceTextField);
         frame.add(destinationLabel);
         frame.add(destinationTextField);
        // frame.add(flightNumberLabel);
         //frame.add(flightNumberTextField);
         frame.add(searchButton);
         frame.add(PayNowButton);
         frame.add(NextButton);
         //panel.setBounds();

         //frame.add(panel, BorderLayout.NORTH);
         frame.add(new JScrollPane(flightTable), BorderLayout.CENTER);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);


     }


     @Override
     public void actionPerformed(ActionEvent e) {
         if (e.getSource() == searchButton) {
             String from = sourceTextField.getText();
             String to = destinationTextField.getText();
             try {
                 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123");
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT flight_no, flight_name, Start,destination,price,class,Time  from flight WHERE Start = '" + from + "' AND destination = '" + to + "'");

                 while (resultSet.next()) {
                     DefaultTableModel model = (DefaultTableModel) flightTable.getModel();
                     model.addRow(new Object[]{resultSet.getString("flight_no"), resultSet.getString("flight_name"), resultSet.getString("Start"), resultSet.getString("destination"), resultSet.getInt("price"), resultSet.getString("class"), resultSet.getString("Time")});
                 }


             } catch (SQLException e1) {
                 e1.printStackTrace();
             }


         } else if (e.getSource() == PayNowButton) {
             new payment();
             frame.setVisible(false);

         }
         else if(e.getSource() == NextButton){
             try {
                 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "devesh@123");
                 Statement statement = connection.createStatement();

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


     }

     // }
     //else if{
     //new passenger_details();
     //air_booking.setVisible();


     //  public void main(String[] args)
     // {
        /*SwingUtilities.invokeLater(() -> {
            air_booking AGE = new air_booking();
            AGE.setVisible(true);
        });*/
     //new air_booking();
     // }


    // void setVisible(boolean b) {


    // }


     public static void main(String[] args) {
         new air_booking();


     }

     public void setVisible(boolean b) {

     }
 }







    

    

