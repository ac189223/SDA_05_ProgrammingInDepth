package exercises;

import org.junit.Test;

        import java.util.Random;

        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.fail;

public class TestLinkedList {
    @Test
    public void testGetOnEmptyList () {
        LinkedList list = new LinkedList();
        boolean success = false;
        try {
            list.get(0);
            fail("should have thrown an exception");
        } catch (IndexOutOfBoundsException e) {
            success = true;
        }
        assertEquals(true, success);
    }

    @Test
    public void testGetOnListWithNodes () {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(8);
        list.add(4);
        assertEquals(1, list.get(0));                       // First
        assertEquals(4, list.get(2));                       // Last
        assertEquals(8, list.get(1));                       // Middle
    }

    @Test
    public void testAdd_Get () {
        LinkedList list = new LinkedList();
        list.add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    public void testAdd_GetOutOfBounds () {
        LinkedList list = new LinkedList();
        list.add(1);
        boolean success = false;
        try {
            list.get(1);
            fail("should have thrown an exception");
        } catch (IndexOutOfBoundsException e) {
            success = true;
        }
        assertEquals(true, success);
    }

    @Test
    public void testSizeOnEmptyList () {
        LinkedList list = new LinkedList();
        assertEquals(0, list.size());
    }

    @Test
    public void testAddOne_Size () {
        LinkedList list = new LinkedList();
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testAddRandom_Size () {
        LinkedList list = new LinkedList();
        int amount = new Random().nextInt(10) + 11;
        for (int i = 0; i < amount; i++)
            list.add(i);
        assertEquals(amount, list.size());
    }
}
