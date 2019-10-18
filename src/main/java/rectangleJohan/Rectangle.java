package rectangleJohan;

public class Rectangle {
    private int height;
    private int width;

    public Rectangle(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int area() { return height*width; }

    public int circumreference() { return height*2+width*2; }

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public void setHeight(int height) {
        if(height <= 0)
            throw new IllegalArgumentException();
        this.height = height;
    }

    public void setWidth(int width) {
        if(width <= 0)
            throw new IllegalArgumentException();
        this.width = width;
    }
}
