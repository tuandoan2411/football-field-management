package view.TableModel;

import model.Pitch;
import view.EditorAndRenderer.DeleteRenderer;
import view.EditorAndRenderer.UpdateRenderer;

import java.util.List;

public class PitchTableModel extends CommonTableModel<Pitch>{
    private List<Pitch> pitchs;
    private String[] columnNames = {"Mã sân", "Loại sân", "", ""};

    public PitchTableModel() {
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2) return DeleteRenderer.class;
        else if(columnIndex == 3) return UpdateRenderer.class;
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 2) return true;
        else if(columnIndex == 3) return true;
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public int getRowCount() {
        return pitchs.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pitch pitch = pitchs.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return pitch.getPitchId();
            case 1:
                return pitch.getPitchType();
            case 2:
                return "Xoá";
            case 3:
                return "Sửa";
        }
        return null;
    }

    @Override
    public void setData(List<Pitch> elements) {
        pitchs = elements;
    }

    public Pitch getRow(int row) {
        return pitchs.get(row);
    }
}

