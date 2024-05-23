package seng201.team48.models;

/**
 * This class describes RepairKit objects, which are Purchasable
 * Repair kits are used to repair a Tower in the player's inventory from a broken state to an unbroken state.
 * When used, the Tower's broken variable changes from true to false.
 */

public class RepairKit extends Purchasable {
    /** Calls constructor of the Purchasable superclass.
     */
    public RepairKit() {
        super("Repair kit", "Be prepared in case your ingredient stations run out!\nRepairs a broken station.", 20, 3);
    }

    /**
     * Repairs the given tower by setting its 'broken' attribute to false.
     * @param brokenTower A tower with its 'broken' attribute set to true.
     */
    public void repairTower(Tower brokenTower) {
        brokenTower.setBroken(false);
    }

    public String toString() {
        return "Repair kit";

    }
}
