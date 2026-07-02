package zombieversity.model.score;

/**
 *  Simple Plain Old Java Object to handle all the information needed to represent a score of this game. 
 */
public interface Score {

    /**
     * @return
     *          The number of kills done during the game.
     */
    int getKills();

    /**
     * 
     * @param kills
     *          The total number of kills done during the game.
     */
    void setKills(int kills);

    /**
     * Used to add a single kill.
     */
    void addKill();

    /**
     * @return
     *          The nickname of who played the game.
     */
    String getNickname();

    /**
     * @param nickname
     *          The nickname of the player that played the game.
     */
    void setNickname(String nickname);

    /**
     * @return
     *          A string representing the amount of time played. It is formatted as '00:00:00'.
     */
    String getTimePlayed();

    /**
     * @param time
     *          The amount of time played as a string.
     */
    void setTimePlayed(String time);

    /**
     * Used to internally set the time played.
     */
    void setGameEnd();

    /**
     * @return
     *          The position of this score relative to the others inside the leaderboard.
     */
    int getPosition();

    /**
     * @param position
     *          The position of this Score in the leaderboard.
     */
    void setPosition(int position);


}
