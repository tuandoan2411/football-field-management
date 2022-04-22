package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchDB {
    private static List<Match> matchs;
    private static List<Match> matchsUnpaid;

    static {
        matchs = new ArrayList<>();
        try {
            loadMatchs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        matchsUnpaid = new ArrayList<>();
        updateMatchsUnpaid();
    }

    public static void updateMatchsUnpaid() {
        matchsUnpaid.clear();
        for(Match match: matchs){
            if(!match.isPayed()){
                matchsUnpaid.add(match);
            }
        }
    }

    private static void loadMatchs() throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String getSql = "SELECT * FROM `match`";
        Statement getStmt = connection.createStatement();
        ResultSet resultSet = getStmt.executeQuery(getSql);
        while(resultSet.next()){
            Match match = new Match();
            match.setMatchId(resultSet.getInt(1));
            match.setTimeIn(resultSet.getTimestamp(2));
            match.setMinute(resultSet.getInt(3));
            match.setPitchId(resultSet.getString(4));
            match.setCustomerId(resultSet.getInt(5));
            match.setStaffId(resultSet.getInt(6));
            match.setPayed(resultSet.getBoolean(7));

            matchs.add(match);
        }
    }

    public static void addMatch(Match match) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        String addSql = "INSERT INTO `match`(match_id, time_in, minute, pitch_id, customer_id, staff_id, is_paid) " +
                "values(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement addStmt = connection.prepareStatement(addSql);
        addStmt.setInt(1, match.getMatchId());
        addStmt.setTimestamp(2, match.getTimeIn());
        addStmt.setInt(3, match.getMinute());
        addStmt.setString(4, match.getPitchId());
        addStmt.setInt(5, match.getCustomerId());
        addStmt.setInt(6, match.getStaffId());
        addStmt.setBoolean(7, match.isPayed());

        addStmt.executeUpdate();
    }

    public static void removeMatch(Match match) throws SQLException{
        Connection connection = ConnectDB.getConnection();
        String removeSql = "DELETE FROM TABLE match WHERE match_id = ?";
        PreparedStatement removeStmt = connection.prepareStatement(removeSql);
        removeStmt.setInt(1, match.getStaffId());

        removeStmt.executeUpdate();
    }

    public static void updateMatch(Match match) throws SQLException{
        System.out.println(match);
        Connection connection = ConnectDB.getConnection();
        String updateSql = "UPDATE `match` " +
                            "SET time_in = ?," +
                            " minute = ?," +
                            " pitch_id = ?," +
                            " customer_id = ?," +
                            " staff_id = ?, " +
                            "is_paid = ?" +
                             " WHERE match_id = ?";
        PreparedStatement updateStmt = connection.prepareStatement(updateSql);

        updateStmt.setTimestamp(1, match.getTimeIn());
        updateStmt.setInt(2, match.getMinute());
        updateStmt.setString(3, match.getPitchId());
        updateStmt.setInt(4, match.getCustomerId());
        updateStmt.setInt(5, match.getStaffId());
        updateStmt.setBoolean(6, match.isPayed());
        updateStmt.setInt(7, match.getMatchId());

        updateStmt.executeUpdate();

        matchs.set(matchs.indexOf(match), match);
    }

    public static Match getMatch(int matchId){
        Match match = new Match();
        match.setMatchId(matchId);
        int index = matchs.indexOf(match);
        if(index != -1){
           return matchs.get(index);
        }
        return null;
    }

    public static double getMatchPrice(Match match) throws SQLException {
        Connection connection = ConnectDB.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{? = call get_match_price(?)}");
        callableStatement.registerOutParameter(1, Types.DOUBLE);
        callableStatement.setInt(2, match.getMatchId());

        callableStatement.execute();

        return callableStatement.getDouble(1);
    }

    public static List<Match> getMatchs(){
        return Collections.unmodifiableList(matchs);
    }

    public static List<Match> getMatchsUnpaid() {
        return Collections.unmodifiableList(matchsUnpaid);
    }
}
