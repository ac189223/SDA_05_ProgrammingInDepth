package AntProblem;

import java.util.ArrayList;
import java.util.Random;

public class AntProblem {
    private static AntProblem antProblem = new AntProblem();
    // Input
    // R N A B X
    // X hard cells
    private int R;      // length of edge       2 <= R <= 20
    private int N;      // number of chews      1 <= N <= R^3 - (R - 1)^3
    private int A;      // starting cell (on the edge)
    private int B;      // destination cell
    private int X;      // number of hard cells 0 <= X <= R^3 - (R - 1)^3 -1
    private ArrayList<Integer> hardCells;
    private int cells;
    private ArrayList<Integer> softCells;
    private int[][] honey;
    private Random random = new Random();

    public int getR() { return R; }
    public int getN() { return N; }
    public int getA() { return A; }
    public int getB() { return B; }
    public int getX() { return X; }
    public ArrayList<Integer> getHardCells() { return hardCells; }
    public int getCells() { return cells; }
    public ArrayList<Integer> getSoftCells() { return softCells; }
    public int[][] getHoney() { return honey; }

    public void setR(int r) { R = r; }
    public void setN(int n) { N = n; }
    public void setA(int a) { A = a; }
    public void setB(int b) { B = b; }
    public void setX(int x) { X = x; }
    public void setHardCells(ArrayList<Integer> hardCells) { this.hardCells = hardCells; }
    public void setCells(int cells) { this.cells = cells; }
    public void setSoftCells(ArrayList<Integer> softCells) { this.softCells = softCells; }
    public void setHoney(int[][] honey) { this.honey = honey; }

    public static void main(String[] args) {
        antProblem.readData();
        antProblem.createHoney();
        antProblem.printHoney();
    }

    private void readData() {
        setR(random.nextInt(19) + 2);
        setCells(third(getR()) - third(getR() - 1));
        setN(random.nextInt(getCells()) + 1);
        setA(random.nextInt(getR()) + 1);
        setX(random.nextInt(getCells()));
        settleHardCells();
        if (getX() == getCells() - 1)
            setB(getA());
        else {
            setSoftCells(new ArrayList<>());
            for (int i = 1; i <= getCells(); i++)
                if (!getHardCells().contains(i))
                    getSoftCells().add(i);
            setB(getSoftCells().get(random.nextInt(getSoftCells().size())));
        }
    }

    private int third(int i) { return i * i * i; }

    private void settleHardCells() {
        setHardCells(new ArrayList<>());
        while (getHardCells().size() < getX()){
            int potentialHC = random.nextInt(getCells()) + 1;
            if (!getHardCells().contains(potentialHC) && getA() != potentialHC)
                getHardCells().add(potentialHC);
        }
    }

    private void createHoney() {
        int diagonal = getR() * 2 - 1;
        setHoney(new int[diagonal][]);
        int length = R;
        int num = 1;
        for(int r = 0; r < diagonal; r++) {
            getHoney()[r] = new int[length];
            for (int pos = 0; pos < length; pos++) {
                getHoney()[r][pos] = num;
                num++;
            }
            if (r < R - 1)
                length++;
            else
                length--;
        }
    }

    private void printHoney() {
        System.out.println(getR() + " " + getCells()  + " " + getA() + " " + getB());
        for(int r = 0; r < getHoney().length; r++) {
            for (int pos = 0; pos < getHoney()[r].length; pos++) {
                if (getHardCells().contains(getHoney()[r][pos]))
                    System.out.print("*");
                System.out.print(getHoney()[r][pos] + " ");
            }
            System.out.println();
        }
    }
}
