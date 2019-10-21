package exercise01;

/**
 * Extends class Animal to represent a chicken
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class AnimalChicken extends Animal {

    /**
     * Constructor of an animal
     *
     * @param breedOrType breed or type of an animal
     * @param name        name of an animal
     * @param yearOfBirth year of birth of the animal (four numeric characters)
     */
    public AnimalChicken(String breedOrType, String name, int yearOfBirth) {
        super(breedOrType, name, yearOfBirth);
    }
}
