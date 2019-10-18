package testingLabKwabe007;
/**
 * The Rectangle class models a three-dimensional Rectangle
 */
public class Rectangle implements Comparable<Rectangle> {
    private final int height;
    private final int width;

    /**
     * Create a new Rectangle with the specified dimensions (height, width).
     *
     * @param height the height of the Rectangle
     * @param width the width of the Rectangle
     */
    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Create a copy of Rectangle.
     *
     * @param Rectangle A Rectangle to copy.
     */
    public Rectangle(Rectangle Rectangle) {
        this.height = Rectangle.height;
        this.width = Rectangle.width;
    }

    /**
     * Get this Rectangle's area
     *
     * @return the Rectangle's area
     */
    public int area() {
        return height * width;
    }

    /**
     * @return The Rectangle's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The Rectangle's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Defines if two Rectangles should be considered equal based on their attributes.
     *
     * @param o an object
     * @return true if the given object has equal width and height to this Rectangle
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Rectangle)) {
            return false;
        }
        Rectangle other = (Rectangle) o;
        return this.width == other.width && this.height == other.height;
    }

    /**
     * Defines the hash code of this Rectangle.
     *
     * This is required by the contract of hashCode, which states that if for
     * two objects x and y, x.equals(y) is true,
     * then x.hashCode() == y.hashCode() must also be true. So, as we override
     * the Object.equals(Object o), we must also override Object.hashCode().
     *
     * For a good explanation, see Effective Java Recipe Item 9
     * @return the hash code of this Rectangle
     */
    @Override
    public int hashCode(){
        int result = 13;
        result = 31 * result + height;
        result = 31 * result + width;
        return result;
    }

    @Override
    public int compareTo(Rectangle rectangle) {
        if (this.area() > rectangle.area()) {
            return 1;
        } else if (this.area() == rectangle.area()) {
            return 0;
        } else {
            return -1;
        }
    }
}