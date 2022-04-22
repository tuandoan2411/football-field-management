package view.Dialog;

import controller.AddUpdatePitchTypeDialogController;
import model.PitchType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddUpdatePitchTypeDialog extends JPanel {
    JDialog dialog;
    PitchType pitchType;
    boolean ok;
    public static final String UPDATE = "Lưu";
    public static final String ADD = "Thêm";
    private String typeDialog;

    JPanel titlePanel;

    JLabel pitchTypeIdLabel;
    JTextField pitchTypeIdTextField;
    JLabel priceLabel;
    JTextField priceTextField;

    JPanel notificationPanel;
    JLabel notificationLabel;

    JPanel buttonPanel;

    AddUpdatePitchTypeDialogController addUpdatePitchTypeDialogController;

    public AddUpdatePitchTypeDialog(String typeDialog) {
        addUpdatePitchTypeDialogController = new AddUpdatePitchTypeDialogController();
        pitchType = new PitchType();
        this.typeDialog = typeDialog;
        setLayout(new GridBagLayout());
        Color addPitchTypeDialogBackground = new Color(41, 54, 63);
        setBackground(addPitchTypeDialogBackground);

        //Font and Color
        Font contentFont = new Font("SansSerif", Font.PLAIN, 14);
        Color contentColor = new Color(56, 214, 193);
        Color backgroundColorTextField = new Color(201, 201, 201);
        Color titleForeground = Color.RED;
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        Color notificationForeground = Color.YELLOW;

        pitchTypeIdLabel = new JLabel("Loại sân");
        pitchTypeIdLabel.setFont(contentFont);
        pitchTypeIdLabel.setForeground(contentColor);
        pitchTypeIdTextField = new JTextField(20);
        pitchTypeIdTextField.addKeyListener(onlyAcceptNumber());
        pitchTypeIdTextField.setBackground(backgroundColorTextField);

        priceLabel = new JLabel("Giá");
        priceLabel.setFont(contentFont);
        priceLabel.setForeground(contentColor);
        priceTextField = new JTextField(20);
        priceTextField.addKeyListener(onlyAcceptNumber());
        priceTextField.setBackground(backgroundColorTextField);

        // Title
        titlePanel = new JPanel();
        titlePanel.setBackground(addPitchTypeDialogBackground);
        JLabel titleLabel = new JLabel("LOẠI SÂN");
        titleLabel.setForeground(titleForeground);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        // Notifications
        notificationPanel = new JPanel();
        notificationPanel.setBackground(addPitchTypeDialogBackground);
        notificationLabel = new JLabel(" ");
        notificationPanel.setPreferredSize(new Dimension(450, 30));
        notificationLabel.setForeground(notificationForeground);
        notificationPanel.add(notificationLabel);

        // JButton
        buttonPanel = new JPanel();
        buttonPanel.setBackground(addPitchTypeDialogBackground);
        JButton addButton = new JButton(typeDialog);
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    PitchType oldPitch = new PitchType(pitchType.getPitchTypeId(), pitchType.getPrice());
                    pitchType.setPitchTypeId(Integer.parseInt(pitchTypeIdTextField.getText()));
                    pitchType.setPrice(Double.parseDouble(priceTextField.getText()));
                    if(typeDialog.equals(ADD)){
                        if(addUpdatePitchTypeDialogController.hasPitchType(pitchType)){
                            JOptionPane.showMessageDialog(AddUpdatePitchTypeDialog.this, "Loại sân này đã tồn tại!");
                            return;
                        }
                        JOptionPane.showMessageDialog(AddUpdatePitchTypeDialog.this, "Thêm loại sân thành công");
                    }else {
                        if(!(pitchType.getPitchTypeId() == oldPitch.getPitchTypeId()) && addUpdatePitchTypeDialogController.hasPitchType(pitchType)){
                            JOptionPane.showMessageDialog(AddUpdatePitchTypeDialog.this, "Loại sân này đã tồn tại!");
                            return;
                        }
                        JOptionPane.showMessageDialog(AddUpdatePitchTypeDialog.this, "Sửa loại sân thành công");
                    }

                    ok = true;
                    dialog.dispose();
                }
            }
        });

        JButton cancelButton = new JButton("Thoát");
        cancelButton.setBackground(Color.BLUE);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        buttonPanel.setPreferredSize(new Dimension(100, 30));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        layoutPitchTypeDialog();
    }

    private KeyListener onlyAcceptNumber() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char test = e.getKeyChar();
                if (!(Character.isDigit(test))) {
                    e.consume();
                }
            }
        };
        return keyListener;
    }

    private boolean checkInput() {
        if(pitchTypeIdTextField.getText().equals("") || priceTextField.getText().equals("")){
            notificationLabel.setText("Không được để các trường rỗng");
            return false;
        }
        return true;
    }

    private void layoutPitchTypeDialog() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 10, 5, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(titlePanel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(pitchTypeIdLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(pitchTypeIdTextField, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(priceLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(priceTextField, gc);

        gc.insets = new Insets(5, 10, 5, 10);

        gc.gridx = 0;
        gc.gridy = 13;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(notificationPanel, gc);

        gc.insets = new Insets(5, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 14;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gc);
    }

    public boolean showDialog(Component parent, String title)
    {
        ok = false;
        // locate the owner frame
        Frame owner = null;
        if (parent instanceof Frame)
            owner = (Frame) parent;
        else
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

        // if first time, or if owner has changed, make new dialog
        if (dialog == null || dialog.getOwner() != owner)
        {
            dialog = new JDialog( owner, true);
            System.out.println("show dialog " + dialog);
            dialog.add(this);
            //dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }

        // set title and show dialog
        dialog.setTitle(title);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);

        return ok;
    }

    public PitchType getPitchType(){
        return pitchType;
    }

    public void setPitchType(PitchType pitchType){
        this.pitchType.setPitchTypeId(pitchType.getPitchTypeId());
        pitchTypeIdTextField.setText(String.valueOf(pitchType.getPitchTypeId()));
        priceTextField.setText(String.valueOf(pitchType.getPrice()));
    }


}

