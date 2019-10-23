package exercise03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for controller methods
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestController {
    private final Controller con = new Controller();
    private final String fileName = "src/main/java/exercise03/memberList.json";

    /**
     * Test of Id validator
     */
    @Test
    public void TestCheckId() {
        // Arrange
        MemberRegister memberRegister = new MemberRegister();
        memberRegister.getMembers().add(new Member("John", "01"));
        memberRegister.getMembers().add(new Member("Paul", "02"));
        // Act
        boolean checkResult = con.checkIds(memberRegister);
        // Assert
        assertTrue(checkResult);

        // Arrange
        memberRegister.getMembers().get(0).setId(memberRegister.getMembers().get(1).getId());
        // Act
        checkResult = con.checkIds(memberRegister);
        // Assert
        assertFalse(checkResult);
    }
}
