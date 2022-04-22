package view.EditorAndRenderer;

import view.Listener.DeleteRowListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEditor extends ButtonEditor {
    private DeleteRowListener deleteRowListener;

    public DeleteEditor(){
        super();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                deleteRowListener.deleteRow(selectedRow);
                stopCellEditing();
            }
        });
    }

    public void setDeleteRowListener(DeleteRowListener deleteRowListener) {
        this.deleteRowListener = deleteRowListener;
    }
}
