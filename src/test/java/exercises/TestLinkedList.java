package exercises;

import org.junit.Test;
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

}
