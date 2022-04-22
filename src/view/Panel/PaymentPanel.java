package view.Panel;

import controller.PaymentPanelController;
import model.Customer;
import model.Match;
import view.Dialog.PaymentDialog;
import view.EditorAndRenderer.ButtonRenderer;
import view.EditorAndRenderer.PaymentEditor;
import view.Listener.PaymentListener;
import view.TableModel.PaymentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class PaymentPanel extends JPanel {
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel notificationLabel;

    private JTable paymentTable;
    PaymentTableModel paymentTableModel;

    private PaymentPanelController paymentPanelController;
    private int staffId;

    public PaymentPanel(int staffId){
        this.staffId = staffId;
        paymentPanelController = new PaymentPanelController();
        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(50, 150, 100));
        searchLabel = new JLabel("Nhập id trận đấu");
        searchLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        searchTextField = new JTextField(10);
        searchTextField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char test = e.getKeyChar();
                if (!(Character.isDigit(test))) {
                    e.consume();
                }
            }
        });
        searchButton = new JButton("Thanh toán");
        searchButton.setBackground(Color.BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    Match match = paymentPanelController.getMatch(Integer.parseInt(searchTextField.getText()));
                    if(match != null){
                        try {
                            pay(match);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        notificationLabel.setText("");
                    }else {
                        notificationLabel.setText("không tồn tại id trận đấu này");
                    }
                }
            }
        });
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.YELLOW);
        notificationLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));

        paymentTableModel = new PaymentTableModel();
        paymentTableModel.setData(paymentPanelController.getMatchsUnpaid());
        paymentTable = new JTable(paymentTableModel);
        paymentTable.setRowSelectionAllowed(false);
        paymentTable.setDefaultRenderer(ButtonRenderer.class, new ButtonRenderer("Thanh toán"));
        PaymentEditor paymentEditor = new PaymentEditor();
        paymentEditor.setPaymentListener(new PaymentListener(){
            @Override
            public void pay(int row) {
                Match match = paymentTableModel.getRow(row);
                try {
                    PaymentPanel.this.pay(match);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        paymentTable.setDefaultEditor(ButtonRenderer.class, paymentEditor);

        setLayout(new BorderLayout());
        add(new JScrollPane(paymentTable), BorderLayout.CENTER);

        layoutSearchPanel();
    }

    private boolean checkInput() {
        if(searchTextField.getText().equals("")){
            notificationLabel.setText("Hãy nhập id trận đấu");
            return false;
        }
        return true;
    }

    private void pay(Match match) throws SQLException {
        PaymentDialog paymentDialog = new PaymentDialog((Frame)null, paymentPanelController.getMatchPrice(match));
        if(paymentDialog.isPaid()){
            match.setPayed(true);
            try {
                paymentPanelController.updateMatch(match);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            paymentPanelController.updateMatchsUnpaid();
            paymentTableModel.refresh();
        }
    }

    private void layoutSearchPanel() {
        searchPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 20);
        searchPanel.add(searchLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 20);
        searchPanel.add(searchTextField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        searchPanel.add(searchButton, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 3;
        gc.insets = new Insets(10, 0, 10, 0);
        searchPanel.add(notificationLabel, gc);

        add(searchPanel, BorderLayout.NORTH);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new PaymentPanel(1));

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

