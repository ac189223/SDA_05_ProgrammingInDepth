package rectangleJohan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {

    private Rectangle rect;

    @Before
    public void setUp(){
        rect = new Rectangle(5,10);
    }

    @Test
    public void testWidhtThrowError() {
        try{
            rect.setWidth(0);
            fail("0 width should throw error");
        } catch(IllegalArgumentException e) {
        }

        try{
            rect.setWidth(-1);
            fail("negative Width should throw error");
        } catch(IllegalArgumentException e) {
        }
    }

    @Test
    public void testHeightThrowError() {
        try{
            rect.setHeight(0);
            fail("0 height should throw error");
        } catch(IllegalArgumentException e) {
        }

        try{
            rect.setHeight(-1);
            fail("negative height should throw error");
        } catch(IllegalArgumentException e) {
        }
    }

    @Test
    public void testArea() { assertEquals(50,rect.area()); }

    @Test
    public void testCircumreference() { assertEquals(30,rect.circumreference()); }
}
