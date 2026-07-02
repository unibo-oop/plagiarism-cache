package justanotherchessgame.model;

import justanotherchessgame.util.Point;

/**
 * Abstract class representing each chess Piece.
 */
public abstract class Piece {
    protected int movedCount;
    protected boolean color;
    protected Point point;

    /**
     * Constructor of Piece.
     * @param color is the color of the Piece.
     * @param x is the x coordinate of the Piece.
     * @param y is the y coordinate of the Piece.
     */
    public Piece(final boolean color, final int x, final int y) {
        this.color = color;
        movedCount = 0;
        point = new Point(x, y);
    }

    /**
     * Getter of the x coordinate.
     * @return is the x coordinate.
     */
    public int getX() {
        return point.getX();
    }
    /**
     * Getter of the y coordinate.
     * @return the y coordinate.
     */
    public int getY() {
        return point.getY();
    }

    /**
     * Setter of the Piece point.
     * @param p is the Point where the piece is located.
     */
    public void setPoint(final Point p) {
        point = new Point(p);
    }

    /**
     * Getter of the Piece point.
     * @return is the Point where the piece is located.
     */
    public Point getPoint() {
        return new Point(point.getX(), point.getY());
    }

    /**
     * Flag indicating if the Piece has Moved.
     * @return a boolean indicating if the piece has moved.
     */
    public boolean hasMoved() {
        return movedCount > 0;
    }

    /**
     * Method which increases the move counter of the piece by 1.
     */
    public void increaseMoved() {
        movedCount++;
    }
    /**
     * Method which decreases the move counter of the piece by 1.
     */
    public void decreaseMoved() {
        movedCount--;
    }

    //TODO: Color's always been a boolean, so maybe refer to this with a different method name
    /**
     * Method which returns the color of the piece as a string.
     * @return the color of the piece as a string.
     */
    public String getColor() {
        if (color) {
            return "white";
        } else {
            return "black";
        }
    }

    /**
     * Method which returns the color of the piece as a boolean.
     * @return the Piece color as a boolean.
     */
    public boolean isWhite() {
        return color;
    }

    /**
     * Method which returns a string representing the piece.
     * @return a string representing the piece.
     */
    public String toString() {
        return (getName() + " " + getColor());
    }

    /**
     * Method which checks if the point is on board.
     * @return a boolean True if the point is on the chessboard, False otherwise.
     */
    protected boolean onBoard() {
        return point.onBoard();
    }

    /**
     * Method that checks if a Pieces MoveList includes a certain point.
     * @param to is the destination point to check.
     * @return a boolean indicating if the Piece can move to the given point.
     */
    public boolean canEatTo(final Point to) {
        // Copies current point
        for (final Moves m : this.getMoves()) {
            Point current = new Point(point);
            if (usesSingleMove()) {
                current = Point.sum(current, new Point(m.getX(), m.getY()));
                if (current.onBoard() && current.equals(to)) {
                        return true;
                }
            } else {
                while (current.onBoard()) {
                    current = Point.sum(current, new Point(m.getX(), m.getY()));
                    if (current.onBoard() && current.equals(to)) {
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method which hashCodes a piece.
     * @return the hash of the piece as an int.
     */
    @Override
    public int hashCode() {
        int result = 0;
        switch (this.getName()) {
        case "King":
        //System.out.println("case1");
            result += 1;
            break;
        case "Queen":
        //System.out.println("case2");
            result += 2;
            break;
        case "Bishop":
        //System.out.println("case3");
            result += 3;
            break;
        case "Knight":
        //System.out.println("case4");
            result += 4;
            break;
        case "Rook":
        //System.out.println("case5");
            result += 5;
            break;
        case "Pawn":
        //System.out.println("case6");
            result += 6;
            break;
        default:
            break;
        }
        if (this.isWhite()) {
            result += 20;
        } else {
            result += 30;
        }
        return result;
    }

    /**
     * Override Method which confronts a given piece to another.
     * @param obj is the object to confront.
     * @return a boolean which indicates if the Piece contains the same information as the given object.
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        } else {
            return this.hashCode() == obj.hashCode();
        }
    }

    /**
     * Method which returns the list possible moves of the piece.
     * @return the list possible moves of the piece.
     */
    protected abstract Moves[] getMoves();
    /**
     * Method which returns a boolean indicating if the piece can move by only one square.
     * @return a boolean indicating if the piece can move by only one square.
     */
    protected abstract boolean usesSingleMove();
    /**
     * Method which returns the name of the piece as a string.
     * @return a string indicating the name of the piece.
     */
    public abstract String getName();
}
