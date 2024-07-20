import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class refund_policy extends JFrame implements ActionListener {
    private JTextArea statusTextArea;
    private JButton previousButton;
    private JButton logoutButton;

    public refund_policy() {
        setTitle("Refund Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create and position the components
        statusTextArea = new JTextArea("A. Award Tickets on Air India\n" +
                "\n" +
                        "If a fully unused award ticket is cancelled and submitted for refundmore than 3 Days (excluding day of departure) before the scheduled departure of the flight, 80% of the FR points used will be refunded. 20% of the points will be deducted as cancellation charge. Non-airline taxes are refundable.\n" +
                        "If fully unused award ticket cancelled and submitted for refund less than 3 Days (excluding day of departure) before the scheduled departure of the flight, the ticket is treated asNo-Show with 100% cancellation charge and no FR Points are credited to the FR account. Only non-airline taxes are refundable.\n" +
                        "B. Award Ticket on Star Alliance Carrier (other than Air India)\n" +
                        "\n" +
                        "Coupon fee (DV) INR 1000 applicable on award tickets on Star Alliance member airlines is not refundable. Coupon fee is not applicable on Air India award tickets..\n" +
                        "If a fully unused award ticket is cancelled and submitted for refundmore than 3 Days (excluding day of departure) before the scheduled departure of the flight, 80% of the FR points used will be refunded. 20% of the points will be deducted as cancellation charge. Non-airline taxes are refundable.\n" +
                        "If fully unused award ticket cancelled and submitted for refund less than 3 Days (excluding day of departure) before the scheduled departure of the flight, the ticket is treated as No-Show with 100% cancellation charge and no FR Points are credited to the FR account. Only non-airline taxes are refundable.\n" +
                        "C. Validity of FR points incase of Refund of Award tickets\n" +
                        "\n" +
                        "In case of Refund of Award/ Redemption tickets , FR points redeemed for the ticket will be re-credited (where applicable) to the FR account into the original validity bucket . This implies that only those redeemed points will be re-credited which have not expired as on date of processing refund.\n" +
                        "Expired points will not be credited.\n" +
                        "D. Partially used award tickets are not eligible for re-credit of FR Points or refund.");
        previousButton = new JButton("Previous");
        logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(logoutButton);

        // Add action listeners to the buttons
        previousButton.addActionListener(this);
        logoutButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(statusTextArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousButton) {
            multiple_pageoption page = new multiple_pageoption();
            page.setVisible(true);


            // Code to handle "Previous" button click
            // Add your logic here
        } else if (e.getSource() == logoutButton) {
            dispose();
            // Code to handle "Logout" button click
            // Add your logic here
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            refund_policy refundStatus = new refund_policy();
            refundStatus.setVisible(true);
        });
    }
}

