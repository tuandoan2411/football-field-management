package view.EditorAndRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class UpdateRenderer extends JButton implements TableCellRenderer {
    public UpdateRenderer(){
        this.setText("Sửa");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        } else {
            setBorder(null);
        }
        if (isSelected) {
            this.setForeground(Color.WHITE);
            this.setBackground(Color.PINK);
        } else {
            this.setForeground(Color.RED);
            this.setBackground(Color.YELLOW);
        }
        return this;
    }
}
