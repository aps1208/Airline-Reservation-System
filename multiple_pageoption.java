import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class multiple_pageoption extends JFrame implements ActionListener {
     JButton BookingButton;
    private JButton bookingStatusButton;
    private JButton refundStatusButton;
    private JButton refundPolicyButton;

    public multiple_pageoption() {
        setTitle("Air Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        BookingButton = new JButton("Booking");
        bookingStatusButton = new JButton("Booking Status");
        refundStatusButton = new JButton("Refund Status");
        refundPolicyButton = new JButton("Refund Policy");

        // Add action listeners
        BookingButton.addActionListener(this);
        bookingStatusButton.addActionListener(this);
        refundStatusButton.addActionListener(this);
        refundPolicyButton.addActionListener(this);

        // Create a panel and add buttons to it
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(BookingButton);
        panel.add(bookingStatusButton);
        panel.add(refundStatusButton);
        panel.add(refundPolicyButton);

        // Add panel to the frame
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new multiple_pageoption();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();


        if (e.getSource() == BookingButton) {
            // Perform action for air booking button
            // Show the next UI page for air booking
            air_booking airBooking = new air_booking();
            //dispose();
            new air_booking();




             // Close the current UI page
        } else if (e.getSource() == bookingStatusButton) {
            // Perform action for booking status button
            // Show the next UI page for booking status
            booking_status bookingStatusPage = new booking_status();
            bookingStatusPage.setVisible(true);
            dispose(); // Close the current UI page
        } else if (e.getSource() == refundStatusButton) {
            // Perform action for refund status button
            // Show the next UI page for refund status
            refund_ststus refundStatusPage = new refund_ststus();
            refundStatusPage.setVisible(true);
             // Close the current UI page
        } else if (e.getSource() == refundPolicyButton) {
            // Perform action for refund policy button
            // Show the next UI page for refund policy
            refund_policy refundPolicyPage = new refund_policy();
            refundPolicyPage.setVisible(true);
            dispose(); // Close the current UI page
        }
    }
}


