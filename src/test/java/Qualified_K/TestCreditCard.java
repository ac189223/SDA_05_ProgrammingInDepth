package Qualified_K;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of methods from CreditCard
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestCreditCard {
    private CreditCard creditCard = new CreditCard();

    @Test
    public void shouldMaskDigitsForBasicCreditCards() {
        assertEquals("5###########0694", creditCard.maskify("5512103073210694"));
    }
    @Test
    public void shouldNotMaskDigitsForShortCreditCards() {
        assertEquals("54321", creditCard.maskify("54321"));
    }
    @Test
    public void shouldNotMaskAnythingElseThanDigitsFirst() {
        assertEquals("4###-####-####-5616", creditCard.maskify("4556-3646-0793-5616"));
    }
    @Test
    public void shouldNotMaskAnythingElseThanDigitsSecond() {
        assertEquals("abcdefgh####ijkl56", creditCard.maskify("abcdefgh1234ijkl56"));
    }
    @Test
    public void shouldNotMaskAnythingElseThanDigitsThird() {
        assertEquals("A#######BCDEFG89HI", creditCard.maskify("A1234567BCDEFG89HI"));
    }
    @Test
    public void shouldNotMaskAnythingElseThanDigitsFourth() {
        assertEquals(",.-'####=)(!56", creditCard.maskify(",.-'1234=)(!56"));
    }
    @Test
    public void shouldHandleEmptyString() {
        assertEquals(",.-'####=)(!56", creditCard.maskify(",.-'1234=)(!56"));
    }


    @Test
    public void shouldMakeProperMask() {
        assertEquals("x###########xxxx", creditCard.createMask("5512103073210694"));
        assertEquals("", creditCard.createMask("54321"));
        assertEquals("x##############xxxx", creditCard.createMask("4556-3646-0793-5616"));
        assertEquals("x#############xxxx", creditCard.createMask("abcdefgh1234ijkl56"));
        assertEquals("x#############xxxx", creditCard.createMask("A1234567BCDEFG89HI"));
        assertEquals("x#########xxxx", creditCard.createMask(",.-'1234=)(!56"));
        assertEquals("", creditCard.createMask(""));
    }
}
