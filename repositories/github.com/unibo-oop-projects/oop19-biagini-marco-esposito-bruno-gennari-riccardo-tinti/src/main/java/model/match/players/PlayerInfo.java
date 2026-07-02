package model.match.players;

import model.enums.PlayerNumber;
import model.enums.PlayerType;

/**
 * This class contains info on a player during a match.
 */
public interface PlayerInfo {
    /**
     * @return player's username
     */
    String getUsername();

    /**
     * @return player's type
     */
    PlayerType getType();

    /**
     * @return player's number.
     */
    PlayerNumber getNumber();
}
