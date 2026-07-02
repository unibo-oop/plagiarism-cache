package model;

/**
 * This is a functional interface, created for the propriety of any pickable object.
 */
public interface PowerUPAction {

    /**
     * execute the object's propriety.
     * @param p : player that takes the power up
     */
    void execute(Player p);
}
