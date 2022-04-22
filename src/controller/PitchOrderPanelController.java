package controller;

import model.Customer;
import model.CustomerDB;

import java.util.List;

public class PitchOrderPanelController {
    public List<Customer> getCustomers() {
        return CustomerDB.getCustomers();
    }

    public Customer getCustomer(int customerId) {
        return CustomerDB.getCustomer(customerId);
    }
}
