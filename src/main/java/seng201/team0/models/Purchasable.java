package seng201.team0.models;

/**
 * Purchasable class which is inherited by RepairKit, Upgrade, and Tower
 */
public class Purchasable {
    private String name;
    private int purchasePrice;
    private int sellPrice;
    private String description;

    /* Constructor which is invoked by all children of Purchasable. */
    public Purchasable(String name, String description, int purchasePrice, int sellPrice) {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

    /* Returns description of Purchasable object */
    public String getDescription() {
        return this.description;
    }

    /* Returns purchase price of Purchasable object */
    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    /* Returns sell price of Purchasable object */
    public int getSellPrice() {
        return this.sellPrice;
    }
}
