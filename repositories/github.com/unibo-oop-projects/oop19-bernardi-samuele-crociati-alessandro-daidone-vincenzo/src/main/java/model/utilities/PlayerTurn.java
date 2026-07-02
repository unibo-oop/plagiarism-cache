/**
 * 
 */
package model.utilities;

/**
 *
 */
public enum PlayerTurn {
    /**
     * Represent player one's turn.
     */
    PLAYER_ONE(Constants.DEFAULT_PLAYER_ONE_TEXT),
    /**
     * Represent player two's turn.
     */
    PLAYER_TWO(Constants.DEFAULT_PLAYER_TWO_TEXT);

    private String text;

    PlayerTurn(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    private static class Constants {
        public static final String DEFAULT_PLAYER_ONE_TEXT = "Player One's turn";
        public static final String DEFAULT_PLAYER_TWO_TEXT = "Player Two's turn";
    }
}
