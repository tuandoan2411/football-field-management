package view.TableModel;

import model.Staff;
import view.EditorAndRenderer.DeleteRenderer;
import view.EditorAndRenderer.UpdateRenderer;

import java.util.List;

public class StaffTableModel extends CommonTableModel<Staff> {
    private List<Staff> staffs;
    private String[] columnNames = {"Mã nhân viên", "Tên", "Số điện thoại", "Địa chỉ", "Tên đăng nhập", "", ""};

    public StaffTableModel() {

    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 5) return DeleteRenderer.class;
        else if (columnIndex == 6) return UpdateRenderer.class;
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 5) return true;
        else if (columnIndex == 6) return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public int getRowCount() {
        return staffs.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Staff staff = staffs.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return staff.getStaffId();
            case 1:
                return staff.getName();
            case 2:
                return staff.getPhoneNumber();
            case 3:
                return staff.getAddress();
            case 4:
                return staff.getUserName();
            case 5:
                return "Xoá";
            case 6:
                return "Sửa";
        }
        return null;
    }

    public void setData(List<Staff> staffs) {
        this.staffs = staffs;
    }

    @Override
    public Staff getRow(int row) {
        return staffs.get(row);
    }
}
