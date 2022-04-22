package view.Dialog;

import controller.AddUpdatePitchDialogController;
import model.Pitch;
import view.Panel.PitchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddUpdatePitchDialog extends JPanel {
    JDialog dialog;
    Pitch pitch;
    boolean ok;
    public static final String UPDATE = "Lưu";
    public static final String ADD = "Thêm";
    private String typeDialog;

    JPanel titlePanel;
    
    JLabel pitchIdLabel;
    JTextField pitchIdTextField;
    JLabel pitchTypeLabel;
    JTextField pitchTypeTextField;
    
    JPanel notificationPanel;
    JLabel notificationLabel;
    
    JPanel buttonPanel;

    AddUpdatePitchDialogController addUpdatePitchDialogController;

    public AddUpdatePitchDialog(String typeDialog) {
        pitch = new Pitch();
        addUpdatePitchDialogController = new AddUpdatePitchDialogController();
        this.typeDialog = typeDialog;
        setLayout(new GridBagLayout());
        Color addPitchDialogBackground = new Color(41, 54, 63);
        setBackground(addPitchDialogBackground);

        //Font and Color
        Font contentFont = new Font("SansSerif", Font.PLAIN, 14);
        Color contentColor = new Color(56, 214, 193);
        Color backgroundColorTextField = new Color(201, 201, 201);
        Color titleForeground = Color.RED;
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        Color notificationForeground = Color.YELLOW;

        pitchIdLabel = new JLabel("Tên sân");
        pitchIdLabel.setFont(contentFont);
        pitchIdLabel.setForeground(contentColor);
        pitchIdTextField = new JTextField(20);
        pitchIdTextField.setBackground(backgroundColorTextField);

        pitchTypeLabel = new JLabel("Loại sân");
        pitchTypeLabel.setFont(contentFont);
        pitchTypeLabel.setForeground(contentColor);
        pitchTypeTextField = new JTextField(20);
        pitchTypeTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char test = e.getKeyChar();
                if (!(Character.isDigit(test))) {
                    e.consume();
                }
            }
        });
        pitchTypeTextField.setBackground(backgroundColorTextField);

        // Title
        titlePanel = new JPanel();
        titlePanel.setBackground(addPitchDialogBackground);
        JLabel titleLabel = new JLabel("THÊM SÂN");
        titleLabel.setForeground(titleForeground);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        // Notifications
        notificationPanel = new JPanel();
        notificationPanel.setBackground(addPitchDialogBackground);
        notificationLabel = new JLabel(" ");
        notificationPanel.setPreferredSize(new Dimension(450, 30));
        notificationLabel.setForeground(notificationForeground);
        notificationPanel.add(notificationLabel);

        // JButton
        buttonPanel = new JPanel();
        buttonPanel.setBackground(addPitchDialogBackground);
        JButton addButton = new JButton(typeDialog);
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    Pitch oldPitch = new Pitch(pitch.getPitchId(), pitch.getPitchType());
                    pitch.setPitchId(pitchIdTextField.getText());
                    pitch.setPitchType(Integer.parseInt(pitchTypeTextField.getText()));
                    if(typeDialog.equals(ADD)){
                        if(addUpdatePitchDialogController.hasPitch(pitch)){
                            JOptionPane.showMessageDialog(AddUpdatePitchDialog.this, "Mã sân này đã tồn tại!");
                            return;
                        }
                        if(!addUpdatePitchDialogController.hasReferencePitchType(pitch)){
                            JOptionPane.showMessageDialog(AddUpdatePitchDialog.this, "Loại sân này chưa tồn tại, bạn hãy tạo nó trước");
                            return;
                        }
                        JOptionPane.showMessageDialog(AddUpdatePitchDialog.this, "Thêm sân thành công");
                    }else {
                        if(!pitch.getPitchId().equals(oldPitch.getPitchId()) && addUpdatePitchDialogController.hasPitch(pitch)){
                            JOptionPane.showMessageDialog(AddUpdatePitchDialog.this, "Mã sân này đã tồn tại!");
                            return;
                        }
                        if(!addUpdatePitchDialogController.hasReferencePitchType(pitch)){
                            JOptionPane.showMessageDialog(AddUpdatePitchDialog.this, "Không có loại sân này!");
                            return;
                        }
                        JOptionPane.showMessageDialog(AddUpdatePitchDialog.this, "Sửa sân thành công");
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

        layoutPitchDialog();
    }

    private boolean checkInput() {
        if(pitchIdTextField.getText().equals("") || pitchTypeTextField.getText().equals("")){
            notificationLabel.setText("Không được để các trường rỗng");
            return false;
        }
        return true;
    }

    private void layoutPitchDialog() {
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
        add(pitchIdLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(pitchIdTextField, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(pitchTypeLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(pitchTypeTextField, gc);

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

    public Pitch getPitch(){
        return pitch;
    }

    public void setPitch(Pitch pitch){
        this.pitch.setPitchId(pitch.getPitchId());
        pitchIdTextField.setText(pitch.getPitchId());
        pitchTypeTextField.setText(String.valueOf(pitch.getPitchType()));
    }

}
