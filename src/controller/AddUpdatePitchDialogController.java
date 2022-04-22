package controller;

import model.Pitch;
import model.PitchDB;
import model.PitchType;
import model.PitchTypeDB;

import java.util.List;

public class AddUpdatePitchDialogController {
    public boolean hasPitch(Pitch pitch) {
        return PitchDB.hasPitch(pitch);
    }

    public boolean hasReferencePitchType(Pitch pitch) {
        List<PitchType> pitchTypes = PitchTypeDB.getPitchTypes();
        for(PitchType pitchType: pitchTypes){
            if(pitch.getPitchType() == pitchType.getPitchTypeId()){
                return true;
            }
        }
        return false;
    }
}
