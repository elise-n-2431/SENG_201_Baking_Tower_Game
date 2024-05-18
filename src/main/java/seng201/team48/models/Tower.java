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
    private String resourceType; /* WHAT TYPES OF RESOURCE DO WE WANT? */
    private int resourceAmount = 0; /* Default to zero. RENAME THIS? */
    private int reloadSpeed; /* How often per second the tower donates resources */
    private int level = 1; /* Towers can be levelled up, but start from level 1. */
    private boolean broken = false; /* Towers have chance of breaking after each round */

    /**
     * Default constructor for Tower objects.
     * @param name Name of the tower object.
     * @param description Description of the tower.
     * @param purchasePrice Purchase price of the tower, which the user pays from their bank balance
     * @param sellPrice Sell price of tower. Once sold, the user is remunerated the sellPrice amount into their bank.
     * @param resourceType Type of resource with which the tower can fill cart objects during a round.
     * @param resourceAmount Amount of resources to load into carts per action.
     * @param reloadSpeed Amount of time (seconds) it takes after a cart fill before the tower can fill carts again.
     */
    public Tower(String name, String description, int purchasePrice, int sellPrice, String resourceType, int resourceAmount, int reloadSpeed) {
        super(name, description, purchasePrice, sellPrice); /* Purchasable attributes */
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.reloadSpeed = reloadSpeed;
    }

    /* Blank constructor for testing purposes. */
    public Tower() {
        super("myTower", "For testing", 5, 3); /* Purchasable attributes */
        this.resourceType = "Eggs";
        this.resourceAmount = 5;
        this.reloadSpeed = 1;
    }

    /** Returns string representation of Tower object. */
    public String toString() {
        return "Tower{description=" + getDescription() + ", purchasePrice=" + getPurchasePrice()
                + ", sellPrice=" + getSellPrice() + ", resourceType: " + resourceType
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
        this.level = level;
    }

    /**
     * Get tower's reload speed.
     *
     * @return reloadSpeed
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Set tower's reload speed.
     * @param reloadSpeed
     */
    public void setReloadSpeed(int reloadSpeed) {
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

    public static void main(String[] args) {
        /* Testing of Tower object creation */
        Tower myTower = new Tower();
        System.out.println(myTower);
        System.out.println(myTower.isBroken());
        myTower.setBroken(true);
        System.out.println(myTower.isBroken());
        RepairKit myKit = new RepairKit("hihi");
        myKit.repairTower(myTower);
        System.out.println(myTower.isBroken());
    }
}
