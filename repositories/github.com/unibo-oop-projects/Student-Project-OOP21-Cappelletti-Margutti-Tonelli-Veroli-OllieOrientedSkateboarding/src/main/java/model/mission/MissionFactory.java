package model.mission;

/**
 * 
 * Interface of a factory of {@link Mission}.
 *
 */
public interface MissionFactory {

    /**
     * Creates a random {@link Mission}.
     * @return a random {@link Mission}.
     */
    Mission createMission();

    /**
     * Creates a {@link Mission} to do with collecting coins.
     * @return a {@link Mission} to do with collecting coins.
     */
    Mission createCollectedCoinMission();

    /**
     * Creates a {@link Mission} to do with reaching a distance.
     * @return a {@link Mission} to do with reaching a distance.
     */
    Mission createDistanceMission();

    /**
     * Creates a {@link Mission} to do with number of player's jump.
     * @return a {@link Mission} to do with number of player's jump.
     */
    Mission createNumberOfJumpMission();

}
