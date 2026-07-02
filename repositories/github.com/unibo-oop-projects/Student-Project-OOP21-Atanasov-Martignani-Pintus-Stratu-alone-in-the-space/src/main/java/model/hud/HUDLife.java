package model.hud;

/**
 * This interface show the methods to handle HUD life points system.
 */
public interface HUDLife {

    /**
     * @return life points.
     */
    int getLifePoints();

    /**
     * @return game status.
     */
    boolean getGameStatus();

    /**
     * update the HUD with the new stats.
     * 
     * @param lifePoints
     */
    void update(int lifePoints);
}
