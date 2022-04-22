package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PitchTypeDB {
    private static List<PitchType> pitchTypes;

    static {
        pitchTypes = new ArrayList<>();
        try {
            loadPitchTypes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addPitchType(PitchType pitchType) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String addSql = "INSERT INTO pitch_type(pitch_type_id, price) " +
                "values(?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(addSql);
        addStmt.setInt(1, pitchType.getPitchTypeId());
        addStmt.setDouble(2, pitchType.getPrice());

        addStmt.executeUpdate();

        pitchTypes.add(pitchType);
    }

    private static void loadPitchTypes() throws SQLException{
        if(pitchTypes.size() == 0) {
            Connection connection = ConnectDB.getConnection();
            String getSql = "SELECT * FROM pitch_type";
            Statement getStmt = connection.createStatement();

            ResultSet resultSet = getStmt.executeQuery(getSql);
            while (resultSet.next()) {
                PitchType pitchType = new PitchType();
                pitchType.setPitchTypeId(resultSet.getInt(1));
                pitchType.setPrice(resultSet.getDouble(2));

                pitchTypes.add(pitchType);
            }
        }
    }

    public static List<PitchType> getPitchTypes() {
        return Collections.unmodifiableList(pitchTypes);
    }

    public static void removePitchType(PitchType pitchType) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String removeSql = "DELETE FROM pitch_type WHERE pitch_type_id = ?";
        PreparedStatement removeStmt = connection.prepareStatement(removeSql);
        removeStmt.setInt(1, pitchType.getPitchTypeId());

        removeStmt.executeUpdate();

        pitchTypes.remove(pitchType);
    }

    public static void removePitchType(int row) throws SQLException {
        removePitchType(pitchTypes.get(row));
    }

    public static void updatePitchType(PitchType pitchType) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String updateSql = "UPDATE pitch_type SET price = ? WHERE pitch_type_id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setDouble(1, pitchType.getPrice());
        updateStmt.setInt(2, pitchType.getPitchTypeId());

        updateStmt.executeUpdate();
        pitchTypes.set(pitchTypes.indexOf(pitchType), pitchType);
    }

    public static boolean hasPitchType(PitchType pitchType) {
        return pitchTypes.contains(pitchType);
    }
}
