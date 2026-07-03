package laterunner.model.shop;

import laterunner.model.saving.FileManager;
import laterunner.model.user.User;
/**
 * The class in witch is implemented the Shop.
 *
 */
public final class Shop {

    private static final int INITIAL_LIFECOST = 1000;
    private static final int INITIAL_SPEEDCOST = 3000;
    private static final double LIFE_MUL = 1.5;
    private static final Shop SINGLETON = new Shop();
    private int lifeCost;
    private int speedCost;


    private Shop() {
            this.lifeCost = INITIAL_LIFECOST;
            this.speedCost = INITIAL_SPEEDCOST;
            }

    /**
     * Get the only instance of Shop. 
     * 
     * @return
     *          the only instance of Shop
     */
    public static Shop getShop() {
            return SINGLETON;
    }

    /**
     * The methot to reset the Shop features.
     * 
     */
    public void reset() {
            this.lifeCost = INITIAL_LIFECOST;
            this.speedCost = INITIAL_SPEEDCOST;
    }

    /**
     * The method in witch you can buy a life.
     * 
     */
    public void buyLife() {
            if (this.isEnableLife()) {
            User.getUser().setMoney(User.getUser().getMoney() - lifeCost);
            User.getUser().setUserLives(User.getUser().getUserLives() + 1);
            this.lifeCost = (int) (lifeCost * LIFE_MUL);
            FileManager.saveToFile();
            } 
    }
    /**
     * The method in witch you can increase your speed.
     * 
     */
    public void buySpeed() {
            if (this.isEnableSpeed()) {
            User.getUser().setMoney(User.getUser().getMoney() - speedCost);
            User.getUser().setSpeedMul(User.getUser().getSpeedMul() + 0.5);
            this.speedCost = speedCost * 2;
            FileManager.saveToFile();
            } 
    }
    /**
     * Return the actual life cost.
     * 
     * @return
     *          the life cost
     */
    public int getLifeCost() {
            return lifeCost;
    }
    /**
     * Get the actual speed cost.
     * 
     * @return
     *          the speed cost
     */
    public int getSpeedCost() {
            return speedCost;
    }
    /**
     * Set the life cost to i.
     * 
     * @param i
     *          the new life cost
     */
    public void setLifeCost(final int i) {
            lifeCost = i;
    }

    /**
     * Set the speed cost to i.
     * 
     * @param i
     *          the new speed cost
     */
    public void setSpeedCost(final int i) {
            speedCost = i;
    }

    /**
     * Check if is possible buy a life.
     * 
     * @return
     *              true if is possible buy a life
     */
    public boolean isEnableLife() {
            return User.getUser().getMoney() - lifeCost >= 0;
    }

    /**
     * Check if is possible buy speed.
     * 
     * @return
     *          true if is possible buy speed
     */
    public boolean isEnableSpeed() {
            return User.getUser().getSpeedMul() < 2 && User.getUser().getMoney() - speedCost >= 0;
    }
}
