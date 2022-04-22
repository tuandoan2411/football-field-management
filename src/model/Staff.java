package model;

public class Staff {
    private int staffId;
    private String name;
    private String phoneNumber;
    private String address;
    private String userName;

    public static int count = 1;

    public Staff(){
        this(count, null, null, null, null);
    }

    public Staff(int staffId, String name, String phoneNumber, String address, String userName) {
        this.staffId = staffId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Staff){
            return this.staffId == ((Staff) obj).staffId;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
