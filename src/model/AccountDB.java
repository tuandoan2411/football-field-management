
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDB {
    private static List<Account> accounts;

    static {
        accounts = new ArrayList<>();
        try {
            loadAccounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addAccount(Account account) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String addSql = "INSERT INTO account(user_name, password, role_id) " +
                "values(?, ?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(addSql);
        addStmt.setString(1, account.getUserName());
        addStmt.setString(2, account.getPassword());
        addStmt.setInt(3, account.getRoleId());

        addStmt.executeUpdate();

        accounts.add(account);
        Account.count++;
    }

    public static void removeAccount(Account account) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String removeSql = "DELETE FROM account WHERE user_name = ?";
        PreparedStatement removeStmt = connection.prepareStatement(removeSql);
        removeStmt.setString(1, account.getUserName());

        removeStmt.executeUpdate();

        accounts.remove(account);
    }

    public static void updateAccount(Account account) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String updateSql = "UPDATE account SET password = ?, role_id = ? WHERE user_name = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setString(1, account.getPassword());
        updateStmt.setInt(2, account.getRoleId());
        updateStmt.setString(3, account.getUserName());

        updateStmt.executeUpdate();

        accounts.set(accounts.indexOf(account), account);
    }

    public static Account getAccount(String userName) {
        Account account = new Account();
        account.setUserName(userName);
        int index = accounts.indexOf(account);
        if(index == -1) return null;
        return accounts.get(index);
    }

    private static void loadAccounts() throws SQLException{
        if(accounts.size() == 0) {
            Connection connection = ConnectDB.getConnection();
            String getSql = "SELECT * FROM account";
            Statement getStmt = connection.createStatement();

            ResultSet resultSet = getStmt.executeQuery(getSql);
            while (resultSet.next()) {
                Account account = new Account();
                account.setUserName(resultSet.getString(1));
                account.setPassword(resultSet.getString(2));
                account.setRoleId(resultSet.getInt(3));

                accounts.add(account);
                Account.count++;
            }
        }
    }

    public static void removeAccount(String userName) throws SQLException {
        removeAccount(new Account(userName));
    }
}
