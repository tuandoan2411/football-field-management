package view.TableModel;

import model.Match;
import view.EditorAndRenderer.ButtonRenderer;

import java.util.List;

public class PaymentTableModel extends CommonTableModel<Match>{
    private List<Match> matchs;
    private String[] columnNames = {"Mã trận đấu", "Thời gian bắt đầu", "Số phút", "Mã sân", "Mã khách hàng", "Mã nhân viên", ""};

    public PaymentTableModel() {

    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 6) return ButtonRenderer.class;
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 6;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public int getRowCount() {
        return matchs.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Match match = matchs.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return match.getMatchId();
            case 1:
                return match.getTimeIn();
            case 2:
                return match.getMinute();
            case 3:
                return match.getPitchId();
            case 4:
                return match.getCustomerId();
            case 5:
                return match.getStaffId();
            case 6:
                return "Thanh toán";
        }
        return null;
    }

    @Override
    public void setData(List<Match> elements) {
        matchs = elements;
    }

    public Match getRow(int row) {
        return matchs.get(row);
    }

    public void refresh(){
        fireTableDataChanged();
    }
}

