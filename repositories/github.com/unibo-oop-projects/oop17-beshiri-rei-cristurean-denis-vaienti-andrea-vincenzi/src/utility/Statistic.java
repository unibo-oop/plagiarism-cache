package utility;

/**
 * Interface that represent user statistic that need to be shown.
 *
 */
public interface Statistic {

    /**
     * Getter for player points.
     * @return player points.
     */
    int getPoints();

    /**
     * Getter for player bullet range.
     * @return bullet range.
     */
    double getBulletRange();

    /**
     * Getter for player bullet damage.
     * @return bullet damage.
     */
    int getBulletDamage();

    /**
     * Getter for player velocity.
     * @return player velocity.
     */
    double getVel();
}
