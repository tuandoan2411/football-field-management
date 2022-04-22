package view.EditorAndRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer(){
        this("");
    }

    public ButtonRenderer(String buttonName){
        this.setText(buttonName);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLUE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
