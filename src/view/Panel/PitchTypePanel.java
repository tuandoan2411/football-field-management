package view.Panel;

import controller.PitchTypePanelController;
import model.PitchType;
import view.Common.CommonTable;
import view.Dialog.AddUpdatePitchTypeDialog;
import view.Listener.CommonAddListener;
import view.TableModel.PitchTypeTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PitchTypePanel extends JPanel {
    private CommonAddPanel commonAddPanel;
    private CommonTable<PitchType> pitchTypeTable;
    private PitchTypePanelController pitchTypePanelController;

    public PitchTypePanel(){
        commonAddPanel = new CommonAddPanel("Thêm");
        pitchTypeTable = new CommonTable<>(new PitchTypeTableModel());
        pitchTypePanelController = new PitchTypePanelController();

        commonAddPanel.setCommonAddListener(new CommonAddListener() {
            @Override
            public void add() {
                try {
                    AddUpdatePitchTypeDialog addPitchTypeDialog = new AddUpdatePitchTypeDialog(AddUpdatePitchTypeDialog.ADD);
                    if(addPitchTypeDialog.showDialog(PitchTypePanel.this, "Thêm loại sân")){
                        pitchTypePanelController.addPitchType(addPitchTypeDialog.getPitchType());
                        pitchTypeTable.refresh();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        pitchTypeTable.setData(pitchTypePanelController.getPitchTypes());
        pitchTypeTable.setDeleteRowListener(row -> {
            int action = JOptionPane.showConfirmDialog(null,
                    "Bạn muốn xoá hàng này?",
                    "Xoá hàng", JOptionPane.YES_NO_CANCEL_OPTION);
            if(action == JOptionPane.YES_OPTION){
                try {
                    pitchTypePanelController.removePitchType(row);
                    pitchTypeTable.refresh();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        pitchTypeTable.setUpdateRowListener(row -> {
            try {
                PitchType pitchType = pitchTypeTable.getRow(row);
                AddUpdatePitchTypeDialog updatePitchTypeDialog = new AddUpdatePitchTypeDialog(AddUpdatePitchTypeDialog.UPDATE);
                updatePitchTypeDialog.setPitchType(pitchType);
                if(updatePitchTypeDialog.showDialog(PitchTypePanel.this, "Sửa loại sân")){
                    pitchTypePanelController.updatePitchType(updatePitchTypeDialog.getPitchType());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            pitchTypeTable.refresh();
        });
        pitchTypeTable.initRendererAndEditor();

        setLayout(new BorderLayout());

        add(commonAddPanel, BorderLayout.NORTH);
        add(pitchTypeTable, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new PitchTypePanel());

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}