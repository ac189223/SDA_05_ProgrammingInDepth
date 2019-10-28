package robotProgramming;

import java.awt.*;
import java.util.HashMap;

public class RoomOfTheRooms implements Room{
    private  HashMap<Integer, Character> directions;
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
        this.directions = new HashMap<>();
        directions.put(0, 'N');
        directions.put(1, 'Ö');
        directions.put(2, 'S');
        directions.put(3, 'W');
    }

    public int getMIN_X() { return MIN_X; }
    public int getMAX_X() { return MAX_X; }
    public int getMIN_Y() { return MIN_Y; }
    public int getMAX_Y() { return MAX_Y; }
    public int getDirection() { return direction; }
    public Point getPosition() { return position; }
    public int getMode() { return mode; }
    public HashMap<Integer, Character> getDirections() { return directions; }

    public void setDirection(int direction) { this.direction = modulo(direction, 4); }
    public void setPosition(Point position) { this.position = position; }
    public void setMode(int mode) { this.mode = modulo(mode, 2); }

    @Override
    public Point getStartPosition() { return new Point(1, 2); }

    @Override
    public boolean contains(Point position) { return false; }

    public int getStartDirection() { return 0; }
    public char getStartMode() { return 1; }

    public int getX() { return (int) getPosition().getX(); }
    public int getY() { return (int) getPosition().getY(); }

    public boolean touchTheBorder() {
        if ((getDirection() == 0 && getY() == getMIN_Y()) ||
                (getDirection() == 1 && getX() == getMAX_X()) ||
                (getDirection() == 2 && getY() == getMAX_Y()) ||
                (getDirection() == 3 && getX() == getMIN_X()))
            return true;
        return false;
    }

    public void executeCommandLine(String commandLine) {
        for (int i = 0; i < commandLine.length(); i++)
            executeOneCommand(commandLine.charAt(i));
        reportPosition();
    }

    public void executeOneCommand(char command) {
        if ((getMode() == 0 && command == 'F') || (getMode() == 1 && command == 'G'))
            changePosition();
        else if ((getMode() == 0 && command == 'R') || (getMode() == 1 && command == 'H'))
            rotate(1);
        else if ((getMode() == 0 && command == 'L') || (getMode() == 1 && command == 'V'))
            rotate(-1);
        else {
            throw new IllegalArgumentException("Error in the provided program - incorrect character: " + command);
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

    public void rotate(int rotation) { setDirection(modulo((getDirection() + rotation), 4)); }

    public void changeMode() { setMode(modulo((getMode() + 1), 2)); }
    public void changeMode(int mode) { setMode(modulo(getMode() + mode, 2)); }

    public String reportPosition() {
        return getX() + " " + getY() + " " + getDirections().get(getDirection());
    }

    public int modulo(int number, int moduloBy) {
        int modulo = number % moduloBy;
        if (number < 0)
            modulo += moduloBy;
        return modulo;
    }

//    public static void main(String[] args) {
//        String commandLine = JOptionPane(new JFrame("=-_-="),
//                "Robots' starting position is \"1 2 N\" - this mean that it is settled in square (1,2) facing North.\n" +
//                        "Theis ​robot​ ​moves​ ​around​ ​the​ ​room​ ​by​ ​interpreting​ ​a​ ​string​ ​of​ ​commands​ ​in​ ​Swedish:\n" +
//                        "    V​ -->  ​turn​ ​left​ ​(vänster),\n" +
//                        "    H​ -->  ​turn​right​ ​(höger),\n" +
//                        "    G​ -->  move forward​ ​(gå)"
//
//    System.out.println("Program executed. Final position: " + reportPosition();
//    }

}
