package model;

public class PitchType {
    private int pitchTypeId;
    private double price;

    public PitchType(int pitchTypeId, double price) {
        this.pitchTypeId = pitchTypeId;
        this.price = price;
    }

    public PitchType(){}

    public int getPitchTypeId() {
        return pitchTypeId;
    }

    public void setPitchTypeId(int pitchTypeId) {
        this.pitchTypeId = pitchTypeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PitchType){
            return this.pitchTypeId == ((PitchType) obj).pitchTypeId;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PitchType{" +
                "pitchTypeId='" + pitchTypeId + '\'' +
                ", price=" + price +
                '}';
    }
}
