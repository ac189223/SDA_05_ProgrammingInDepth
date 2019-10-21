package exercise01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests of register of animals
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestAnimalsRegister {
    private AnimalsRegister animalsRegister = new AnimalsRegister();

    /**
     * Create collection of specific types of animals
     */
    @Test
    public void testCreateAnimalsCollection() {
        // Act
        animalsRegister.createAnimalsCollection();
        ArrayList<Animal> fetchedAnimals = animalsRegister.getAnimals();
        int amountOfAnimals = animalsRegister.getAnimals().size();
        // Assert
        assertEquals(AnimalDog.class, fetchedAnimals.get(0).getClass());
        assertEquals(AnimalDolphin.class, fetchedAnimals.get(1).getClass());
        assertEquals(AnimalParakeet.class, fetchedAnimals.get(amountOfAnimals - 1).getClass());
    }

    /**
     * Prepare printout for one of the animals
     */
    @Test
    public void testPrintDetails() {
        // Act
        animalsRegister.createAnimalsCollection();
        Animal firstAnimal = animalsRegister.getAnimals().get(0);
        String givenPrintout = animalsRegister.printDetails(firstAnimal);
        String expectedPrintout = "lucy - golden retriever, born in 2011";
        // Assert
        assertEquals(expectedPrintout, givenPrintout);
    }

    /**
     * Printout for all of the animals (alphabetically sorted)
     */
    @Test
    public void testPrintAlphabetically() {
        // Act
        animalsRegister.createAnimalsCollection();
        animalsRegister.printAlphabetically();
        // Check the console

    }

    /**
     * Filtering methods on the animals
     */
    @Test
    public void testFilterAnimals() {
        // Act
        animalsRegister.createAnimalsCollection();
        ArrayList<Animal> filteredByWrongFieldString = animalsRegister.filterAnimals("size", "dolphin");
        ArrayList<Animal> filteredByWrongFieldInt = animalsRegister.filterAnimals("size", 10);
        ArrayList<Animal> filteredByBreed = animalsRegister.filterAnimals("breed", "dolphin");
        ArrayList<Animal> filteredByWrongBreed = animalsRegister.filterAnimals("breed", "kitty");
        ArrayList<Animal> filteredByName = animalsRegister.filterAnimals("name", "cleo");
        ArrayList<Animal> filteredByWrongName = animalsRegister.filterAnimals("name", "carrie");
        ArrayList<Animal> filteredByYear2010 = animalsRegister.filterAnimals("year", 2010);
        ArrayList<Animal> filteredByYear2011 = animalsRegister.filterAnimals("year", 2011);
        ArrayList<Animal> filteredByWrongYear = animalsRegister.filterAnimals("year", 2000);
        // Assert
        assertEquals(0, filteredByWrongFieldString.size());
        assertEquals(0, filteredByWrongFieldInt.size());
        assertEquals("winter", filteredByBreed.get(0).getName());
        assertEquals(AnimalDolphin.class, filteredByBreed.get(0).getClass());
        assertEquals(0, filteredByWrongBreed.size());
        assertEquals("parakeet", filteredByName.get(0).getBreedOrType());
        assertEquals(AnimalParakeet.class, filteredByName.get(0).getClass());
        assertEquals(0, filteredByWrongName.size());
        assertEquals(2, filteredByYear2010.size());
        assertEquals(1, filteredByYear2011.size());
        assertEquals(0, filteredByWrongYear.size());
    }

    /**
     * Creating swimmers collection
     */
    @Test
    public void testCreateSwimmers() {
        // Act
        animalsRegister.createAnimalsCollection();
        ArrayList<Animal> swimmers = animalsRegister.createSwimmers();
        // Assert
        assertEquals(5, swimmers.size());
        assertTrue(swimmers.get(0) instanceof Swimmer);
        assertTrue(swimmers.get(1) instanceof Swimmer);
        assertTrue(swimmers.get(2) instanceof Swimmer);
        assertTrue(swimmers.get(3) instanceof Swimmer);
        assertTrue(swimmers.get(4) instanceof Swimmer);
    }
}
