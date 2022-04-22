package view.Panel;

import controller.StaffPanelController;
import model.Account;
import model.Staff;
import view.Common.CommonTable;
import view.Dialog.AddUpdateStaffDialog;
import view.Listener.CheckAccountExistedListener;
import view.Listener.CommonAddListener;
import view.TableModel.StaffTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StaffPanel extends JPanel{
    private CommonAddPanel commonAddPanel;
    private CommonTable<Staff> staffTable;
    private StaffPanelController staffPanelController;

    public StaffPanel(){
        commonAddPanel = new CommonAddPanel("Thêm");
        staffTable = new CommonTable<>(new StaffTableModel());
        staffPanelController = new StaffPanelController();

        commonAddPanel.setCommonAddListener(new CommonAddListener() {
            @Override
            public void add() {
                try {
                    AddUpdateStaffDialog addStaffDialog = new AddUpdateStaffDialog(AddUpdateStaffDialog.ADD);
                    addStaffDialog.setCheckAccountExistedListener(new CheckAccountExistedListener() {
                        @Override
                        public boolean checkAccountExisted(Account account) {
                            return staffPanelController.getAccount(account) != null;
                        }
                    });
                    if(addStaffDialog.showDialog(StaffPanel.this, "Thêm nhân viên")){
                        staffPanelController.addAccount(addStaffDialog.getAccount());
                        staffPanelController.addStaff(addStaffDialog.getStaff());
                        JOptionPane.showMessageDialog(StaffPanel.this, "Thêm nhân viên thành công");
                        staffTable.refresh();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        staffTable.setData(staffPanelController.getStaffs());
        staffTable.setDeleteRowListener(row -> {
            int action = JOptionPane.showConfirmDialog(null,
                    "Bạn muốn xoá hàng này?",
                    "Xoá hàng", JOptionPane.YES_NO_CANCEL_OPTION);
            if(action == JOptionPane.YES_OPTION){
                Staff staff = staffTable.getRow(row);
                try {
                    staffPanelController.removeStaff(row);
                    staffPanelController.removeAccount(staff.getUserName());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                staffTable.refresh();
            }
        });
        staffTable.setUpdateRowListener(row -> {
            try {
                Staff staff = staffTable.getRow(row);
                Account account = staffPanelController.getAccount(staff.getUserName());
                AddUpdateStaffDialog updateStaffDialog = new AddUpdateStaffDialog(AddUpdateStaffDialog.UPDATE);
                updateStaffDialog.setStaff(staff);
                updateStaffDialog.setAccount(account);
                updateStaffDialog.setCheckAccountExistedListener(new CheckAccountExistedListener() {
                    @Override
                    public boolean checkAccountExisted(Account account) {
                        return staffPanelController.getAccount(account) != null;
                    }
                });
                if(updateStaffDialog.showDialog(StaffPanel.this, "Sửa nhân viên")){
                    Account updateAccount = updateStaffDialog.getAccount();
                    if(!updateAccount.equals(account)){
                        staffPanelController.removeAccount(account.getUserName());
                        staffPanelController.addAccount(updateAccount);
                    }else {
                        staffPanelController.updateAccount(updateAccount);
                    }
                    staffPanelController.updateStaff(updateStaffDialog.getStaff());

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("ON staffTable.refresh();");
            staffTable.refresh();
        });
        staffTable.initRendererAndEditor();

        setLayout(new BorderLayout());

        add(commonAddPanel, BorderLayout.NORTH);
        add(staffTable, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new StaffPanel());

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}