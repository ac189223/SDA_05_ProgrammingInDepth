package robotProgramming;

public class RoomToDie extends RoomOfTheRooms {
    private int amountOfLives;

    public RoomToDie(int amountOfLives) {
        super();
        this.setAmountOfLives(amountOfLives);
    }

    public RoomToDie() {
        super();
        this.setAmountOfLives(3);
    }

    public int getAmountOfLives() { return amountOfLives; }
    public void setAmountOfLives(int amountOfLives) { this.amountOfLives = amountOfLives; }

    @Override
    public void crossTheBorder() {
        setAmountOfLives(getAmountOfLives() - 1);
        if (!checkIfAlive())
            System.exit(0);
    }

    private boolean checkIfAlive() {
        boolean alive = true;
        if (getAmountOfLives() <= 0)
            alive = false;
        return alive;
    }
}
