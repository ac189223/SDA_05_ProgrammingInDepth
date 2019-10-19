package testingLabKwabe007;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RectangleProcessor.
 *
 * @author Simon Larsén
 * @author Anton Lyxell
 * @author andrzejcalka
 * @author =-_-=
 * @version 2019-10-19
 */
public class RectangleArrayProcessorTest {

    /* An instance of the RectangleArrayProcessor */
    private RectangleArrayProcessor rectangleArrayProcessor;

    /**
     * Set up variables to be available for each test case.
     *
     * Assistant's note: this function is executed once before every test
     * case in this class. In this method we initalize some default
     * values that will use to verify the correctness of the array processor.
     */
    @BeforeEach
    public void setUp() {
        rectangleArrayProcessor = new RectangleArrayProcessor();
    }

    @Test
    void testProcessorRemovesRectanglesLargerThan() {
        // Arrange
        Rectangle[] inputArray = {
                new Rectangle(2, 2),
                new Rectangle(2, 3),
                new Rectangle(5, 4),
                new Rectangle(1, 5)
        };
        int inputMaxArea = 5;
        Rectangle[] expectedArrayForMaxArea = {
                new Rectangle(2, 2),
                new Rectangle(1, 5)
        };
        Rectangle r = new Rectangle(4, 4);
        Rectangle[] expectedArrayForR = {
                new Rectangle(2, 2),
                new Rectangle(2, 3),
                new Rectangle(1, 5)
        };

        // Act
        Rectangle[] actualArrayForMaxArea = rectangleArrayProcessor.removeRectanglesLargerThan(inputArray, inputMaxArea);
        Rectangle[] actualArrayForR = rectangleArrayProcessor.removeRectanglesLargerThan(inputArray, r);

        // Assert
        assertArrayEquals(expectedArrayForMaxArea, actualArrayForMaxArea);
        assertArrayEquals(expectedArrayForR, actualArrayForR);
        assertNotEquals(actualArrayForR, actualArrayForMaxArea);
    }

    @Test
    void testContainsSquare() {
        // Arrange
        Rectangle[] inputArrayWithSquare = {
                new Rectangle(2, 2),
                new Rectangle(2, 3),
                new Rectangle(5, 4),
                new Rectangle(1, 5)
        };
        Rectangle[] inputArrayWithoutSquare = {
                new Rectangle(2, 3),
                new Rectangle(5, 4),
                new Rectangle(1, 5)
        };

        // Act
        boolean checkOfArrayWithSquare = rectangleArrayProcessor.containsSquare(inputArrayWithSquare);
        boolean checkOfArrayWithoutSquare = rectangleArrayProcessor.containsSquare(inputArrayWithoutSquare);

        // Assert
        assertTrue(checkOfArrayWithSquare);
        assertFalse(checkOfArrayWithoutSquare);
    }

    @Test
    void testFilterRectanglesWithEqualArea() {
        // Arrange
        Rectangle[] inputArrayWith = {
                new Rectangle(2, 2),
                new Rectangle(2, 3),
                new Rectangle(5, 4),
                new Rectangle(3, 2),
                new Rectangle(1, 6),
                new Rectangle(5, 3),
                new Rectangle(3, 5)
        };
        Rectangle[] inputArrayWithout = {
                new Rectangle(2, 2),
                new Rectangle(5, 4),
                new Rectangle(5, 3),
                new Rectangle(3, 5)
        };
        Rectangle r = new Rectangle(6, 1);
        Rectangle[] expectedArrayWithForR = {
                new Rectangle(2, 3),
                new Rectangle(3, 2),
                new Rectangle(1, 6),
        };

        // Act
        Rectangle[] actualArrayWithForR = rectangleArrayProcessor.filterRectanglesWithEqualArea(inputArrayWith, r);
        int actualSizeArrayWithoutForR = rectangleArrayProcessor.filterRectanglesWithEqualArea(inputArrayWithout, r).length;

        // Assert
        assertArrayEquals(expectedArrayWithForR, actualArrayWithForR);
        assertEquals(0, actualSizeArrayWithoutForR);
    }
}
















/**
 * A helper class to aid construction of collections of Rectangle object. You don't have to use this class
 * but it can be helpful!
 */
class RectangleFactory {

    /* The upper bound (exclusive) of the values submitted
       to the Rectangle constructor */
    private static final int RECTANGLE_MAX_DIMENSION = 50;
    /* The random generator, seeded with to make test suit deterministic */
    private final Random random;

    /* Constructor for RectangleFactory */
    public RectangleFactory() {
        random = new Random(0);
    }

    /**
     * Returns a boolean indicating whether the input array contains
     * a duplicate of the element and a given index in the indices
     * before the given index.
     *
     * @param  array an array of Rectangle objects to search for duplicates in
     * @param  index the index to search from
     * @return       the boolean indicator value
     */
    private boolean prefixContains(Rectangle[] array, int index) {
        for (int i = 0; i < index; i++) {
            if (array[i].compareTo(array[index]) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an array of Rectangle objects with random properties.
     * The array is guaranteed to not contain duplicates.
     *
     * @param  size  the number of elements in the array
     * @return       the array of Rectangle objects
     */
    public Rectangle[] boxArray(int size) {
        Rectangle[] array = new Rectangle[size];
        for (int i = 0; i < size; i++) {
            do {
                array[i] = new Rectangle(random.nextInt(RECTANGLE_MAX_DIMENSION),
                        random.nextInt(RECTANGLE_MAX_DIMENSION));
            } while (prefixContains(array, i));
        }
        return array;
    }


    public Rectangle[] rectangleArrayFromIntArrays(int[] ...intArray) {
        Rectangle[] array = new Rectangle[intArray.length];
        Stream<int[]> stream =  Arrays.stream(intArray);

        Rectangle[] rectangleArray = stream.map(a -> new Rectangle(a[0], a[1])).toArray(Rectangle[]::new);
        return rectangleArray;
    }

}