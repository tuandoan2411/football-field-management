package view.Frame;

import view.Login;
import view.Panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    JTabbedPane tabbedPane;
    StaffPanel staffPanel;
    PitchPanel pitchPanel;
    PitchTypePanel pitchTypePanel;

    public Admin(){
        tabbedPane = new JTabbedPane();
        staffPanel = new StaffPanel();
        pitchPanel = new PitchPanel();
        pitchTypePanel = new PitchTypePanel();

        tabbedPane.addTab("Nhân viên", staffPanel);
        tabbedPane.add("Sân", pitchPanel);
        tabbedPane.add("Loại sân", pitchTypePanel);

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
        tabbedPane.setBackground(new Color(134, 234, 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(900, 500));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Admin();
    }
}
