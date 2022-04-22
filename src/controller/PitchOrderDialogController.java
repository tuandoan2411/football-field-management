package controller;

import model.Match;
import model.MatchDB;
import model.Pitch;
import model.PitchDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PitchOrderDialogController {
    public boolean order(int staffId, int customerId, Date startTime, int minute, int type) throws SQLException {
        System.out.println("PitchOrderDialogController " + startTime);
        List<Pitch> pitches = PitchDB.getPitchs();
        List<Match>  matches = MatchDB.getMatchsUnpaid();
        for(Pitch pitch : pitches){
            if(pitch.getPitchType() != type){
                continue;
            }
            boolean ordered = false;
            GregorianCalendar start = new GregorianCalendar();
            start.setTime(startTime);
            System.out.println("start " + start);
            GregorianCalendar end = new GregorianCalendar();
            end.setTime(startTime);
            end.add(GregorianCalendar.MINUTE, minute);
            System.out.println("end " + end);
            for (Match match : matches) {
                GregorianCalendar startOfMatch = new GregorianCalendar();
                startOfMatch.setTime(match.getTimeIn());
                GregorianCalendar endOfMatch = new GregorianCalendar();
                endOfMatch.setTime(startOfMatch.getTime());
                endOfMatch.add(GregorianCalendar.MINUTE, match.getMinute());

                if(pitch.getPitchId().equals(match.getPitchId())
                        && !(start.compareTo(endOfMatch) >= 0 || end.compareTo(startOfMatch) <= 0)){
                    ordered = true;
                    break;
                }
            }
            if(!ordered){
                Match match = new Match();
                System.out.println("start " + start);
                match.setTimeIn(new Timestamp(start.getTimeInMillis()));
                match.setMinute(minute);
                match.setPitchId(pitch.getPitchId());
                match.setStaffId(staffId);
                match.setCustomerId(customerId);
                match.setPayed(false);

                MatchDB.addMatch(match);
                return true;
            }
        }
        return false;
    }
}
