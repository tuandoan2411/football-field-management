package view;

import controller.LoginController;
import model.Account;
import view.Frame.Admin;
import view.Frame.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    JPanel titlePanel;
    JLabel titleLabel;

    JLabel userNameLabel;
    JTextField userNameTextField;
    JLabel passwordLabel;
    JPasswordField passwordField;

    JLabel notificationLabel;
    JPanel notificationPanel;

    JPanel buttonPanel;
    JButton loginButton;

    LoginController loginController;

    public Login() {
        loginController = new LoginController();

        initComponents();
        decorateComponents();
        addLisenerForComponents();
        layoutComponents();

        setSize(new Dimension(450, 250));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setVisible(true);
    }

    private void initComponents() {
        titlePanel = new JPanel();
        titleLabel = new JLabel("ĐĂNG NHẬP");
        titlePanel.add(titleLabel);

        userNameLabel = new JLabel("Tên đăng nhập");
        userNameTextField = new JTextField(20);
        passwordLabel = new JLabel("Mật khẩu");
        passwordField = new JPasswordField(20);

        notificationLabel = new JLabel("");
        notificationPanel = new JPanel();
        notificationPanel.add(notificationLabel);

        buttonPanel = new JPanel();
        loginButton = new JButton("Đăng nhập");
        buttonPanel.add(loginButton);
    }

    private void decorateComponents() {
        Color backgroundLogin = new Color(41, 54, 63);
        Color foregroundLabel = new Color(56, 214, 193);
        Font labelAndTextFieldFont = new Font("SansSerif", Font.PLAIN, 14);
        getContentPane().setBackground(backgroundLogin);

        // Title Panel
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(foregroundLabel);
        titlePanel.setBackground(backgroundLogin);

        // Login Panel
        userNameLabel.setFont(labelAndTextFieldFont);
        userNameLabel.setForeground(foregroundLabel);
        userNameTextField.setBackground(new Color(201, 201, 201));

        passwordLabel.setFont(labelAndTextFieldFont);
        passwordLabel.setForeground(foregroundLabel);
        passwordField.setBackground(new Color(201, 201, 201));

        // Notifications
        notificationLabel.setForeground(Color.YELLOW);
        notificationPanel.setBackground(backgroundLogin);

        // Login Button
        loginButton.setFont(labelAndTextFieldFont);
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.setBackground(backgroundLogin);
        buttonPanel.setPreferredSize(new Dimension(100, 30));
    }

    private void addLisenerForComponents() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    Account account = loginController.getAccount(userNameTextField.getText(), String.valueOf(passwordField.getPassword()));
                    if(account != null){
                        if(account.getRoleId() == 0){
                            new Admin();
                            dispose();
                        }else{
                            int staffId = loginController.getStaff(account.getUserName()).getStaffId();
                            new User(staffId);
                            dispose();
                        }
                    }else {
                        notificationLabel.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                    }
                }
            }
        });
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                notificationLabel.setText("");
            }
        };
        userNameTextField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);
    }

    private boolean checkInput() {
        String password = String.valueOf(passwordField.getPassword());
        if(userNameTextField.getText().equals("") || password.equals("")){
            notificationLabel.setText("Không được để trống các trường");
            return false;
        }
        return true;
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Adding to JFrame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(titlePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(userNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(userNameTextField, gbc);

        gbc.insets = new Insets(5, 10, 0, 10);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        gbc.insets = new Insets(0, 10, 0, 10);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(notificationPanel, gbc);

        gbc.insets = new Insets(0, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);
    }

    public static void main(String[] args) {
        new Login();
    }
}
