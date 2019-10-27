package robotProgramming;

import java.awt.*;

public class RoomOfTheRooms implements Room{
    private final int MIN_X = 0;
    private final int MAX_X = 4;
    private final int MIN_Y = 0;
    private final int MAX_Y = 4;
    private int direction;              // 0 for North
    private Point position;
    private int mode;                   // 0 for English

    public RoomOfTheRooms() {
        this.setDirection(getStartDirection());
        this.setPosition(getStartPosition());
        this.setMode(getStartMode());
    }

    public int getMIN_X() { return MIN_X; }
    public int getMAX_X() { return MAX_X; }
    public int getMIN_Y() { return MIN_Y; }
    public int getMAX_Y() { return MAX_Y; }
    public int getDirection() { return direction; }
    public Point getPosition() { return position; }
    public int getMode() { return mode; }

    public void setDirection(int direction) { this.direction = direction % 4; }
    public void setPosition(Point position) { this.position = position; }
    public void setMode(int mode) { this.mode = mode % 2; }

    @Override
    public Point getStartPosition() { return new Point(1, 2); }

    @Override
    public boolean contains(Point position) { return false; }

    public int getStartDirection() { return 0; }
    public char getStartMode() { return 0; }

    public int getX() { return (int) getPosition().getX(); }
    public int getY() { return (int) getPosition().getY(); }

    public boolean touchTheBorder() {
        if ((getDirection() == 0 && getX() == getMIN_X()) ||
                (getDirection() == 1 && getY() == getMAX_Y()) ||
                (getDirection() == 2 && getX() == getMAX_X()) ||
                (getDirection() == 3 && getY() == getMIN_Y()))
            return true;
        return false;
    }

    public void executeOneCommand(char command) {
        if ((getMode() == 0 && command == 'F') || (getMode() == 1 && command == 'G'))
            changePosition();
        else if ((getMode() == 0 && command == 'R') || (getMode() == 1 && command == 'H'))
            rotate(1);
        else if ((getMode() == 0 && command == 'L') || (getMode() == 1 && command == 'V'))
            rotate(-1);
        else {
            System.out.println("Error in the provided program - incorrect character");
            System.exit(0);
        }
    }

    public void changePosition() {
        if (touchTheBorder())
            crossTheBorder();
        else
            switch (getDirection()) {
                case 0:
                    getPosition().setLocation(getX(), getY() - 1);
                    break;
                case 1:
                    getPosition().setLocation(getX() + 1, getY());
                    break;
                case 2:
                    getPosition().setLocation(getX(), getY() + 1);
                    break;
                case 3:
                    getPosition().setLocation(getX() - 1, getY());
                    break;
            }
    }

    public void crossTheBorder() {
        // to be implemented in subclasses
    }

    public void rotate(int rotationDirection) { setDirection((getDirection() + rotationDirection) % 4); }

    public void changeMode() { setMode((getMode() + 1) % 2); }
    public void changeMode(int mode) { setMode(mode % 2); }
}
