package testingLabKwabe007;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for Box.
 *
 * @author Simon Larsén
 * @version 2017-08-16
 */
public class RectangleTest {
    private final int ALL_HEIGHT = 1;
    private final int SMALL_WIDTH = 1;
    private final int MEDIUM_WIDTH = 2;
    private final int LARGE_WIDTH = 3;
    private final Rectangle SMALL_Rectangle = new Rectangle(ALL_HEIGHT, SMALL_WIDTH);
    private final Rectangle MEDIUM_Rectangle = new Rectangle(ALL_HEIGHT, MEDIUM_WIDTH);
    private final Rectangle LARGE_Rectangle = new Rectangle(ALL_HEIGHT, LARGE_WIDTH);

    @Test
    public void boxImplementsComparable() {
        // Act
        Rectangle rectangle = new Rectangle(1, 1);

        // Assert
        assertThat(rectangle, isA(Comparable.class));
    }

    @Test
    public void compareToIsPositiveWhenArgumentIsSmallerThanCallee() {
        // Act
        int compareMediumToSmall = MEDIUM_Rectangle.compareTo(SMALL_Rectangle);
        int compareLargeToSmall = LARGE_Rectangle.compareTo(SMALL_Rectangle);
        int compareLargeToMedium = LARGE_Rectangle.compareTo(MEDIUM_Rectangle);

        // Assert
        assertTrue(0 < compareMediumToSmall);
        assertTrue(0 < compareLargeToSmall);
        assertTrue(0 < compareLargeToMedium);
    }

    @Test
    public void compareToIsNegativeWhenArgumentIsLargerThanCallee() {
        // Act
        int compareSmallToMedium = SMALL_Rectangle.compareTo(MEDIUM_Rectangle);
        int compareSmallToLarge = SMALL_Rectangle.compareTo(LARGE_Rectangle);
        int compareMediumToLarge = MEDIUM_Rectangle.compareTo(LARGE_Rectangle);

        // Assert
        assertTrue(compareSmallToMedium < 0);
        assertTrue(compareSmallToLarge < 0);
        assertTrue(compareMediumToLarge < 0);
    }

    @Test
    public void compareToIsZeroWhenArgumentIsSameSizeAsCallee() {
        // Arrange
        Rectangle smallRectangle = new Rectangle(ALL_HEIGHT, SMALL_WIDTH);
        Rectangle mediumRectangle = new Rectangle(ALL_HEIGHT, MEDIUM_WIDTH);
        Rectangle largeRectangle = new Rectangle(ALL_HEIGHT, LARGE_WIDTH);

        // Act
        int compareSmallToSmall = SMALL_Rectangle.compareTo(smallRectangle);
        int compareMediumToMedium = MEDIUM_Rectangle.compareTo(mediumRectangle);
        int compareLargeToLarge = LARGE_Rectangle.compareTo(largeRectangle);

        // Assert
        assertThat(compareSmallToSmall, equalTo(0));
        assertThat(compareMediumToMedium, equalTo(0));
        assertThat(compareLargeToLarge, equalTo(0));
    }
}