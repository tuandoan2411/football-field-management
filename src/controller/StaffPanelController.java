package controller;

import model.*;
import model.Staff;
import model.StaffDB;

import java.sql.SQLException;
import java.util.List;

public class StaffPanelController {

    public List<Staff> getStaffs() {
        return StaffDB.getStaffs();
    }

    public void addStaff(Staff staff) throws SQLException {
        StaffDB.addStaff(staff);
    }

    public void removeStaff(int row) throws SQLException {
        System.out.println("remove row: " + row);
        StaffDB.removeStaff(row);
    }

    public void updateStaff(Staff staff) throws SQLException {
        StaffDB.updateStaff(staff);
    }

    public Staff getStaff(Staff staff){
        return StaffDB.getStaff(staff.getStaffId());
    }

    public void updateAccount(Account account) throws SQLException {
        AccountDB.updateAccount(account);
    }

    public void addAccount(Account account) throws SQLException {
        AccountDB.addAccount(account);
    }

    public void removeAccount(String userName) throws SQLException {
        AccountDB.removeAccount(userName);
    }

    public Account getAccount(Account account) {
        return AccountDB.getAccount(account.getUserName());
    }

    public Account getAccount(String userName) {
        return AccountDB.getAccount(userName);
    }
}
