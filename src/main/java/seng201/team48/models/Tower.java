package seng201.team48.models;

/**
 * This class contains the blueprint for all in-game 'Tower' objects.
 * In-game, six tower objects are created, all with different properties.
 * * Tower is a subclass of Purchasable.
 * It inherits the purchasePrice, sellPrice and description variables, and their
 * getters.
 * @author Hannah Grace, Elise Newman
 */

public class Tower extends Purchasable {
    private String resourceType;
    private int resourceAmount;
    private double reloadSpeed; /* Decimal value for progress bar increase (percent of 1) */
    private int level = 1; /* Towers can be levelled up, but start from level 1. */
    private boolean broken = false; /* Towers have chance of breaking after each round */

    /**
     * Default constructor for Tower objects.
     * @param description Description of the tower.
     * @param purchasePrice Purchase price of the tower, which the user pays from their bank balance
     * @param sellPrice Sell price of tower. Once sold, the user is remunerated the sellPrice amount into their bank.
     * @param resourceAmount Amount of resources to load into carts per action.
     * @param reloadSpeed Progress percent increase per second.
     */
    public Tower(String name, String description, int purchasePrice, int sellPrice, int resourceAmount, double reloadSpeed) {
        super(name, description, purchasePrice, sellPrice); /* Purchasable attributes */
        this.resourceType = name;
        this.resourceAmount = resourceAmount;
        this.reloadSpeed = reloadSpeed;
    }

    /** Returns string representation of Tower object. */
    public String toString() {
        return "Tower{description: " + getDescription() + ", purchasePrice: " + getPurchasePrice()
                + ", sellPrice: " + getSellPrice() + ", resourceType: " + resourceType
                + ", resourceAmount: " + resourceAmount + ", reloadSpeed: " + reloadSpeed + "}";

    }

    /**
     * Gets resource type of object.
     * @return resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Gets tower's current level.
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set level of tower
     * @param level Level of tower. Higher levels result in favourable increases to tower attributes.
     */
    public void setLevel(int level) {
        if (level <= 4) {
            this.level = level;
            this.reloadSpeed -= 0.005;
        }
    }

    /**
     * Get tower's reload speed.
     *
     * @return reloadSpeed
     */
    public double getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Set tower's reload speed.
     * @param reloadSpeed
     */
    public void setReloadSpeed(double reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    /**
     * Setter function for resource type of tower.
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Getter function for resourceAmount
     *
     * @return resourceAmount
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Set resourceAmount
     * @param resourceAmount Amount of resources to load into carts per action.
     */
    public void setResourceAmount(int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    /**
     * Boolean getter function to check whether a tower is broken. Returns true or false.
     * @return broken
     */
    public boolean isBroken() {
        return broken;
    }

    /**
     * Sets boolean broken. Changes state of tower between broken and not broken.
     * If a tower is broken, it cannot be used in a round until it is repaired by consuming a repair kit.
     * @param broken State of tower determining whether a tower in the player's inventory. can be used in a round.
     */
    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    /**
     * 'Adds' repair kit item to tower.
     * @param kit Repair kit which is consumed to repair the current tower.
     */
    public void addItem(RepairKit kit) {
        /* Repairs tower from a broken state */
        setBroken(false);
    }

}
