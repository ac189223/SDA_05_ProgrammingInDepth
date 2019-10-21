package exercise01;

/**
 * Represents an animalDataSet used as a format of data fetched from the csv file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public final class DataSetAnimal {
    private final String breedOrType ;
    private final String name;
    private final int yearOfBirth;

    /**
     * Constructor of a animalDataSet containing data from one line of the csv file
     *
     * @param breedOrType       first field in animalDataSet containing breed or type of an animal
     * @param name              second field in animalDataSet containing name of an animal
     * @param yearOfBirth       third field in animalDataSet containing year of birth of the animal (four numeric characters)
     */
    public DataSetAnimal(String breedOrType, String name, int yearOfBirth) {
        this.breedOrType = breedOrType;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Getters for this class
     *
     */
    public String getBreedOrType() { return breedOrType; }
    public String getName() { return name; }
    public int getYearOfBirth() { return yearOfBirth; }
}

