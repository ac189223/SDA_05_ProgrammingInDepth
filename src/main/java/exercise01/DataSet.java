package exercise01;

/**
 * Represents a dataSet used as a format of data fetched from the csv file
 *
 * @author andrzejcalka
 * @author =-_-=
 */public final class DataSet {
    private final String breedOrType ;
    private final String name;
    private final int yearOfBirth;

    /**
     * Constructor of a dataSet containing data from one line of the csv file
     */
    public DataSet(String breedOrType, String name, int yearOfBirth) {
        this.breedOrType = breedOrType;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Getters for this class
     */
    public String getBreedOrType() { return breedOrType; }
    public String getName() { return name; }
    public int getYearOfBirth() { return yearOfBirth; }
}

