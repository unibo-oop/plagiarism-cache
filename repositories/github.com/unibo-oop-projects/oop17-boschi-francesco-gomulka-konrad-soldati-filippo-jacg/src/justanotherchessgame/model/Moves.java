package justanotherchessgame.model;

/**
 * Enum representing moves of each piece.
 */
public enum Moves {

    UP(0, 1), 
    DOWN(0, -1), 
    LEFT(-1, 0), 
    RIGHT(1, 0), 
    UP_LEFT(-1, 1), 
    UP_RIGHT(1, 1), 
    DOWN_LEFT(-1, -1), 
    DOWN_RIGHT(1, -1), 

    UP2(0, 2), 
    DOWN2(0, -2), 

    KNIGHT_UPL(-1, 2), 
    KNIGHT_UPR(1, 2), 
    KNIGHT_LEFTU(-2, 1), 
    KNIGHT_LEFTD(-2, -1), 
    KNIGHT_RIGHTU(2, 1), 
    KNIGHT_RIGHTD(2, -1), 
    KNIGHT_DOWNL(-1, -2), 
    KNIGHT_DOWNR(1, -2);

    private int x;
    private int y;

    /**
     * Getter of the x of the move.
     * @return an int representing the x of the move.
     */
    public int getX() {
        return x; 
    }

    /**
     * Getter of the y of the move.
     * @return an int representing the y of the move.
     */
    public int getY() {
        return y; 
    }
    /**
     * Setter of the x of the move.
     * @param x is an int representing the x of the move.
     */
    protected void setX(final int x) {
        this.x = x; 
    }

    /**
     * Setter of the y of the move.
     * @param y is an int representing the y of the move.
     */
    protected void setY(final int y) {
        this.y = y; 
    }

    /**
     * Constructor of the move.
     * @param x is the x of the move.
     * @param y is the y of the move.
     */
    Moves(final int x,  final int y) {
        this.x = x;
        this.y = y;
    }

}
