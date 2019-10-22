package exercise02;

/**
 * Represents the individual entries of review from the XML file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Review {
    private String SN;
    private String personID;
    private String date;
    private String score;

    /**
     * Getters for this class
     */
    public String getSN() { return SN; }
    public String getPersonID() { return personID; }
    public String getDate() { return date; }
    public String getScore() { return score; }

    /**
     * Setters for this class
     */
    public void setSN(String SN) { this.SN = SN; }
    public void setPersonID(String personID) { this.personID = personID; }
    public void setDate(String date) { this.date = date; }
    public void setScore(String score) { this.score = score; }

    @Override
    public String toString() {
        return "Review [SN=" + SN + ", personID=" + personID + ", date=" + date +
                ", score=" + score + "]";
    }
}
