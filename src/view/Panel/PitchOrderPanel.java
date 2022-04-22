package view.Panel;

import controller.PitchOrderPanelController;
import model.Customer;
import view.Dialog.PitchOrderDialog;
import view.EditorAndRenderer.ButtonRenderer;
import view.EditorAndRenderer.PitchOrderEditor;
import view.Listener.PitchOrderListener;
import view.TableModel.PitchOrderTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PitchOrderPanel extends JPanel{
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel notificationLabel;
    private JTable pitchOrderTable;

    private PitchOrderPanelController pitchOrderPanelController;
    private int staffId;

    public PitchOrderPanel(int staffId){
        this.staffId = staffId;
        pitchOrderPanelController = new PitchOrderPanelController();
        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(50, 150, 100));
        searchLabel = new JLabel("Nhập id khách hàng");
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
        searchButton = new JButton("Đặt sân");
        searchButton.setBackground(Color.BLUE);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    Customer customer = pitchOrderPanelController.getCustomer(Integer.parseInt(searchTextField.getText()));
                    if(customer != null){
                        new PitchOrderDialog(staffId, customer.getCustomerId());
                    }
                    else{
                        notificationLabel.setText("Không tìm thấy id " + searchTextField.getText());
                    }
                }
            }
        });
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.YELLOW);
        notificationLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));

        PitchOrderTableModel pitchOrderTableModel = new PitchOrderTableModel();
        pitchOrderTableModel.setData(pitchOrderPanelController.getCustomers());
        pitchOrderTable = new JTable(pitchOrderTableModel);
        pitchOrderTable.setRowSelectionAllowed(false);
        pitchOrderTable.setDefaultRenderer(ButtonRenderer.class, new ButtonRenderer("Đặt sân"));
        PitchOrderEditor pitchOrderEditor = new PitchOrderEditor();
        pitchOrderEditor.setPitchOrderListener(new PitchOrderListener(){
            @Override
            public void order(int row) {
                Customer customer = pitchOrderTableModel.getRow(row);
                new PitchOrderDialog(staffId, customer.getCustomerId());
            }
        });
        pitchOrderTable.setDefaultEditor(ButtonRenderer.class, pitchOrderEditor);

        setLayout(new BorderLayout());
        add(new JScrollPane(pitchOrderTable), BorderLayout.CENTER);

        layoutSearchPanel();
    }

    private boolean checkInput() {
        if(searchTextField.getText().equals("")){
            notificationLabel.setText("Hãy nhập id khách hàng");
            return false;
        }
        return true;
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

        frame.add(new PitchOrderPanel(1));

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
