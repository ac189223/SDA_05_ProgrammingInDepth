package exercise01;

import java.util.ArrayList;

/**
 * Represents a dataSet used as a format for data fetched from the csv file together with number of incorrectly provided lines
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public final class DataSet {
    private final ArrayList<DataSetAnimal> animals;
    private final int incorrectLines;

    /**
     * Constructor of a dataSet containing data from one line of the csv file
     *
     * @param animals           first field in dataSet containing list of animals
     * @param incorrectLines    second field in dataSet number of incorrect fields in file
     */
    public DataSet(ArrayList<DataSetAnimal> animals, int incorrectLines) {
        this.animals = animals;
        this.incorrectLines = incorrectLines;
    }

    /**
     * Getters for this class
     *
     */
    public ArrayList<DataSetAnimal> getAnimals() { return animals; }
    public int getIncorrectLines() { return incorrectLines; }
}

