package model.match.players;

import javafx.util.Pair;
import model.enums.PlayerNumber;

/**
 * This interface offers methods to get info on the current match.
 */
public interface MatchInfo {

    /**
     * @return the <rows,columns> size of the playing field
     */
    Pair<Integer, Integer> getFieldSize();

    /**
     * @return the number of ships in the match (for each player)
     */
    int getShipsNumber();

    /**
     * @param playerNumber - number of the player
     * @return the player's info
     */
    PlayerInfo getPlayerInfo(PlayerNumber playerNumber);
}
