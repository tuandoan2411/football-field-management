package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Match {
    private int matchId;
    private Timestamp timeIn;
    private int minute;
    private String pitchId;
    private int customerId;
    private int staffId;
    private boolean isPayed;

    public Match(){
    }

    public Match(int matchId, Timestamp timeIn, int minute, String pitchId, int customerId, int staffId, boolean isPayed) {
        this.matchId = matchId;
        this.timeIn = timeIn;
        this.minute = minute;
        this.pitchId = pitchId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.isPayed = isPayed;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Timestamp timeIn) {
        this.timeIn = timeIn;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getPitchId() {
        return pitchId;
    }

    public void setPitchId(String pitchId) {
        this.pitchId = pitchId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Match){
            return matchId == ((Match) obj).matchId;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", timeIn=" + timeIn +
                ", minute=" + minute +
                ", pitchId='" + pitchId + '\'' +
                ", customerId=" + customerId +
                ", staffId=" + staffId +
                ", isPayed=" + isPayed +
                '}';
    }
}
