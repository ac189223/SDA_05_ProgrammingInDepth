package robotProgramming;

public class RoomGoesRound extends RoomOfTheRooms {

    @Override
    public void crossTheBorder() {
        switch (getDirection()) {
            case 0:
                getPosition().setLocation(getX(), modulo(getY() - 1, 4));
                break;
            case 1:
                getPosition().setLocation(modulo(getX() + 1, 4), getY());
                break;
            case 2:
                getPosition().setLocation(getX(), modulo(getY() + 1, 4));
                break;
            case 3:
                getPosition().setLocation(modulo(getX() - 1, 4), getY());
                break;
        }
    }
}
