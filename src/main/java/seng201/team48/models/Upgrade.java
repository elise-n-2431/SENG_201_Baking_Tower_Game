package seng201.team48.models;

/**
 * This class describes Upgrade objects, which are Purchasable
 * Upgrades are applied to a Tower of the given level to level them up.
 * When used, the Tower's level increases by one, increasing its reload speed.
 */

public class Upgrade extends Purchasable {
    /** Calls constructor of the Purchasable superclass.
     * @param towerLevel Numerical level of the tower which it can upgrade, passed in as a String.
     */
    public Upgrade(String towerLevel) {
        super("Level " + towerLevel + " Upgrade", towerLevel, computePurchasePrice(towerLevel), 3);
    }

    public static int computePurchasePrice(String towerLevel) {
        int purchasePrice;
        switch (towerLevel) {
            case "1":
                purchasePrice = 20;
                break;
            case "2":
                purchasePrice = 30;
                break;
            case "3":
                purchasePrice = 40;
                break;
            default:
                throw new IllegalArgumentException("Invalid upgrade level: " + towerLevel);
        }
        return purchasePrice;
    }

    /**
     * Repairs the given tower by setting its 'broken' attribute to false.
     * @param brokenTower A tower with its 'broken' attribute set to true.
     */
    public void repairTower(Tower brokenTower) {
        brokenTower.setBroken(false);
    }

    public String toString() {
        return "Upgrade for level " + this.getDescription() + " tower. Price: " + this.getPurchasePrice();

    }
}
