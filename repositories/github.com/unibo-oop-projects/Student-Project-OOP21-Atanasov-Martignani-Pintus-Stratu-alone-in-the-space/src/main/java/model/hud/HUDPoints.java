package model.hud;

/**
 * This interface show the methods to handle HUD points system.
 */
public interface HUDPoints {

    /**
     * @return points.
     */
    int getPoints();

    /**
     * Set the points to the chosen value.
     * 
     * @param value amount of points
     */
    void setPoints(int value);

    /**
     * Update points.
     *
     * @param points
     */
    void update(int points);

}
