package Qualified_K;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of method from Challenge - should return proper ordinal number
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestChallenge {
    private Challenge challenge = new Challenge();

    @Test
    public void testNumberToOrdinal() {
        assertEquals("0", challenge.numberToOrdinal(0));
        assertEquals("1st", challenge.numberToOrdinal(1));
        assertEquals("2nd", challenge.numberToOrdinal(2));
        assertEquals("3rd", challenge.numberToOrdinal(3));
        assertEquals("4th", challenge.numberToOrdinal(4));
        assertEquals("10th", challenge.numberToOrdinal(10));
        assertEquals("11th", challenge.numberToOrdinal(11));
        assertEquals("12th", challenge.numberToOrdinal(12));
        assertEquals("13th", challenge.numberToOrdinal(13));
        assertEquals("14th", challenge.numberToOrdinal(14));
        assertEquals("20th", challenge.numberToOrdinal(20));
        assertEquals("21st", challenge.numberToOrdinal(21));
        assertEquals("22nd", challenge.numberToOrdinal(22));
        assertEquals("23rd", challenge.numberToOrdinal(23));
        assertEquals("24th", challenge.numberToOrdinal(24));
        assertEquals("30th", challenge.numberToOrdinal(30));
        assertEquals("1000th", challenge.numberToOrdinal(1000));
    }
}
