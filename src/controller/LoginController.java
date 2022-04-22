package controller;

import model.Account;
import model.AccountDB;
import model.Staff;
import model.StaffDB;

public class LoginController {

    private Account getAccount(String userName) {
        return AccountDB.getAccount(userName);
    }

    public Staff getStaff(String userName) {
        return StaffDB.getStaff(userName);
    }

    public Account getAccount(String userName, String password) {
        Account account = getAccount(userName);
        if(account != null && password.equals(account.getPassword())){
            return account;
        }
        return null;
    }
}
