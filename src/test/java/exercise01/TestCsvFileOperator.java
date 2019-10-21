package exercise01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCsvFileOperator {
    private final CsvFileOperator CFO = new CsvFileOperator();
    private final String FILE_NAME = "src/main/java/resources/animals.csv";

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
