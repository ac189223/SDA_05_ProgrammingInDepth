package exercise01;

/**
 * Extends class Animal and implements Swimmer to represent a duck
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class AnimalDuck extends Animal implements Swimmer {

    /**
     * Constructor of an animal
     *
     * @param breedOrType breed or type of an animal
     * @param name        name of an animal
     * @param yearOfBirth year of birth of the animal (four numeric characters)
     */
    public AnimalDuck(String breedOrType, String name, int yearOfBirth) {
        super(breedOrType, name, yearOfBirth);
    }
}
