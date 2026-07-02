package model.gamemode;

import java.util.Optional;

/**
 * Concrete implementation of class WinCondition.
 */
public final class WinConditionImpl implements WinCondition {

    private final int maxShips = 5;
    private Optional<GameMode> gameMode;

    @Override
    public boolean isMatchOver(final int playerHits, final int opponentRemainingShips) {
        if (isDataValid(playerHits, opponentRemainingShips)) {
            return gameMode.isPresent() ? gameMode.get().isMatchOver(playerHits, opponentRemainingShips) : false;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        this.gameMode = Optional.ofNullable(gameMode);
    }

    private boolean isDataValid(final int playerHits, final int opponentRemainingShips) {
        return checkHit(playerHits) && checkShip(opponentRemainingShips);
    }

    private boolean checkHit(final int playerHits) {
        return playerHits > -1;
    }

    private boolean checkShip(final int opponentRemainingShips) {
        return opponentRemainingShips > -1 && opponentRemainingShips <= maxShips;
    }

}
