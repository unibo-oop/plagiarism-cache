package model.statistic;

/**
 * 
 *fully manages a match.
 *the statistic match of both players.
 */
public interface StatisticMatchManager {
/**
 * 
 * @return the match statistic of the player one
 */
    StatisticMatch getPlayerOneStatisticMatch();

    /**
     * 
     * @return the match statistic of the player two
     */
    StatisticMatch getPlayerTwoStatisticMatch();

    /**
     * method that will be called every time something happens during the game.
     */
    void update();

}
