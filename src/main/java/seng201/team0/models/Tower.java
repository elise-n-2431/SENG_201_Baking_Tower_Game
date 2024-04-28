package seng201.team0.models;

/**
 * Tower is a subclass of Purchasable.
 * It inherits the purchasePrice, sellPrice and description variables, and their
 * getters.
 *
 */

public class Tower extends Purchasable {
    private String resourceType; /* WHAT TYPES OF RESOURCE DO WE WANT? */
    private int resourceAmount = 0; /* Default to zero. RENAME THIS? */
    private int reloadSpeed; /* How often per second the tower donates resources */
    private int level = 1; /* Towers can be levelled up, but start from level 1. */
    private boolean broken = false; /* Towers have chance of breaking after each round */

    /* Default constructor for Tower objects. */
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

    /* Returns string representation of Tower object */
    public String toString() {
        return "Tower{description=" + getDescription() + ", purchasePrice=" + getPurchasePrice()
                + ", sellPrice=" + getSellPrice() + ", resourceType: " + resourceType
                + ", resourceAmount: " + resourceAmount + ", reloadSpeed: " + reloadSpeed + "}";

    }

    public boolean isBroken() {
        /* Getter function to check whether a tower is broken. Returns true or false. */
        return broken;
    }

    public void setBroken(boolean broken) {
        /* Change state of the tower between broken and unbroken */
        this.broken = broken;
    }

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
