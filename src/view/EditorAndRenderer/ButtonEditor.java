package view.EditorAndRenderer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    protected JButton button;
    protected int selectedRow;

    public ButtonEditor(){
        button = new JButton();
        button.setForeground(Color.WHITE);
        button.setBackground(Color.MAGENTA);
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selectedRow = row;
        return button;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        super.cancelCellEditing();
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}

