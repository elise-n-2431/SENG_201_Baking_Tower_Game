package seng201.team48.models;

/**
 * This class describes Upgrade objects, which are Purchasable
 * Upgrades are applied to a Tower of the given level to level them up.
 * When used, the Tower's level increases by one, increasing its reload speed.
 * @author Hannah Grace, Elise Newman
 */

public class Upgrade extends Purchasable {
    private String towerLevel;
    /** Calls constructor of the Purchasable superclass.
     * @param towerLevel Numerical level of the tower which it can upgrade, passed in as a String.
     */
    public Upgrade(String towerLevel) {
        super("Level " + towerLevel + " Upgrade",
                "Upgrades a level " + towerLevel + " station to level "+(Integer.parseInt(towerLevel)+1), computePurchasePrice(towerLevel), 10);
        this.towerLevel = towerLevel;
    }

    public static int computePurchasePrice(String towerLevel) {
        int purchasePrice = switch (towerLevel) {
            case "1" -> 20;
            case "2" -> 30;
            case "3" -> 40;
            default -> throw new IllegalArgumentException("Invalid upgrade level: " + towerLevel);
        };
        return purchasePrice;
    }

    public String toString() {
        return this.getDescription() + " station. Price: " + this.getPurchasePrice();

    }
}
