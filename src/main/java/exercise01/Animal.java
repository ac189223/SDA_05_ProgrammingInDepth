package exercise01;

/**
 * Represents an animal (fetched from the csv file)
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class Animal {
    private String breedOrType ;
    private String name;
    private int yearOfBirth;

    /**
     * Constructor of an animal
     *
     * @param breedOrType       breed or type of an animal
     * @param name              name of an animal
     * @param yearOfBirth       year of birth of the animal (four numeric characters)
     */
    public Animal(String breedOrType, String name, int yearOfBirth) {
        this.setBreedOrType(breedOrType);
        this.setName(name);
        this.setYearOfBirth(yearOfBirth);
    }

    /**
     * Setters for this class
     */
    public void setBreedOrType(String breedOrType) { this.breedOrType = breedOrType; }
    public void setName(String name) { this.name = name; }
    public void setYearOfBirth(int yearOfBirth) { this.yearOfBirth = yearOfBirth; }

    /**
     * Getters for this class
     */
    public String getBreedOrType() { return breedOrType; }
    public String getName() { return name; }
    public int getYearOfBirth() { return yearOfBirth; }
}
