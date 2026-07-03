package model.exceptions;

/**
 * 
 * Exception thrown when the game finish because one of the player won the game.
 *
 */
public class GameOverException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7909083161469734117L;

    /**
     * 
     * @param winner
     *          the name of the winning player.
     */
    public GameOverException(final String winner) {
        super(winner);
    }

}
