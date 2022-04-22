package controller;

import model.Pitch;
import model.PitchDB;
import model.PitchType;
import model.PitchTypeDB;

import java.sql.SQLException;
import java.util.List;

public class PitchTypePanelController {
    public void addPitchType(PitchType pitchType) throws SQLException {
        PitchTypeDB.addPitchType(pitchType);
    }

    public List<PitchType> getPitchTypes() {
        return PitchTypeDB.getPitchTypes();
    }

    public void removePitchType(int row) throws SQLException {
        PitchTypeDB.removePitchType(row);
    }

    public void updatePitchType(PitchType pitchType) throws SQLException {
        PitchTypeDB.updatePitchType(pitchType);
    }

    public boolean hasReferencedPitchType(PitchType pitchType) {
        List<Pitch> pitches = PitchDB.getPitchs();
        for(Pitch pitch: pitches){
            if(pitchType.getPitchTypeId() == pitch.getPitchType()){
                return true;
            }
        }
        return false;
    }
}
