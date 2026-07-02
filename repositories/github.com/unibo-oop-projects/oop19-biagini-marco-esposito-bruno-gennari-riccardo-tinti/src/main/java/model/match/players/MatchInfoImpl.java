package model.match.players;

import javafx.util.Pair;
import model.enums.PlayerNumber;

/**
 * Concrete implementation of MatchInfo.
 */
public final class MatchInfoImpl implements MatchInfo {

    private final Pair<Integer, Integer> size;
    private final PlayerInfo playerOne;
    private final PlayerInfo playerTwo;
    private final int shipNumber;

    /**
     * constructor of this class.
     * @param size - <rows,columns> size of the playing field
     * @param shipNumber - number of ship (for each player)
     * @param playerOne - player one's info
     * @param playerTwo - player two's info
     */
    public MatchInfoImpl(final Pair<Integer, Integer> size, final int shipNumber, final PlayerInfo playerOne, final PlayerInfo playerTwo) {
       this.size = size;
       this.shipNumber = shipNumber;
       this.playerOne = playerOne;
       this.playerTwo = playerTwo;
    }

    @Override
    public Pair<Integer, Integer> getFieldSize() {
        return size;
    }

    @Override
    public int getShipsNumber() {
        return shipNumber;
    }

    @Override
    public PlayerInfo getPlayerInfo(final PlayerNumber playerNumber) {
        return playerNumber.equals(PlayerNumber.PLAYER_ONE) ? playerOne : playerTwo;
    }

}
