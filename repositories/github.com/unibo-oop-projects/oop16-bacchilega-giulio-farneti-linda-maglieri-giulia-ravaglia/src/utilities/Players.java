package utilities;

import model.logic.GameLogic;

/**
 * @author : Giulio Bacchilega
 */
public enum Players {

    /**
     * 
     */
    playerWhite, playerBlack;

    /**
     * Return the opposite player of p.
     * @return Player
     */
    public static Players opposite() {
        if (GameLogic.getLog().getActualPlayer().equals(playerBlack)) {
            return playerWhite;
        }
        return playerBlack;
    }
}
