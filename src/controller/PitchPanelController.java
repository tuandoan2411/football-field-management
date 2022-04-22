package controller;

import model.Pitch;
import model.PitchDB;
import model.PitchType;
import model.PitchTypeDB;

import java.sql.SQLException;
import java.util.List;

public class PitchPanelController {
    public void addPitch(Pitch pitch) throws SQLException {
        PitchDB.addPitch(pitch);
    }

    public List<Pitch> getPitchs(){
        return PitchDB.getPitchs();
    }

    public void removePitch(int row) throws SQLException {
        PitchDB.removePitch(row);
    }

    public void updatePitch(Pitch pitch, Pitch newPitch) throws SQLException {
        PitchDB.updatePitch(pitch, newPitch);
    }
}
