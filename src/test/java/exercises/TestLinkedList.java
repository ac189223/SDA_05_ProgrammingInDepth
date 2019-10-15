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

}
