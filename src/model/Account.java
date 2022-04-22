package model;

public class Account {
    private String userName;
    private String password;
    private int roleId;

    public static int count;

    public Account(){
    }

    public Account(String userName, String password, int roleId) {
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
    }

    public Account(String userName) {
        this(userName, null, 2);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Account){
            return this.userName.equals(((Account) obj).userName);
        }
        return false;
    }
}
