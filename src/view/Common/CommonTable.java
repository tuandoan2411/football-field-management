package view.Common;

import view.EditorAndRenderer.DeleteEditor;
import view.EditorAndRenderer.DeleteRenderer;
import view.EditorAndRenderer.UpdateEditor;
import view.EditorAndRenderer.UpdateRenderer;
import view.Listener.DeleteRowListener;
import view.Listener.UpdateRowListener;
import view.TableModel.CommonTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CommonTable<T> extends JPanel{
    private JTable table;
    private CommonTableModel<T> commonTableModel;
    private DeleteRowListener deleteRowListener;
    private UpdateRowListener updateRowListener;

    public CommonTable(CommonTableModel<T> commonTableModel){
        this.commonTableModel = commonTableModel;
        table = new JTable(commonTableModel);
        table.setRowSelectionAllowed(false);

        setLayout(new BorderLayout());
        add(new JScrollPane(table));
        setBackground(new Color(150, 102, 20));
    }

    public void initRendererAndEditor() {
        table.setDefaultRenderer(DeleteRenderer.class, new DeleteRenderer());
        table.setDefaultRenderer(UpdateRenderer.class, new UpdateRenderer());

        DeleteEditor deleteEditor = new DeleteEditor();
        deleteEditor.setDeleteRowListener(deleteRowListener);
        table.setDefaultEditor(DeleteRenderer.class, deleteEditor);

        UpdateEditor updateEditor = new UpdateEditor();
        updateEditor.setUpdateRowListener(updateRowListener);
        table.setDefaultEditor(UpdateRenderer.class, updateEditor);
    }

    public void setData(List<T> elements){
        commonTableModel.setData(elements);
    }

    public void setDeleteRowListener(DeleteRowListener deleteRowListener) {
        this.deleteRowListener = deleteRowListener;
    }

    public void setUpdateRowListener(UpdateRowListener updateRowListener){
        this.updateRowListener = updateRowListener;
    }

    public void refresh() {
        commonTableModel.fireTableDataChanged();
    }

    public T getRow(int row) {
        return commonTableModel.getRow(row);
    }
}
