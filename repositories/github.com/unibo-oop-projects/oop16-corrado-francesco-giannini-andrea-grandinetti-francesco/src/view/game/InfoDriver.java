package view.game;

/**
 * 
 *
 */
public interface InfoDriver {

    /**
     * Getter.
     * @return name of player
     */
    String getName();

    /**
     * Getter.
     * @return name of driver
     */
    String getDriverName();

    /**
     * Getter.
     * @return name of team
     */
    String getTeam();

    /**
     * Getter.
     * @return type of tyre
     */
    String getTyreType();

    /**
     * Getter.
     * @return driver's position
     */
    String getPosition();

    /**
     * Getter.
     * @return decay of tyre
     */
    String getTyreDecay();

    /**
     * Getter.
     * @return point of shell
     */
    String getShellPoint();
}
