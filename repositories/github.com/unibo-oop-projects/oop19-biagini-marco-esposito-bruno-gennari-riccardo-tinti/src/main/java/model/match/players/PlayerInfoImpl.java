package model.match.players;

import model.enums.PlayerNumber;
import model.enums.PlayerType;

/**
 * Concrete implementation of PlayerInfo interface.
 */
public final class PlayerInfoImpl implements PlayerInfo {

    private final String username;
    private final PlayerType type;
    private final PlayerNumber number;

    /**
     * Constructor of this class.
     * @param username - player's username
     * @param type - player type
     * @param number - player number
     */
    public PlayerInfoImpl(final String username, final PlayerType type, final PlayerNumber number) {
        this.username = username;
        this.type = type;
        this.number = number;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public PlayerType getType() {
        return type;
    }

    @Override
    public PlayerNumber getNumber() {
        return number;
    }
}
