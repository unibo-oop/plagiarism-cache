package starcatraz.model;

/**
 * Challenges for the player.
 */
public interface Achievements {

    /**
     * @return if the "first victory" achievement has been achieved
     */
    boolean isFirstVictoryCompleted();

    /**
     * @return if the "first defeat" achievement has been achieved
     */
    boolean isFirstDefeatCompleted();

    /**
     * @return if the "first ten victories" achievement has been achieved
     */
    boolean isFirstTenVictoriesCompleted();

    /**
     * @return if the "first one hundred victories" achievement has been achieved
     */
    boolean isFirstHundredVictoriesCompleted();

    /**
     * @return if the "first fifty game played" achievement has been achieved
     */
    boolean isFiftyGamePlayedCompleted();

    /**
     * @return if the "win with half deck left" achievement has been achieved
     */
    boolean isWinWithHalfDeckLeftCompleted();

    /**
     * @return if the "first ten robot attack rejected with a rocket" achievement has been achieved
     */
    boolean isRejectTenRobotAttacksCompleted();

    /**
     * @return if the "win in four minutes" achievement has been achieved
     */
    boolean isWinInTwoMinutesCompleted();

    /**
     * @return if the "play six card of the same color in a row" achievement has been achieved
     */
    boolean isSixCardStreakCompleted();
}
