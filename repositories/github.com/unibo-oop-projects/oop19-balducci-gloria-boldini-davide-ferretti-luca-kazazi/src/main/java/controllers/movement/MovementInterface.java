package controllers.movement;

public interface MovementInterface {

    /**
     * @return up
     * 
     *         check up value
     * 
     */
    boolean isUp();

    /**
     * @param up
     * 
     *           set up value
     */
    void setUp(boolean up);

    /**
     * @return down
     * 
     *         check down value
     * 
     */
    boolean isDown();

    /**
     * @param down
     * 
     *             set up value
     * 
     */
    void setDown(boolean down);

    /**
     * @return right
     * 
     *         check right value
     * 
     */
    boolean isRight();

    /**
     * @param right
     * 
     *              set right value
     * 
     */
    void setRight(boolean right);

    /**
     * @return left
     * 
     *         check left value
     * 
     */
    boolean isLeft();

    /**
     * @param left
     * 
     *             set left value
     * 
     */
    void setLeft(boolean left);

    /**
     * 
     * method used to move the entities.
     */
    void moveEntity();

    /**
     * @param speed
     * 
     *              method to update player's speed
     */
    void updateSpeed(double speed);

    /**
     * @return speed
     * 
     *         get player speed
     */
    double getSpeed();

    /**
     * 
     * reset all direction variables to false.
     */
    void reset();

}
