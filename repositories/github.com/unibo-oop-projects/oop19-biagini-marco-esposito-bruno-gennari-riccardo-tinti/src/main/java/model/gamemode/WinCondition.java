package model.gamemode;

/**
 * This interface offers methods to check whether a match is over, according to the selected gamemode.
 */
public interface WinCondition {

    /**
     * this method checks whether the player has won the match.
     * @param playerHits - how many hits the player has scored.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    boolean isMatchOver(int playerHits, int opponentRemainingShips);

    /**
     * @param gameMode - the new gameMode
     */
    void setGameMode(GameMode gameMode);
}
