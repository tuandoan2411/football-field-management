package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PitchDB {
    private static List<Pitch> pitchs;

    static {
        pitchs = new ArrayList<>();
        try {
            loadPitchs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addPitch(Pitch pitch) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String addSql = "INSERT INTO pitch(pitch_id, pitch_type_id) " +
                "values(?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(addSql);
        addStmt.setString(1, pitch.getPitchId());
        addStmt.setInt(2, pitch.getPitchType());

        addStmt.executeUpdate();

        pitchs.add(pitch);
    }

    private static void loadPitchs() throws SQLException{
        if(pitchs.size() == 0) {
            Connection connection = ConnectDB.getConnection();
            String getSql = "SELECT * FROM pitch";
            Statement getStmt = connection.createStatement();

            ResultSet resultSet = getStmt.executeQuery(getSql);
            while (resultSet.next()) {
                Pitch pitch = new Pitch();
                pitch.setPitchId(resultSet.getString(1));
                pitch.setPitchType(resultSet.getInt(2));

                pitchs.add(pitch);
            }
        }
    }

    public static List<Pitch> getPitchs() {
        return Collections.unmodifiableList(pitchs);
    }

    public static void removePitch(Pitch pitch) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String removeSql = "DELETE FROM pitch WHERE pitch_id = ?";
        PreparedStatement removeStmt = connection.prepareStatement(removeSql);
        removeStmt.setString(1, pitch.getPitchId());

        removeStmt.executeUpdate();

        pitchs.remove(pitch);
    }

    public static void removePitch(int row) throws SQLException {
        removePitch(pitchs.get(row));
    }

    public static void updatePitch(Pitch pitch, Pitch newPitch) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String updateSql = "UPDATE pitch SET pitch_id = ?, pitch_type_id = ? WHERE pitch_id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
        updateStmt.setString(1, newPitch.getPitchId());
        updateStmt.setInt(2, newPitch.getPitchType());
        updateStmt.setString(3, pitch.getPitchId());

        updateStmt.executeUpdate();

        pitchs.set(pitchs.indexOf(pitch), newPitch);
    }

    public static boolean hasPitch(Pitch pitch) {
        return pitchs.indexOf(pitch) != -1;
    }
}
