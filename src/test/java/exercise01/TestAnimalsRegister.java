package exercise01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
