package giocoscudetto.model.api;

/**
 * Represents a pawn used on the game board. 
 */
public interface Pawn {

    /**
     * Moves the pawn by the numbwer of steps.
     * 
     * @param steps number of steps to move.
     */
    void changePosition(int steps);

    /**
     * Returns the current position of the pawn on the board.
     * 
     * @return the current position of the pawn like a int.
     */
    int getPosition();

    /**
     * Sets the position of the pawn on the board.
     * 
     * @param position the position to set for the pawn.
     */
    void setPosition(int position);

    /**
     * Returns the color of the pawn.
     * 
     * @return a string representing the color of the pawn.
     */
    int getPawnRGB();
}
