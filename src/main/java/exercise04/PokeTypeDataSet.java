package exercise04;

import java.util.ArrayList;

public class PokeTypeDataSet {
    private ArrayList<String> proAttack;
    private ArrayList<String> poorAttack;
    private ArrayList<String> proDefence;
    private ArrayList<String> poorDefence;

    public PokeTypeDataSet() {
        this.proAttack = new ArrayList<>();
        this.poorAttack = new ArrayList<>();
        this.proDefence = new ArrayList<>();
        this.poorDefence = new ArrayList<>();
    }

    public ArrayList<String> getProAttack() { return proAttack; }
    public ArrayList<String> getPoorAttack() { return poorAttack; }
    public ArrayList<String> getProDefence() { return proDefence; }
    public ArrayList<String> getPoorDefence() { return poorDefence; }
}
