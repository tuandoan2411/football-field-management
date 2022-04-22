package view.TableModel;

import model.PitchType;
import view.EditorAndRenderer.DeleteRenderer;
import view.EditorAndRenderer.UpdateRenderer;

import java.util.List;

public class PitchTypeTableModel extends CommonTableModel<PitchType>{
    private List<PitchType> pitchTypes;
    private String[] columnNames = {"Loại sân", "Giá", "", ""};

    public PitchTypeTableModel() {
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
        return pitchTypes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PitchType pitchType = pitchTypes.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return pitchType.getPitchTypeId();
            case 1:
                return pitchType.getPrice();
            case 2:
                return "Xoá";
            case 3:
                return "Sửa";
        }
        return null;
    }

    @Override
    public void setData(List<PitchType> elements) {
        pitchTypes = elements;
    }

    public PitchType getRow(int row) {
        return pitchTypes.get(row);
    }
}

