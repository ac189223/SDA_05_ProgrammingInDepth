package exercise01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of data reader used to fetch data from the csv file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestDataFileOperator {
    private final DataFileOperator DFO = new DataFileOperator();
    private final String FILE_NAME = "src/main/java/resources/animals.csv";
    private final String TARGET_FILE_NAME = "src/main/java/resources/swimmers.csv";
    private AnimalsRegister animalsRegister = new AnimalsRegister();

    /**
     * Read data from non existing file
     */
    @Test
    public void testGetAnimalDataFromNonExistingFile() {
        // Act
        DataSet fetchedData = DFO.getAnimalData("src/main/java/resources/fakeFile.csv");
        int amountOfAnimals = fetchedData.getAnimals().size();
        int amountOfIncorrectLines = fetchedData.getIncorrectLines();
        // Assert
        assertEquals(0, amountOfAnimals);
        assertEquals(0, amountOfIncorrectLines);
    }

    /**
     * Read data from provided file
     */
    @Test
    public void testGetAnimalData() {
        // Act
        DataSet fetchedData = DFO.getAnimalData(FILE_NAME);
        String firstAnimalBreed = fetchedData.getAnimals().get(0).getBreedOrType();
        String secondAnimalName = fetchedData.getAnimals().get(1).getName();
        int amountOfAnimals = fetchedData.getAnimals().size();
        int lastAnimalYearOfBirth = fetchedData.getAnimals().get(amountOfAnimals - 1).getYearOfBirth();
        int amountOfIncorrectLines = fetchedData.getIncorrectLines();
        // Assert
        assertEquals("golden retriever", firstAnimalBreed);
        assertEquals("winter", secondAnimalName);
        assertEquals(2010, lastAnimalYearOfBirth);
        assertEquals(2, amountOfIncorrectLines);
    }

    /**
     * Write data into target file
     */
    @Test
    public void testOverwriteFile() {
        // Act
        animalsRegister.createAnimalsCollection();
        ArrayList<Animal> swimmers = animalsRegister.createSwimmers();
        DFO.overwriteFile(TARGET_FILE_NAME, swimmers);
        // Check the file
    }
}
