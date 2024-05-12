package seng201.team0.models;
import seng201.team0.exceptions.NegativeBankException;

import java.util.ArrayList;

/**
 * Inventory class which stores bank balance, items owned by player. Controls which towers are active.
 * @author Hannah Grace
 */

public class Inventory {
    private int bankBalance;
    private ArrayList<String> storedItems;
    private ArrayList<Tower> activeTowers;
    private int lastSelected;

    public Inventory(int balance) {
        this.bankBalance = balance;
    }

    /**
     * Increase bank balance.
     * @param increase The amount (positive integer) to be added to player's bank balance.
     * */
    public void increaseBank(int increase) {
        bankBalance += increase;
    }

    /**
     * Decrease bank balance.
     * @param decrease The amount (positive integer) to be subtracted from player's bank balance.
    * */
    public void decreaseBank(int decrease) throws NegativeBankException {
        if (bankBalance - decrease >= 0) {
            bankBalance -= decrease;
        }
        else {
            throw new NegativeBankException("Bank balance below 0");
        }
    }

    /**
     * Return value of bank balance.
     * @return bankBalance
     * */
    public int getBankBalance() {
        return bankBalance;
    }

    /**
     * Move a tower in player's inventory to an arrayList of active towers.
    * This means that the tower will contribute to filling carts.
    * @param selectedTower The tower selected, based on mouse click in the GUI. (? or use other method)
    * */
    public void activateTower(Tower selectedTower) {
        activeTowers.add(selectedTower);
    }

    /**
     * Remove selected Tower object from array of active Towers.
     * The tower remains in Inventory, but will no longer contribute to filling carts.
     * @param selectedTower The tower selected, based on mouse click in the GUI.
     * */
    public void deactivateTower(Tower selectedTower) {
        activeTowers.remove(selectedTower);
    }


}
