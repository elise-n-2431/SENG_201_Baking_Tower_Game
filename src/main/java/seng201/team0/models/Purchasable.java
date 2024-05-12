package seng201.team0.models;

/**
 * Purchasable class which is inherited by RepairKit, Upgrade, and Tower
 * @author Hannah Grace
 */
public class Purchasable {
    private String name;
    private int purchasePrice;
    private int sellPrice;
    private String description;

    /**
     * Constructor which is invoked by all children of Purchasable.
     * @param name Name of Purchasable object
     * @param description Description of object
     * @param purchasePrice Amount of coins required to purchase from shop
     * @param sellPrice Amount of coins which player can sell the item for
     */
    public Purchasable(String name, String description, int purchasePrice, int sellPrice) {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
    }

    /**
     * Gets description of Purchasable object
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get purchase price of Purchasable object
     * @return purchasePrice
     */
    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Get sell price of Purchasable object
     * @return sellPrice
     */
    public int getSellPrice() {
        return this.sellPrice;
    }
}
