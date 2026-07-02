package starcatraz.model.game;

/**
 * Possible statuses of a game.
 */
public enum GameStatus {
    LOST ("You lost the game"),
    WON ("You won the game"),
    IN_PROGRESS ("The game is in progress"),
    PAUSED ("The game is in pause"),
    UNDER_ATTACK ("You are being attacked by a robot"),
    USING_CHIP ("You are using a chip"),
    ACTIVATING_ROCKET ("You are deciding wether to activate a rocket");

    private final String description;

    /**
     * Constructor for GameStatus
     * @param description
     */
    GameStatus(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
