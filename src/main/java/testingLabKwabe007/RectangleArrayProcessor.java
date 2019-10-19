package testingLabKwabe007;

import java.util.ArrayList;

/**
 * A class used for processing Rectangle arrays.
 *  * @author andrzejcalka
 *  * @author =-_-=
 * @version 2019-10-19
 */
public class RectangleArrayProcessor {

    /**
     * Returns an array where all Rectangles with an area larger than maxArea have been removed from the input array.
     *
     * @param maxArea maximum area allowed.
     * @return the resulting Rectangle array.
     */
    public Rectangle[] removeRectanglesLargerThan(Rectangle[] array, int maxArea) {
        ArrayList<Rectangle> resultArray = new ArrayList<>();
        for (Rectangle rectangle: array)
            if (rectangle.area() <= maxArea)
                resultArray.add(rectangle);
        return resultArray.toArray(new Rectangle[resultArray.size()]);
    }

    /**
     * Returns an array where all Rectangles with an area larger or equal to r's area have been removed from the input array.
     *
     * @param array set of rectangles to be compared with the given one.
     * @param r given rectangle that is the biggest one allowed.
     * @return the resulting Rectangle array.
     */
    public Rectangle[] removeRectanglesLargerThan(Rectangle[] array, Rectangle r) {
        ArrayList<Rectangle> resultArray = new ArrayList<>();
        for (Rectangle rectangle: array)
            if (rectangle.compareTo(r) < 0)
                resultArray.add(rectangle);
        return resultArray.toArray(new Rectangle[resultArray.size()]);
    }

    /**
     * Returns true if the array contains a square or false otherwise.
     *
     * @param array set of rectangles to be checked
     * @return  boolean value
     */
    public boolean containsSquare(Rectangle[] array) {
        for (Rectangle rectangle: array)
            if (rectangle.getHeight() == rectangle.getWidth())
                return true;
        return false;
    }

    /**
     * Returns an array containing only the rectangles with an area equal to r's area.
     *
     * @param array set of rectangles to be compared with the given one.
     * @param r given rectangle that is the biggest one allowed.
     * @return the resulting Rectangle array.
     */
    public Rectangle[] filterRectanglesWithEqualArea(Rectangle[] array, Rectangle r) {
        ArrayList<Rectangle> resultArray = new ArrayList<>();
        for (Rectangle rectangle: array)
            if (rectangle.compareTo(r) == 0)
                resultArray.add(rectangle);
        return resultArray.toArray(new Rectangle[resultArray.size()]);
    }



}
