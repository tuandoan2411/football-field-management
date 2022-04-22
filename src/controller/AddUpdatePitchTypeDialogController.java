package controller;

import model.PitchType;
import model.PitchTypeDB;

public class AddUpdatePitchTypeDialogController {
    public boolean hasPitchType(PitchType pitchType) {
        return PitchTypeDB.hasPitchType(pitchType);
    }
}
