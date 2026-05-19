package characters;

public class AccuracySystem {
    private double basicAccuracy;
    private double specialAccuracy;
    private double ultimateAccuracy;

    // Constructor to set unique rates per character type
    public AccuracySystem(double basic, double special, double ultimate) {
        this.basicAccuracy = basic;
        this.specialAccuracy = special;
        this.ultimateAccuracy = ultimate;
    }

    // Getters
    public double getBasicAccuracy() { return basicAccuracy; }
    public double getSpecialAccuracy() { return specialAccuracy; }
    public double getUltimateAccuracy() { return ultimateAccuracy; }

    // Setters
    public void setBasic(double basicAccuracy) { this.basicAccuracy = basicAccuracy; }
    public void setSpecial(double specialAccuracy) { this.specialAccuracy = specialAccuracy; }
    public void setUltimate(double ultimateAccuracy) { this.ultimateAccuracy = ultimateAccuracy; }
}