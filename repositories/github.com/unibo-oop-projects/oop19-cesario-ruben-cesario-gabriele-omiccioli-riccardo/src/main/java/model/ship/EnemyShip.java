package model.ship;

/**
 *  Models an enemy ship: a SpaceShip which will grant a certain amount of
 *  score points upon destruction.
 */
public interface EnemyShip extends SpaceShip {

    /**
     * Returns the amount of points this ship will grant upon destruction.
     * @return the amount of points this ship will grant upon destruction.
     */
    int getScorePoints();

}
