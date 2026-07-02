package gamemanagement;

/**
 * This class models the game management of some particular events such as game
 * over, the speed change, the elimination of rows and the place of a piece.
 * It also creates an instance of TimerController and has the methods to control
 * the game (start, stop and resume).
 */
public interface GameManagement {
    /**
     * Starts the game.
     */
    void startPlay();

    /**
     * Stops the game.
     */
    void stopPlay();

    /**
     * Resumes the game.
     */
    void resumePlay();

    /**
     * Manages the game over.
     */
    void gameOver();

    /**
     * Manages speed change.
     */
    void speedChange();

    /**
     * Manages the elimination of rows.
     */
    void eliminationRow();

    /**
     * Manages a placed piece.
     */
    void placePiece();
}
