package seng201.team48.models;

/**
 * Purchasable class which is inherited by RepairKit, Upgrade, and Tower
 * @author Hannah Grace, Elise Newman
 */
public class Purchasable implements Cloneable {
    private String name;
    private int purchasePrice;
    private int sellPrice;
    private String description;

    /**
     * Constructor which is invoked by all children of Purchasable.
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

    /**
     * Get name of the Purchasable object
     * @return name
     */
    public String getName() { return name; }

    /**
     * Set name
     * @param name Name of Purchasable object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates clone of the Purchasable object, and throws a RuntimeException if the clone is not supported.
     * @return clone of the Purchasable object
     */
    public Purchasable cloneSelf() {
        try {
            return (Purchasable) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
}
