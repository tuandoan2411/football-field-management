package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDB {
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();

        try {
            /*addCustomer(new Customer(1, "tuan", "0354727259"));
            addCustomer(new Customer(2, "trung", "037394498"));
            addCustomer(new Customer(3, "viet", "39934858"));*/
            loadCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String addSql = "INSERT INTO customer(customer_id, name, phone_number) " +
                        "values(?, ?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(addSql);
        addStmt.setInt(1, customer.getCustomerId());
        addStmt.setString(2, customer.getName());
        addStmt.setString(3, customer.getPhoneNumber());

        addStmt.executeUpdate();

        customers.add(customer);
        Customer.count++;
    }

    public static void removeCustomer(Customer customer) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String removeSql = "DELETE FROM customer WHERE customer_id = ?";
        PreparedStatement removeStmt = connection.prepareStatement(removeSql);
        removeStmt.setInt(1, customer.getCustomerId());

        removeStmt.executeUpdate();

        customers.remove(customer);
    }

    public static void updateCustomer(Customer customer) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String updateSql = "UPDATE customer SET name = ?, phone_number = ? WHERE customer_id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setString(1, customer.getName());
        updateStmt.setString(2, customer.getPhoneNumber());
        updateStmt.setInt(3, customer.getCustomerId());

        updateStmt.executeUpdate();

        customers.set(customers.indexOf(customer), customer);
    }

    public static void updateCustomer(int index) throws SQLException {
        updateCustomer(customers.get(index));
    }

    public static Customer getCustomer(int customerId){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        int index = customers.indexOf(customer);
        if(index != -1){
            return customers.get(index);
        }
        return null;
    }

    public static List<Customer> getCustomers(){
        return Collections.unmodifiableList(customers);
    }

    private static void loadCustomers() throws SQLException{
        if(customers.size() == 0) {
            Connection connection = ConnectDB.getConnection();
            String getSql = "SELECT * FROM customer";
            Statement getStmt = connection.createStatement();

            ResultSet resultSet = getStmt.executeQuery(getSql);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setPhoneNumber(resultSet.getString(3));

                customers.add(customer);
                Customer.count++;
            }
        }
    }

    public static void removeCustomer(int index) throws SQLException {
        removeCustomer(customers.get(index));
    }
}
