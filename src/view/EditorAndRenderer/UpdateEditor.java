package view.EditorAndRenderer;

import view.Listener.UpdateRowListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEditor extends ButtonEditor {
    private UpdateRowListener updateRowListener;

    public UpdateEditor(){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateRowListener.updateRow(selectedRow);
                stopCellEditing();
            }
        });
    }

    public void setUpdateRowListener(UpdateRowListener updateRowListener) {
        this.updateRowListener = updateRowListener;
    }
}
