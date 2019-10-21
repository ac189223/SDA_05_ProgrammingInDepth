package exercise01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Represents a set of animals stored as instances of the correct subclass of class Animals
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class AnimalsRegister {
    private final String FILE_NAME = "src/main/java/resources/animals.csv";
    private final DataFileOperator DFO = new DataFileOperator();
    private ArrayList<Animal> animals;

    /**
     * Setter for this class
     */
    public void setAnimals(ArrayList<Animal> animals) { this.animals = animals; }

    /**
     * Getters for this class
     */
    public String getFILE_NAME() { return FILE_NAME; }
    public DataFileOperator getDFO() { return DFO; }
    public ArrayList<Animal> getAnimals() { return animals; }

    /**
     * Fetching animals data from csv file
     */
    public void createAnimalsCollection() {
        ArrayList<DataSetAnimal> animalsFetched = getDFO().getAnimalData(getFILE_NAME()).getAnimals();
        setAnimals(new ArrayList<>());
        for (DataSetAnimal animal: animalsFetched) {
            String tmpBreed = animal.getBreedOrType().substring(animal.getBreedOrType().lastIndexOf(" ") + 1);
            switch (tmpBreed) {
                case "dolphin":
                    getAnimals().add(new AnimalDolphin(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                case "duck":
                    getAnimals().add(new AnimalDuck(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                case "cat":
                    getAnimals().add(new AnimalCat(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                case "chicken":
                    getAnimals().add(new AnimalChicken(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                case "horse":
                    getAnimals().add(new AnimalHorse(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                case "shark":
                    getAnimals().add(new AnimalShark(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                case "parakeet":
                    getAnimals().add(new AnimalParakeet(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
                default:
                    getAnimals().add(new AnimalDog(animal.getBreedOrType(), animal.getName(), animal.getYearOfBirth()));
                    break;
            }
        }
    }

    /**
     * Printing collection of animals to the console, sorted alphabetically
     */
    public void printAlphabetically() {
        if (getAnimals().size() > 0) {
            System.out.println("======== Animals sorted alphabetically ========");
            getAnimals().stream()
                    .sorted(Comparator.comparing(Animal::getName)).collect(Collectors.toList())
                    .forEach(animal -> System.out.println(printDetails(animal)));
            System.out.println("================================== =-_-= ======");
        } else {
            System.out.println("Nothing to print");
        }
    }

    /**
     * Printing animal details to the console
     *
     * @param animal            the animal chosen for printout
     * @return                  String to be printed
     */
    public String printDetails(Animal animal) {
        return animal.getName() + " - " + animal.getBreedOrType() + ", born in " + animal.getYearOfBirth();
    }

    /**
     * Filtering collection of animals by breed or name
     *
     * @param field             field that will be filtered
     * @param value             value that will be searched for
     * @return                  ArrayList of filtered animals
     */
    public ArrayList<Animal> filterAnimals(String field, String value) {
        ArrayList<Animal> filteredAnimals = new ArrayList<>();
        if (getAnimals().size() > 0) {
            if (field.equals("breed"))
                filteredAnimals = (ArrayList<Animal>) getAnimals().stream()
                        .filter(animal -> animal.getBreedOrType().equals(value))
                        .collect(Collectors.toList());
            else if (field.equals("name"))
                filteredAnimals = (ArrayList<Animal>) getAnimals().stream()
                        .filter(animal -> animal.getName().equals(value))
                        .collect(Collectors.toList());
        }
        return filteredAnimals;
    }

    /**
     * Filtering collection of animals by year
     *
     * @param field             field that will be filtered
     * @param value             value that will be searched for
     * @return                  ArrayList of filtered animals
     */
    public ArrayList<Animal> filterAnimals(String field, int value) {
        ArrayList<Animal> filteredAnimals = new ArrayList<>();
        if (getAnimals().size() > 0)
            if (field.equals("year"))
                filteredAnimals = (ArrayList<Animal>) getAnimals().stream()
                        .filter(animal -> animal.getYearOfBirth() == value)
                        .collect(Collectors.toList());
        return filteredAnimals;
    }

    /**
     * Filtering collection of animals to create swimmers collection
     *
     * @return                  ArrayList of swimmers - animals, that their class implements Swimmers interface
     */
    public ArrayList<Animal> createSwimmers() {
        ArrayList<Animal> swimmers = new ArrayList<>();
        if (getAnimals().size() > 0)
            swimmers = (ArrayList<Animal>) getAnimals().stream()
                    .filter(animal -> animal instanceof Swimmer)
                    .collect(Collectors.toList());
        return swimmers;
    }


}
