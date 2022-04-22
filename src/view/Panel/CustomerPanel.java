package view.Panel;

import controller.CustomerPanelController;
import model.Customer;
import view.Common.CommonTable;
import view.Dialog.AddUpdateCustomerDialog;
import view.Listener.CommonAddListener;
import view.TableModel.CustomerTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CustomerPanel extends JPanel{
    private CommonAddPanel commonAddPanel;
    private CommonTable<Customer> customerTable;
    private CustomerPanelController customerPanelController;

    public CustomerPanel(){
        commonAddPanel = new CommonAddPanel("Thêm");
        customerTable = new CommonTable<>(new CustomerTableModel());
        customerPanelController = new CustomerPanelController();

        commonAddPanel.setCommonAddListener(new CommonAddListener() {
            @Override
            public void add() {
                try {
                    AddUpdateCustomerDialog addCustomerDialog = new AddUpdateCustomerDialog(AddUpdateCustomerDialog.ADD);
                    if(addCustomerDialog.showDialog(CustomerPanel.this, "Thêm khách hàng")){
                        customerPanelController.addCustomer(addCustomerDialog.getCustomer());
                        customerTable.refresh();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        customerTable.setData(customerPanelController.getCustomers());
        customerTable.setDeleteRowListener(row -> {
            int action = JOptionPane.showConfirmDialog(null,
                    "Bạn muốn xoá hàng này?",
                    "Xoá hàng", JOptionPane.YES_NO_CANCEL_OPTION);
            if(action == JOptionPane.YES_OPTION){
                try {
                    customerPanelController.removeCustomer(row);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                customerTable.refresh();
            }
        });
        customerTable.setUpdateRowListener(row -> {
            try {
                Customer customer = customerTable.getRow(row);
                AddUpdateCustomerDialog updateCustomerDialog = new AddUpdateCustomerDialog(AddUpdateCustomerDialog.UPDATE);
                updateCustomerDialog.setCustomer(customer);
                if(updateCustomerDialog.showDialog(CustomerPanel.this, "Sửa khách hàng")){
                    customerPanelController.updateCustomer(updateCustomerDialog.getCustomer());
                    customerTable.refresh();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        customerTable.initRendererAndEditor();

        setLayout(new BorderLayout());

        add(commonAddPanel, BorderLayout.NORTH);
        add(customerTable, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new CustomerPanel());

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}