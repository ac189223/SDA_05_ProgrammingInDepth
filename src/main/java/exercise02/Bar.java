package exercise02;

/**
 * Represents the individual entries of bar from the XML file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Bar {
    private String SN;
    private String name;
    private double fett;
    private double energy;
    private double kolhydrat;
    private double protein;
    private double fiber;

    /**
     * Getters for this class
     */
    public String getSN() { return SN; }
    public String getName() { return name; }
    public double getFett() { return fett; }
    public double getEnergy() { return energy; }
    public double getKolhydrat() { return kolhydrat; }
    public double getProtein() { return protein; }
    public double getFiber() { return fiber; }

    /**
     * Setters for this class
     */
    public void setSN(String SN) { this.SN = SN; }
    public void setName(String name) { this.name = name; }
    public void setFett(double fett) { this.fett = fett; }
    public void setEnergy(double energy) { this.energy = energy; }
    public void setKolhydrat(double kolhydrat) { this.kolhydrat = kolhydrat; }
    public void setProtein(double protein) { this.protein = protein; }
    public void setFiber(double fiber) { this.fiber = fiber; }

    @Override
    public String toString() {
        return "Bar [SN=" + SN + ", name=" + name + ", fett=" + fett + ", energy=" + energy +
                ", kolhydrat=" + kolhydrat + ", protein=" + protein + ", fiber=" + fiber + "]";
    }
}
