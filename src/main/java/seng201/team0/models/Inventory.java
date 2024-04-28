package seng201.team0.models;
import java.util.ArrayList;

/**
 * Inventory class.
 */

public class Inventory {
    private int bankBalance;
    private ArrayList<String> storedItems;
    private ArrayList<Tower> activeTowers;
    private int lastSelected;

    /* Increase/decrease bank balance: Alternatively, pass in negative values
    * to increaseBank
    * */
    public void decreaseBank(int decrease) {
        bankBalance -= decrease;
    }

    public void increaseBank(int increase) {
        bankBalance += increase;
    }

    /* Move a tower in inventory to an arrayList of active towers.
    * This means that the tower will contribute to filling carts.
    * TO-DO: Create Tower class.
    * */
    public void activateTower(Tower selectedTower) {
        activeTowers.add(selectedTower);
    }
}
