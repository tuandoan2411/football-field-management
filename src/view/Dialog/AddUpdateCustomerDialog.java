package view.Dialog;

import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class AddUpdateCustomerDialog extends JPanel {
    JDialog dialog;
    Customer customer;
    boolean ok;
    public static final String UPDATE = "Lưu";
    public static final String ADD = "Thêm";
    private String typeDialog;

    JLabel nameLabel;
    JLabel phoneNumberLabel;
    JTextField nameTextField;
    JTextField phoneNumberTextField;
    JPanel titlePanel;
    JPanel notificationPanel;
    JLabel notificationLabel;
    JPanel buttonPanel;

    public AddUpdateCustomerDialog(String typeDialog) {
        customer = new Customer();
        this.typeDialog = typeDialog;
        setLayout(new GridBagLayout());
        Color addCustomerDialogBackground = new Color(41, 54, 63);
        setBackground(addCustomerDialogBackground);

        //Font and Color
        Font contentFont = new Font("SansSerif", Font.PLAIN, 14);
        Color contentColor = new Color(56, 214, 193);
        Color backgroundColorTextField = new Color(201, 201, 201);
        Color titleForeground = Color.RED;
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        Color notificationForeground = Color.YELLOW;

        // JLabel
        nameLabel = new JLabel("Tên khách hàng");
        nameLabel.setFont(contentFont);
        nameLabel.setForeground(contentColor);
        phoneNumberLabel = new JLabel("Số điện thoại");
        phoneNumberLabel.setFont(contentFont);
        phoneNumberLabel.setForeground(contentColor);

        // JTextField
        nameTextField = new JTextField(20);
        nameTextField.setBackground(backgroundColorTextField);
        phoneNumberTextField = new JTextField(20);
        phoneNumberTextField.setBackground(backgroundColorTextField);

        phoneNumberTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char test = e.getKeyChar();
                if (!(Character.isDigit(test))) {
                    e.consume();
                }
            }
        });

        // Title
        titlePanel = new JPanel();
        titlePanel.setBackground(addCustomerDialogBackground);
        JLabel titleLabel = new JLabel("THÊM KHÁCH HÀNG");
        titleLabel.setForeground(titleForeground);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);

        // Notifications
        notificationPanel = new JPanel();
        notificationPanel.setBackground(addCustomerDialogBackground);
        notificationLabel = new JLabel(" ");
        notificationPanel.setPreferredSize(new Dimension(450, 30));
        notificationLabel.setForeground(notificationForeground);
        notificationPanel.add(notificationLabel);

        // JButton
        buttonPanel = new JPanel();
        buttonPanel.setBackground(addCustomerDialogBackground);
        JButton addButton = new JButton(typeDialog);
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    customer.setName(nameTextField.getText());
                    customer.setPhoneNumber(phoneNumberTextField.getText());
                    if(typeDialog.equals(ADD)){
                        JOptionPane.showMessageDialog(AddUpdateCustomerDialog.this, "Thêm khách hàng thành công");
                    }else {
                        JOptionPane.showMessageDialog(AddUpdateCustomerDialog.this, "Sửa khách hàng thành công");
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

        layoutCustomerDialog();
    }

    private boolean checkInput() {
        if(nameTextField.getText().equals("") || phoneNumberTextField.getText().equals("")){
            notificationLabel.setText("Không được để các trường rỗng");
            return false;
        }
        return true;
    }

    private void layoutCustomerDialog() {
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
        dialog.setVisible(true);

        return ok;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer.setCustomerId(customer.getCustomerId());
        nameTextField.setText(customer.getName());
        phoneNumberTextField.setText(customer.getPhoneNumber());
    }

}


