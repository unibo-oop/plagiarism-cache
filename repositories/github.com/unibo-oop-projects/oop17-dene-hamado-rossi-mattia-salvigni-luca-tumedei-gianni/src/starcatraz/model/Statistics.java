package starcatraz.model;

import java.time.LocalTime;

/**
 * Keeps track of the game's statistics.
 */
public interface Statistics {

    /**
     * @return the number of victories achieved
     */
    int getVictories();

    /**
     * @return the number of defeats achieved
     */
    int getDefeats();

    /**
     * @return the number of game played
     */
    int getPlayedGames();

    /**
     * @return the game won in the shortest time
     */
    LocalTime getGameTimeRecord();

    /**
     * @return the maximum number of card of the same color played in a game
     */
    int getCardStreak();

    /**
     * @return the number of robot defeated
     */
    int getDefeatedRobotsCount();

    /**
     * @return the lowest number of cards used to win
     */
    int getVictoryWithFewestCards();

    /**
     * Set the number of victories.
     * 
     * @param victories, number of victories to set
     */
    void setVictories(int victories);

    /**
     * @param the number of defeats to set
     */
    void setDefeats(int defeats);

    /**
     * @param the number of game played to set
     */
    void setPlayedGames(int gameplayed);

    /**
     * @param the time of the game won in the shortest time to set
     */
    void setGameTimeRecord(String gametime);

    /**
     * @param the maximum number of card of the same color played in a game to set
     */
    void setCardStreak(int cardstreak);

    /**
     * @param the
     *            number of robots defeated to set
     */
    void setDefeatedRobotsCount(int robotsdefeated);

    /**
     * @param the fewest number of cards left in a victory to set
     */
    void setVictoryWithFewestCards(int cardsleft);

    /**
     * Increment the number of victories.
     */
    void incrementVictories();

    /**
     * Increment the number of defeats.
     */
    void incrementDefeats();

    /**
     * Increment the number of game played.
     */
    void incrementPlayedGames();
}
