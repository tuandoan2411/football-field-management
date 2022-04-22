package view.Dialog;

import model.Account;
import model.Staff;
import view.Listener.CheckAccountExistedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddUpdateStaffDialog extends JPanel {
    JDialog dialog;
    Staff staff;
    Account account;
    boolean ok;
    public static final String UPDATE = "Lưu";
    public static final String ADD = "Thêm";
    private String typeDialog;

    JLabel nameLabel;
    JLabel phoneNumberLabel;
    JLabel addressLabel;
    JLabel userNameLabel;
    JLabel passwordLabel;

    JTextField nameTextField;
    JTextField phoneNumberTextField;
    JTextField addressTextField;
    JTextField userNameTextField;
    JTextField passwordTextField;

    JPanel titlePanel;
    JLabel titleLabel;
    JPanel notificationPanel;
    JLabel notificationLabel;
    JPanel buttonPanel;
    JButton saveButton;
    JButton cancelButton;

    CheckAccountExistedListener checkAccountExistedListener;

    public AddUpdateStaffDialog(String typeDialog) {
        staff = new Staff();
        account = new Account();
        account.setRoleId(1);
        this.typeDialog = typeDialog;

        initComponents();
        decorateComponents();
        addLisenerForComponents();
        layoutComponents();
    }

    private void initComponents() {
        // JLabel
        nameLabel = new JLabel("Tên nhân viên");
        phoneNumberLabel = new JLabel("Số điện thoại");
        addressLabel = new JLabel("Địa chỉ");
        userNameLabel = new JLabel("Tên đăng nhập");
        passwordLabel = new JLabel("Mật khẩu");

        // JTextField
        nameTextField = new JTextField(20);
        phoneNumberTextField = new JTextField(20);
        addressTextField = new JTextField(20);
        userNameTextField = new JTextField(20);
        passwordTextField = new JTextField(20);

        // Title
        titlePanel = new JPanel();
        titleLabel = new JLabel("THÊM NHÂN VIÊN");
        titlePanel.add(titleLabel);

        // Notification
        notificationPanel = new JPanel();
        notificationLabel = new JLabel(" ");
        notificationPanel.add(notificationLabel);

        // Button
        buttonPanel = new JPanel();
        saveButton = new JButton(typeDialog);
        cancelButton = new JButton("Thoát");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
    }

    private void decorateComponents() {
        Color addCustomerDialogBackground = new Color(41, 54, 63);
        setBackground(addCustomerDialogBackground);

        //Font and Color
        Font contentFont = new Font("SansSerif", Font.PLAIN, 14);
        Color contentColor = new Color(56, 214, 193);
        Color backgroundColorTextField = new Color(201, 201, 201);
        Color titleForeground = Color.RED;
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        Color notificationForeground = Color.YELLOW;

        // Label
        nameLabel.setFont(contentFont);
        phoneNumberLabel.setFont(contentFont);
        addressLabel.setFont(contentFont);
        userNameLabel.setFont(contentFont);
        passwordLabel.setFont(contentFont);

        nameLabel.setForeground(contentColor);
        phoneNumberLabel.setForeground(contentColor);
        addressLabel.setForeground(contentColor);
        userNameLabel.setForeground(contentColor);
        passwordLabel.setForeground(contentColor);

        //TextField
        nameTextField.setBackground(backgroundColorTextField);
        phoneNumberTextField.setBackground(backgroundColorTextField);
        addressTextField.setBackground(backgroundColorTextField);
        userNameTextField.setBackground(backgroundColorTextField);
        passwordTextField.setBackground(backgroundColorTextField);

        // Title
        titlePanel.setBackground(addCustomerDialogBackground);

        titleLabel.setForeground(titleForeground);
        titleLabel.setFont(titleFont);

        // Notification
        notificationPanel.setBackground(addCustomerDialogBackground);

        notificationPanel.setPreferredSize(new Dimension(450, 30));
        notificationLabel.setForeground(notificationForeground);

        // Button
        buttonPanel.setBackground(addCustomerDialogBackground);
        buttonPanel.setPreferredSize(new Dimension(100, 30));

        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.setBackground(Color.BLUE);
        saveButton.setForeground(Color.WHITE);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setBackground(Color.BLUE);
        cancelButton.setForeground(Color.WHITE);
    }

    private void addLisenerForComponents() {
        phoneNumberTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char test = e.getKeyChar();
                if (!(Character.isDigit(test))) {
                    e.consume();
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    staff.setName(nameTextField.getText());
                    staff.setPhoneNumber(phoneNumberTextField.getText());
                    staff.setAddress(addressTextField.getText());
                    staff.setUserName(userNameTextField.getText());

                    String userNameBeforeSet = account.getUserName();
                    account.setUserName(userNameTextField.getText());
                    account.setPassword(passwordTextField.getText());

                    if(checkAccountExistedListener.checkAccountExisted(account)){
                        if(typeDialog.equals(UPDATE) && userNameBeforeSet.equals(account.getUserName())){
                            ok = true;
                            dialog.dispose();
                        }else {
                            notificationLabel.setText("Tên đăng nhập đã tồn tại");
                        }
                    }else {
                        ok = true;
                        dialog.dispose();
                    }
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }
    
    private boolean checkInput() {
        if(nameTextField.getText().equals("") || phoneNumberTextField.getText().equals("")
          || addressTextField.getText().equals("") || userNameTextField.getText().equals("")
            || passwordTextField.getText().equals("")){
            notificationLabel.setText("Không được để các trường rỗng");
            return false;
        }
        return true;
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

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
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(nameTextField, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(phoneNumberLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(phoneNumberTextField, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(addressLabel, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(addressTextField, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(userNameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(userNameTextField, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(passwordTextField, gc);

        gc.insets = new Insets(5, 10, 5, 10);

        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(notificationPanel, gc);

        gc.insets = new Insets(5, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 7;
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
        dialog.setVisible(true);

        return ok;
    }

    public Staff getStaff(){
        return staff;
    }

    public void setStaff(Staff staff){
        this.staff.setStaffId(staff.getStaffId());
        nameTextField.setText(staff.getName());
        phoneNumberTextField.setText(staff.getPhoneNumber());
        addressTextField.setText(staff.getAddress());
    }

    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account.setUserName(account.getUserName());
        userNameTextField.setText(account.getUserName());
        passwordTextField.setText(account.getPassword());
    }

    public void setCheckAccountExistedListener(CheckAccountExistedListener checkAccountExistedListener) {
        this.checkAccountExistedListener = checkAccountExistedListener;
    }
}


