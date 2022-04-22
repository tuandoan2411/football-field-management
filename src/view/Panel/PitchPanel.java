package view.Panel;

import controller.PitchPanelController;
import model.Pitch;
import view.Common.CommonTable;
import view.Dialog.AddUpdatePitchDialog;
import view.Listener.CommonAddListener;
import view.TableModel.PitchTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PitchPanel extends JPanel {
    private CommonAddPanel commonAddPanel;
    private CommonTable<Pitch> pitchTable;
    private PitchPanelController pitchPanelController;

    public PitchPanel(){
        commonAddPanel = new CommonAddPanel("Thêm");
        pitchTable = new CommonTable<>(new PitchTableModel());
        pitchPanelController = new PitchPanelController();

        commonAddPanel.setCommonAddListener(new CommonAddListener() {
            @Override
            public void add() {
                try {
                    AddUpdatePitchDialog addPitchDialog = new AddUpdatePitchDialog(AddUpdatePitchDialog.ADD);
                    if(addPitchDialog.showDialog(PitchPanel.this, "Thêm sân")){
                        pitchPanelController.addPitch(addPitchDialog.getPitch());
                        pitchTable.refresh();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        pitchTable.setData(pitchPanelController.getPitchs());
        pitchTable.setDeleteRowListener(row -> {
            int action = JOptionPane.showConfirmDialog(null,
                    "Bạn muốn xoá hàng này?",
                    "Xoá hàng", JOptionPane.YES_NO_CANCEL_OPTION);
            if(action == JOptionPane.YES_OPTION){
                try {
                    pitchPanelController.removePitch(row);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                pitchTable.refresh();
            }
        });
        pitchTable.setUpdateRowListener(row -> {
            try {
                Pitch pitch = pitchTable.getRow(row);
                AddUpdatePitchDialog updatePitchDialog = new AddUpdatePitchDialog(AddUpdatePitchDialog.UPDATE);
                updatePitchDialog.setPitch(pitch);

                if(updatePitchDialog.showDialog(PitchPanel.this, "Sửa sân")){
                    pitchPanelController.updatePitch(pitch, updatePitchDialog.getPitch());
                    pitchTable.refresh();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        pitchTable.initRendererAndEditor();

        setLayout(new BorderLayout());

        add(commonAddPanel, BorderLayout.NORTH);
        add(pitchTable, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new PitchPanel());

        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
