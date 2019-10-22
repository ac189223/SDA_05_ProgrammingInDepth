package exercise02;

/**
 * Represents the individual entries of bar from the XML file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Bar {
    private String SN;
    private String fett;
    private String energy;
    private String kolhydrat;
    private String protein;
    private String fiber;

    /**
     * Getters for this class
     */
    public String getSN() { return SN; }
    public String getFett() { return fett; }
    public String getEnergy() { return energy; }
    public String getKolhydrat() { return kolhydrat; }
    public String getProtein() { return protein; }
    public String getFiber() { return fiber; }

    /**
     * Setters for this class
     */
    public void setSN(String SN) { this.SN = SN; }
    public void setFett(String fett) { this.fett = fett; }
    public void setEnergy(String energy) { this.energy = energy; }
    public void setKolhydrat(String kolhydrat) { this.kolhydrat = kolhydrat; }
    public void setProtein(String protein) { this.protein = protein; }
    public void setFiber(String fiber) { this.fiber = fiber; }

    @Override
    public String toString() {
        return "Bar [SN=" + SN + ", fett=" + fett + ", energy=" + energy +
                ", kolhydrat=" + kolhydrat + ", protein=" + protein + ", fiber=" + fiber + "]";
    }
}
