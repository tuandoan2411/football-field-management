package view.Dialog;

import controller.PitchOrderDialogController;
import view.Common.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class PitchOrderDialog extends JDialog {
    private int staffId;
    private int customerId;
    
    private JLabel startTimeLabel;
    private DateTimePicker startTimePicker;
    private JLabel minuteLabel;
    private JComboBox<Integer> minuteCmb;
    private JLabel typeLabel;
    private JComboBox<Integer> typeCmb;
    private JLabel notificationLabel;
    private JButton orderButton;
    
    private PitchOrderDialogController pitchOrderDialogController;
    

    public PitchOrderDialog(int staffId, int customerId) {
        this.staffId = staffId;
        this.customerId = customerId;
        
        startTimeLabel = new JLabel("Thời gian bắt đầu");
        startTimePicker = new DateTimePicker();
        startTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT ) );
        Date now = new Date();
        startTimePicker.setDate(now);
        startTimePicker.setResult(now);
        minuteLabel = new JLabel("Số phút");
        Integer[] minute = {60, 90, 120};
        minuteCmb = new JComboBox<>(minute);
        typeLabel = new JLabel("Loại sân");
        Integer[] types = {5, 7, 11};
        typeCmb = new JComboBox<>(types);
        orderButton = new JButton("Đặt");
        notificationLabel = new JLabel("");

        pitchOrderDialogController = new PitchOrderDialogController();

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    order();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        layoutPitchOrderDialog();
        setSize(400, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setVisible(true);
    }

    private void order() throws SQLException {
        if(checkInput()){
            System.out.println("startTimePicker.getDate() " + startTimePicker.getDate());
            boolean ordered = pitchOrderDialogController.order(staffId, customerId, startTimePicker.getDate(), (int)minuteCmb.getSelectedItem(), (int)typeCmb.getSelectedItem());
            if(ordered){
                JOptionPane.showMessageDialog(this, "Đặt thành công", "Hoàn thành đặt sân", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }else {
                notificationLabel.setText("Giờ bạn chọn đã hết sân, hãy chọn giờ khác");
            }
        }
        else{
            notificationLabel.setText("Hãy chọn lại ngày tháng năm");
        }
    }

    private boolean checkInput() {
        GregorianCalendar commitTime = new GregorianCalendar();
        commitTime.setTime(startTimePicker.getCommitTime());
        System.out.println("startTimePicker.getCommitTime() " + startTimePicker.getCommitTime());
        GregorianCalendar startTime = new GregorianCalendar();
        System.out.println("startTimePicker.getDate() " + startTimePicker.getDate());
        System.out.println("result " + startTimePicker.getResult());
        startTime.setTime(startTimePicker.getResult());

        System.out.println("checkInput() startTime " + startTime);
        System.out.println("checkInput() commitTime " + commitTime);
        return startTime.compareTo(commitTime) >= 0;
    }

    private void layoutPitchOrderDialog() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        add(startTimeLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(startTimePicker, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(minuteLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(minuteCmb, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(typeLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(typeCmb, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        add(orderButton, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        add(notificationLabel, gc);
    }

    public static void main(String[] args) {
        new PitchOrderDialog(1, 2);
    }
}
