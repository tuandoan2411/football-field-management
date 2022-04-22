package view.Frame;

import view.Login;
import view.Panel.CustomerPanel;
import view.Panel.PaymentPanel;
import view.Panel.PitchOrderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame {
    int staffId;

    JTabbedPane tabbedPane;
    PitchOrderPanel pitchOrderPanel;
    PaymentPanel paymentPanel;
    CustomerPanel customerPanel;


    public User(int staffId){
        this.staffId = staffId;
        tabbedPane = new JTabbedPane();
        pitchOrderPanel = new PitchOrderPanel(staffId);
        paymentPanel = new PaymentPanel(3);
        customerPanel = new CustomerPanel();

        tabbedPane.addTab("Đặt sân", pitchOrderPanel);
        tabbedPane.add("Thanh toán", paymentPanel);
        tabbedPane.add("Khách hàng", customerPanel);

        JPanel logoutPanel = new JPanel();
        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.setForeground(Color.ORANGE);
        logoutButton.setBackground(Color.CYAN);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });
        logoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);

        add(tabbedPane);
        add(logoutPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new User(2);
    }
}
