package exercise03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJSONReader {
    private JSONReader jsonReader = new JSONReader();

    /**
     * Test of reading from provided file
     * Test of reading from non existing file
     */
    @Test
    public void testReadFromJSON() {
        // Arrange
        MemberRegister memberRegister;
        // Act
        memberRegister = jsonReader.readFromJSON("src/main/java/exercise03/memberList.json");
        int amountOfMembers = memberRegister.getMembers().size();
        String firstMemberName = memberRegister.getMembers().get(0).getName();
        String secondMemberId = memberRegister.getMembers().get(1).getId();
        // Assert
        assertEquals(8, amountOfMembers);
        assertEquals("David Yu", firstMemberName);
        assertEquals("8c48", secondMemberId);

        // Act
        memberRegister = jsonReader.readFromJSON("src/main/java/exercise03/fakeFile.json");
        int amountOfMembersFromFakeFile = memberRegister.getMembers().size();
        // Assert
        assertEquals(0, amountOfMembersFromFakeFile);
    }
}
