package view.TableModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class CommonTableModel<T> extends AbstractTableModel {
    public abstract void setData(List<T> elements);

    public abstract T getRow(int row) ;
}
