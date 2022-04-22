package model;

public class Pitch {
    private String pitchId;
    private int pitchType;

    public Pitch() {
    }

    public Pitch(String pitchId, int pitchType) {
        this.pitchId = pitchId;
        this.pitchType = pitchType;
    }

    public String getPitchId() {
        return pitchId;
    }

    public void setPitchId(String pitchId) {
        this.pitchId = pitchId;
    }

    public int getPitchType() {
        return pitchType;
    }

    public void setPitchType(int pitchType) {
        this.pitchType = pitchType;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pitch){
            return this.pitchId.equals(((Pitch) obj).pitchId);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pitch{" +
                "pitchId='" + pitchId + '\'' +
                ", pitchType='" + pitchType + '\'' +
                '}';
    }
}
