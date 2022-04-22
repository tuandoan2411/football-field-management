package view.Panel;

import view.Listener.CommonAddListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CommonAddPanel extends JPanel {
    private JButton addButton;
    private CommonAddListener commonAddListener;


    public CommonAddPanel(String nameButton){
        addButton = new JButton(nameButton);
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commonAddListener.add();
            }
        });

        this.add(addButton);
        setBackground(new Color(200, 100, 150));
    }

    public void setCommonAddListener(CommonAddListener commonAddListener){
        this.commonAddListener = commonAddListener;
    }
}