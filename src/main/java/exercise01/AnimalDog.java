package exercise01;

/**
 * Extends class Animal and implements Swimmer to represent a dog
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class AnimalDog extends Animal implements Swimmer {

    /**
     * Constructor of an animal
     *
     * @param breedOrType breed or type of an animal
     * @param name        name of an animal
     * @param yearOfBirth year of birth of the animal (four numeric characters)
     */
    public AnimalDog(String breedOrType, String name, int yearOfBirth) {
        super(breedOrType, name, yearOfBirth);
    }
}
