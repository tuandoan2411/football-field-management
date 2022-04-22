package controller;

import model.Match;
import model.MatchDB;

import java.sql.SQLException;
import java.util.List;

public class PaymentPanelController {
    public List<Match> getMatchsUnpaid() {
        return MatchDB.getMatchsUnpaid();
    }

    public void updateMatch(Match match) throws SQLException {
        MatchDB.updateMatch(match);
    }

    public void updateMatchsUnpaid() {
        MatchDB.updateMatchsUnpaid();
    }

    public Match getMatch(int matchId) {
        return MatchDB.getMatch(matchId);
    }

    public double getMatchPrice(Match match) throws SQLException {
        return MatchDB.getMatchPrice(match);
    }
}
