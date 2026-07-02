package controls;

import gamegraphics.ViewGame;
import movements.Movements;
import speed.Speed;

/**
 * Interface for the Logics of the KeyListener, with methods for each action.
 */
public interface TetrisKeyListenerLogics {
    /**
     * Moves the piece to the Left.
     * 
     */
    void moveLeft();

    /**
     * Moves the piece to the Right.
     * 
     */
    void moveRight();

    /**
     * Rotates the piece Clockwise.
     * 
     */
    void rotateClockwise();

    /**
     * Rotates the piece CounterClockwise.
     * 
     */
    void rotateCounterClockwise();

    /**
     * Activates the Holdbox.
     * 
     */
    void useHoldbox();

    /**
     * Begins the Acceleration of the piece.
     * 
     */
    void beginAcceleration();

    /**
     * Ends the Acceleration of the piece.
     * 
     */
    void endAcceleration();

    /**
     * Uses the Instant Positioning on the piece.
     * 
     */
    void instantPositioning();

    /**
     * Mutes the Game volume.
     * 
     */
    void muteVolume();

    /**
     * Pauses the Game.
     * 
     */
    void pauseGame();

    /**
     * Uses the Booster 1 (cancels all lines with 80% or more blocks in it).
     * 
     */
    void cancelMultipleLines();

    /**
     * Uses the Booster 2 (cancels the top line).
     * 
     */
    void cancelTopLine();

    /**
     * Uses the booster 3: switch the current piece on the board of the game with a
     * 1x1 piece.
     */
    void switchPiece();

    /**
     * Sets the Movements.
     * 
     * @param movements : Movements Object
     */
    void setMovements(Movements movements);

    /**
     * Sets the View.
     * 
     * @param view : ViewGame Object
     */
    void setView(ViewGame view);

    /**
     * Sets the Speed.
     * 
     * @param speed : Speed Object
     */
    void setSpeed(Speed speed);
}
