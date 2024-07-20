import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class t_co {
    public void main() {
        JFrame f = new JFrame("AIR JET");
        JLabel l1=new JLabel("TERMS & CONDITIONS:");
        l1.setBounds(20,20, 80,30);


        JButton b9 = new JButton("Previous");
        b9.setBounds(5,200, 80,30);
        f.add(l1);f.add(b9);
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);


    }
}
