package controller;

import model.Customer;
import model.CustomerDB;

import java.sql.SQLException;
import java.util.List;

public class CustomerPanelController {

    public List<Customer> getCustomers() {
        return CustomerDB.getCustomers();
    }

    public void addCustomer(Customer customer) throws SQLException {
        CustomerDB.addCustomer(customer);
    }

    public void removeCustomer(int row) throws SQLException {
        System.out.println("remove row: " + row);
        CustomerDB.removeCustomer(row);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        CustomerDB.updateCustomer(customer);
    }


}
