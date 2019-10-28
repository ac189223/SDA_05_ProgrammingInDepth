package robotProgramming;

import java.awt.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomWithWallsTest extends RoomOfTheRooms {

    @Test
    public void roomCreationTest() {
        // Assert
        assertEquals(1, this.getPosition().getX());
        assertEquals(2, this.getPosition().getY());
        assertEquals(0, this.getDirection());
        assertEquals(1, this.getMode());
        assertEquals(4, this.getDirections().size());
        assertEquals('N', (char) this.getDirections().get(0));
        assertEquals('Ö', (char) this.getDirections().get(1));
        assertEquals('S', (char) this.getDirections().get(2));
        assertEquals('W', (char) this.getDirections().get(3));
    }

    @Test
    public void coordinatesTest() {
        // Assert
        assertEquals(this.getX(), this.getPosition().getX());
        assertEquals(this.getY(), this.getPosition().getY());
    }

    @Test
    public void rotationTest() {
        // Act
        this.rotate(9);
        // Assert
        assertEquals(1, this.getDirection());

        // Act
        this.rotate(0);
        // Assert
        assertEquals(1, this.getDirection());

        // Act
        this.rotate(-3);
        // Assert
        assertEquals(2, this.getDirection());
    }

    @Test
    public void changingModeTest() {
        // Act
        this.changeMode(5);
        // Assert
        assertEquals(0, this.getMode());

        // Act
        this.changeMode(-4);
        // Assert
        assertEquals(0, this.getMode());

        // Act
        this.changeMode();
        // Assert
        assertEquals(1, this.getMode());
    }

    @Test
    public void reportPositionTest() {
        // Assert
        assertEquals("1 2 N", this.reportPosition());
    }

    @Test
    public void executeOneCommandTest() {
        // Act
        this.executeOneCommand('G');
        // Assert
        assertEquals("1 1 N", this.reportPosition());

        // Act
        this.executeOneCommand('H');
        // Assert
        assertEquals("1 1 Ö", this.reportPosition());

        // Act
        this.executeOneCommand('V');
        // Assert
        assertEquals("1 1 N", this.reportPosition());

        // Act
        try {
            this.executeOneCommand('F');
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        }

        // Arrange
        this.changeMode();

        // Act
        this.executeOneCommand('F');
        // Assert
        assertEquals("1 0 N", this.reportPosition());

        // Act
        this.executeOneCommand('R');
        // Assert
        assertEquals("1 0 Ö", this.reportPosition());

        // Act
        this.executeOneCommand('L');
        // Assert
        assertEquals("1 0 N", this.reportPosition());

        // Act
        try {
            this.executeOneCommand('H');
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void executeCommandLineTest() {
        // Act
        this.executeCommandLine("HGGHHGV");
        // Assert
        assertEquals("2 2 S", this.reportPosition());

        // Act
        try {
            this.executeCommandLine("HGFVGH");
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        }

        // Arrange
        this.changeMode();
        this.setPosition(new Point(2,2));
        this.setDirection(2);

        // Act
        this.executeCommandLine("RRFLF");
        // Assert
        assertEquals("1 1 W", this.reportPosition());

        // Act
        try {
            this.executeCommandLine("LFRRFVRF");
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void touchTheBorderTest() {
        // Act
        this.executeCommandLine("GG");
        // Assert
        assertTrue(this.touchTheBorder());

        // Act
        this.executeCommandLine("H");
        // Assert
        assertFalse(this.touchTheBorder());

        // Act
        this.executeCommandLine("GGG");
        // Assert
        assertTrue(this.touchTheBorder());

        // Act
        this.executeCommandLine("H");
        // Assert
        assertFalse(this.touchTheBorder());

        // Act
        this.executeCommandLine("GGGG");
        // Assert
        assertTrue(this.touchTheBorder());

        // Act
        this.executeCommandLine("H");
        // Assert
        assertFalse(this.touchTheBorder());

        // Act
        this.executeCommandLine("GGGG");
        // Assert
        assertTrue(this.touchTheBorder());

        // Act
        this.executeCommandLine("H");
        // Assert
        assertFalse(this.touchTheBorder());
    }

    @Test
    public void crossTheBorderTest() {
        // Act
        this.executeCommandLine("GG");
        // Assert
        assertTrue(this.touchTheBorder());

        // Act
        Point atTheBorder = new Point(getX(), getY());
        this.executeCommandLine("G");
        // Assert
        assertTrue(this.touchTheBorder());
        assertEquals(atTheBorder, getPosition());
    }
}
