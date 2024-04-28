package seng201.team0.models;

/**
 * RepairKit objects are used to repair a Tower object.
 * When used, the Tower's broken variable changes from true to false.
 */

public class RepairKit extends Purchasable {
    public RepairKit(String name) {
        /* Calls constructor of the Purchasable superclass. */
        super(name, "Repair kit", 5, 3);
    }

    /* Repairs the given tower. */
    public void repairTower(Tower brokenTower) {
        brokenTower.setBroken(false);
    }
}
