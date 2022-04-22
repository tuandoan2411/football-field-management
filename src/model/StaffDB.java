package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaffDB {
    private static List<Staff> staffs;

    static {
        staffs = new ArrayList<>();
        /*addStaff(new Staff(1, "TRAN THI THANH THUY", "3333", "LONG AN", "THANH THUY"));
        addStaff(new Staff(2, "MESSI", "1010", "ARGENTINA", "ME"));*/

        try {
            loadStaffs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addStaff(Staff staff) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String addSql = "INSERT INTO staff(staff_id, name, phone_number, address, user_name) " +
                "values(?, ?, ?, ?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(addSql);
        addStmt.setInt(1, staff.getStaffId());
        addStmt.setString(2, staff.getName());
        addStmt.setString(3, staff.getPhoneNumber());
        addStmt.setString(4, staff.getAddress());
        addStmt.setString(5, staff.getUserName());

        addStmt.executeUpdate();

        staffs.add(staff);
        Staff.count++;
    }

    public static void removeStaff(Staff staff) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String removeSql = "DELETE FROM staff WHERE staff_id = ?";
        PreparedStatement removeStmt = connection.prepareStatement(removeSql);
        removeStmt.setInt(1, staff.getStaffId());

        removeStmt.executeUpdate();

        staffs.remove(staff);
    }

    public static void updateStaff(Staff staff) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String updateSql = "UPDATE staff SET name = ?, phone_number = ?, address = ?, user_name = ?" +
                " WHERE staff_id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setString(1, staff.getName());
        updateStmt.setString(2, staff.getPhoneNumber());
        updateStmt.setString(3, staff.getAddress());
        updateStmt.setString(4, staff.getUserName());
        updateStmt.setInt(5, staff.getStaffId());

        updateStmt.executeUpdate();

        staffs.set(staffs.indexOf(staff), staff);
    }

    public static Staff getStaff(int staffId){
        Staff staff = new Staff();
        staff.setStaffId(staffId);
        int index = staffs.indexOf(staff);
        if(index == -1) return null;
        return staffs.get(index);
    }

    public static Staff getStaff(String userName){
        for (Staff staff: staffs) {
            if(staff.getUserName().equals(userName)){
                return staff;
            }
        }
        return null;
    }

    public static List<Staff> getStaffs(){
        return Collections.unmodifiableList(staffs);
    }

    private static void loadStaffs() throws SQLException{
        if(staffs.size() == 0) {
            Connection connection = ConnectDB.getConnection();
            String getSql = "SELECT * FROM staff";
            Statement getStmt = connection.createStatement();

            ResultSet resultSet = getStmt.executeQuery(getSql);
            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setStaffId(resultSet.getInt(1));
                staff.setName(resultSet.getString(2));
                staff.setPhoneNumber(resultSet.getString(3));
                staff.setAddress(resultSet.getString(4));
                staff.setUserName(resultSet.getString(5));

                staffs.add(staff);
                Staff.count++;
            }
        }
    }

    public static void removeStaff(int index) throws SQLException {
        removeStaff(staffs.get(index));
    }
}
