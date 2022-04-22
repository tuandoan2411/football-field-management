package view.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PaymentDialog extends JPanel {
    JDialog dialog;
    boolean isPaid = false;

    public PaymentDialog(Frame frame, double price) {
        JLabel priceLabel = new JLabel("Tổng tiền: " + price);
        
        // JButton
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);
        JButton payButton = new JButton("Thanh toán");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaid = true;
                dialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Thoát");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        buttonPanel.setPreferredSize(new Dimension(100, 30));
        payButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(payButton);
        buttonPanel.add(cancelButton);
        
        setLayout(new BorderLayout());
        add(priceLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        dialog = new JDialog(frame, true);
        dialog.add(this);
        dialog.setTitle("XÁC NHẬN THANH TOÁN");
        dialog.setLocationRelativeTo(null);
        dialog.setSize(new Dimension(300, 200));
        dialog.setVisible(true);
    }

    public boolean isPaid()
    {
        return isPaid;
    }
}





