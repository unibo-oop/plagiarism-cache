package model.inanimated;

/**
 * Interface that represents heart in the shop.
 *
 */
public interface Heart extends Inanimated {

    /**
     * @return the cost of the Heart.
     */
    int getCost();

    /**
     * @return the amount of life that the heart gives.
     */
    int getLife();
}
