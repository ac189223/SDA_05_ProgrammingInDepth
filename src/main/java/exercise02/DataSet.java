package exercise02;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dataLists used as a format of data fetched from MySQL database
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class DataSet {
    private List<Bar> bars;
    private List<Review> reviews;

    /**
     * Constructor of a ready to work with dataSet containing empty ArrayLists
     */
    public DataSet() {
        this.bars = new ArrayList<Bar>();
        this.reviews = new ArrayList<Review>();
    }

    /**
     * Getters for this class
     */
    public List<Bar> getBars() { return bars; }
    public List<Review> getReviews() { return reviews; }

    /**
     * Setters for this class
     */
    public void setBars(List<Bar> bars) { this.bars = bars; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
