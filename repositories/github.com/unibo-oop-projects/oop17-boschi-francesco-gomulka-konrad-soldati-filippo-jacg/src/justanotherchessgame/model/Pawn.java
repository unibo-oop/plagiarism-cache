package justanotherchessgame.model;

import java.util.ArrayList;

import justanotherchessgame.util.Point;

/**
 * Class relative to a Pawn Piece.
 */
public class Pawn extends Piece {

    /**
     * Constructor of the Pawn, same as piece.
     * @param color is the color of the piece.
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece
     */
    public Pawn(final boolean color, final int x, final int y) {
        super(color, x, y);
    }

    @Override
    protected final Moves[] getMoves() {
        Moves[] pawnm = {};

        //pawn moves depend on the color
        final ArrayList<Moves> temp = new ArrayList<Moves>();

        if (this.color) {

            //in order to capture other pieces
            temp.add(Moves.UP_RIGHT);
            temp.add(Moves.UP_LEFT);
            /*
             * TODO: reasonable way to compute and stop "path-like" moves, 
             * such as Moves.UP2 or Moves.DOWN2
             * It's not behaving correctly because (for pawns) it currently stop looking
             * for any kind of moves after it finds an obstacle, but you can still move 
             * diagonally in that case. Changing the order of the moves properly fixed this,
             * but it's meant just a temporary workaround.
             */
            temp.add(Moves.UP);
            //first move
            if (!hasMoved()) {
                temp.add(Moves.UP2);
            }
        } else {
            //in order to capture other pieces
            temp.add(Moves.DOWN_RIGHT);
            temp.add(Moves.DOWN_LEFT);
            /*
             * Read the same comment regarding white-colored pawns
             */
            temp.add(Moves.DOWN);
            //first move
            if (!hasMoved()) {
                temp.add(Moves.DOWN2);
            }
        }

        pawnm = temp.toArray(pawnm);
        return pawnm;
    }

    /**
     * Override of the canEatTo method from piece, since the logic behind a pawn is different from each other piece.
     * @param to is the end point to check.
     */
    @Override
    public boolean canEatTo(final Point to) {
        final int deltaY = color ? 1 : -1;
        final Point delta = Point.subtract(to, point);
        //diff(Y) = deltaY, diff(X) = +-1
        return delta.getY() == deltaY && (delta.getX() == 1 || delta.getX() == -1);
    }

    @Override
    protected final boolean usesSingleMove() {
        return true;
    }

    @Override
    public final String getName() {
        return "Pawn";
    }
}
