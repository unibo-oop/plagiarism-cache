package model.match.players;

import java.util.Optional;

import model.enums.PlayerNumber;

/**
 *  Implementation of CurrentPlayer.
 */
public class CurrentPlayerImpl implements CurrentPlayer {

    private Optional<PlayerNumber> current = Optional.empty();

    /**
     * @return the current player
     */
    public Optional<PlayerNumber> getCurrentPlayer() {
        return current;
    }

    /**
     * @param playerNumber - the new current player
     */
    public void setCurrentPlayer(final PlayerNumber playerNumber) {
        current = Optional.ofNullable(playerNumber);
    }

    /**
     * sets the next player as current.
     */
    public void nextPlayer() {
        current = current.isPresent() 
                ? Optional.of(current.get().equals(PlayerNumber.PLAYER_ONE)
                        ? PlayerNumber.PLAYER_TWO
                        : PlayerNumber.PLAYER_ONE) 
                : Optional.empty();
    }

}
