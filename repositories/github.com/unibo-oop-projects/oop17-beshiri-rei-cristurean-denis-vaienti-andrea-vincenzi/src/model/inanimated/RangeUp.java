package model.inanimated;

/**
 * Interface that represent power up item for player bullet range.
 */
public interface RangeUp extends Inanimated {

    /**
     * Cost of the power up.
     * 
     * @return the cost of the power up.
     */
    int getCost();

    /**
     * The range to increase.
     * 
     * @return the range to increase.
     */
    double getRangeUp();
}
