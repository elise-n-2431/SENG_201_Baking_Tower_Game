package seng201.team0.models;
import java.util.ArrayList;

public class Inventory {
    private int bankBalance;
    private ArrayList<String> storedItems;
    private ArrayList<String> activeTowers;
    private int lastSelected;

    public void decreaseBank(int decrease) {
        bankBalance -= decrease;
    }

    public void increaseBank(int increase) {
        bankBalance += increase;
    }

    public void activateTower() {

    }
}
