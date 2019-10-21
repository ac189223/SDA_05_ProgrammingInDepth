package exercise01;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestCsvFileOperator {
    private final CsvFileOperator CFO = new CsvFileOperator();
    private final String FILE_NAME = "src/main/java/resources/animals.csv";

    /**
     * Read data from non existing file
     */
    @Test
    public void testGetAnimalDataFromNonExistingFile() {
        // Act
        DataSet fetchedData = CFO.getAnimalData("src/main/java/resources/fakeFile.csv");
        // Assert
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
        DataSet fetchedData = CFO.getAnimalData(FILE_NAME);
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
}
