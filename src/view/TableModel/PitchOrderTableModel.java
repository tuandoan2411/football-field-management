package view.TableModel;

import model.Customer;
import view.EditorAndRenderer.ButtonRenderer;

import java.util.List;

public class PitchOrderTableModel extends CommonTableModel<Customer>{
    private List<Customer> customers;
    private String[] columnNames = {"Mã khách hàng", "Tên", "Số điện thoại", ""};

    public PitchOrderTableModel() {

    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3) return ButtonRenderer.class;
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer customer = customers.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return customer.getCustomerId();
            case 1:
                return customer.getName();
            case 2:
                return customer.getPhoneNumber();
            case 3:
                return "Đặt sân";
        }
        return null;
    }

    @Override
    public void setData(List<Customer> elements) {
        customers = elements;
    }

    public Customer getRow(int row) {
        return customers.get(row);
    }
}
