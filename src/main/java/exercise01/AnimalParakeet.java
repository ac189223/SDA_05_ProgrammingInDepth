package exercise01;

/**
 * Extends class Animal to represent a parakeet
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class AnimalParakeet extends Animal {

    /**
     * Constructor of an animal
     *
     * @param breedOrType breed or type of an animal
     * @param name        name of an animal
     * @param yearOfBirth year of birth of the animal (four numeric characters)
     */
    public AnimalParakeet(String breedOrType, String name, int yearOfBirth) {
        super(breedOrType, name, yearOfBirth);
    }
}
