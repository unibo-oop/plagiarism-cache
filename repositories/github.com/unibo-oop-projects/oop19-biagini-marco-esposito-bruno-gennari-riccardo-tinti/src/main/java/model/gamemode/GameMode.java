package model.gamemode;

/**
 * This enum models the win conditions for a match, selectable by the player in the matchSettings menu.
 */
public enum GameMode {

    /**
     * Sink all enemy ships to win.
     */
    CLASSIC("Classic", "Sink all enemy ships to win.") {
        @Override
        protected boolean isMatchOver(final int playerHits, final int opponentRemainingShips) {
            return opponentRemainingShips == 0;
        }
    },

    /**
     * Be the first player to hit a ship.
     */
    FIRST_HIT("Score a hit", "Be the first player to score a hit.") {
        @Override
        protected boolean isMatchOver(final int playerHits, final int opponentRemainingShips) {
            return playerHits > 0;
        }
    },

    /**
     * Be the first player to sink 3 ships.
     */
    THREE_HITS("Score three Hits", "Be the first player to score 3 hits.") {
        @Override
        protected boolean isMatchOver(final int playerHits, final int opponentRemainingShips) {
            return playerHits > 2;
        }
    };

    private final String name;
    private final String description;

    GameMode(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * this method checks whether the player has won the match.
     * @param playerHits - how many hits the player has scored.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    protected abstract boolean isMatchOver(int playerHits, int opponentRemainingShips);

    /**
     * @return the GameMode's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the GameMode's description.
     */
    public String getDescription() {
        return description;
    }

}
