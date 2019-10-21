package exercise01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a data reader used to fetch data from the csv file
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class DataFileOperator {
    private final String SEPARATOR = ",";
    private static final int NUMBER_OF_FIELDS_EXPECTED = 3;
    private static final int BREED_TYPE = 0,
            NAME = 1,
            YEAR_OF_BIRTH = 2;

    /**
     * Constructor of a ready_to_use reader for csv file
     *
     */
    public DataFileOperator() { }

    /**
     * Method to read data from csv file

     * @param fileName      path and name of the csv file to read from
     * @return              collection of animalDataSets containing data and number of  incorrectly provided lines in the csv file
     */
    public DataSet getAnimalData(String fileName)
    {
        /**
         * Function to create dataSets from lines of the csv file
         *
         */
        Function<String, DataSetAnimal> createSetOfData =
                record -> {
                    String[] parts = record.split(SEPARATOR);
                    if (parts.length == NUMBER_OF_FIELDS_EXPECTED) {
                        try {
                            String breed_type = parts[BREED_TYPE].trim();
                            String name = parts[NAME].trim();
                            int yearOfBirth = Integer.parseInt(parts[YEAR_OF_BIRTH].trim());
                            return new DataSetAnimal(breed_type, name, yearOfBirth);
                        }
                        catch(NumberFormatException e) {
                            // throw new NumberFormatException("Line has an incorrect value for year of birth:\n    " + record);
                            System.out.println("Line has an incorrect value for year of birth:\n    " + record);
                            return null;
                        }
                    }
                    else {
                        System.out.println("Line has the wrong number of fields:\n    " + record);
                        return null;
                    }
                };

        ArrayList<DataSetAnimal> fetchedAnimalsData;
        try {
            fetchedAnimalsData = Files.lines(Paths.get(fileName))
                    .filter(record -> record.length() > 0)
                    .map(createSetOfData)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            // throw new IOException("Unable to open " + fileName);
            System.out.println("Unable to open " + fileName);
            fetchedAnimalsData = new ArrayList<>();
        }

        ArrayList<DataSetAnimal> animals = new ArrayList<>();
        int incorrectLines = 0;
        if (fetchedAnimalsData.size() != 0) {
            animals = fetchedAnimalsData.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toCollection(ArrayList::new));
            incorrectLines = (int) fetchedAnimalsData.stream()
                    .filter(Objects::isNull)
                    .count();
        }

        return new DataSet(animals, incorrectLines);
    }
}
