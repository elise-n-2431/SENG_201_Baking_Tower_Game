import seng201.team48.UpgradeManager;

public class UpgradeManagerTest {
    UpgradeManager upgradeManager;

    void setUp() {
        upgradeManager.generateUpgradesForSale();
        System.out.println(upgradeManager.getUpgradesForSale());
    }

//    public static void main(String[] args) {
//        UpgradeManager upgradeManager = new UpgradeManager();
//        upgradeManager.generateUpgradesForSale();
//        System.out.println(upgradeManager.getUpgradesForSale());
//    }
}
