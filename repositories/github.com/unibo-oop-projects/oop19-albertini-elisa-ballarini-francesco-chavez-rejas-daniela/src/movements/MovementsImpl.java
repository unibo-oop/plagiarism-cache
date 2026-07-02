package movements;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import gamelogic.Board;
import gamelogic.GameLogic;
import pair.Pair;
import piece.Piece;

/**
 * This class implements the interface {@link Movements}.
 */
public final class MovementsImpl implements Movements {
    private final GameLogic game;
    /*
     * These fields are use to revert the fix for the empty columns.
     */
    private int fixEmptyCol;
    private int leftBeforeFix;
    private Piece pieceFixed;
    private boolean isFixed;

    /**
     * Constructor of class MovementsImpl.
     * 
     * @param game is used to get the current piece and to check the
     *             correctness of the movement applied.
     */
    public MovementsImpl(final GameLogic game) {
        this.isFixed = false;
        this.game = game;
    }

    @Override
    public void rotateClockwise() {
        this.rotate(true);
    }

    @Override
    public void rotateCounterclockwise() {
        this.rotate(false);
    }

    @Override
    public void moveLeft() {
        this.move(-1);
    }

    @Override
    public void moveRight() {
        this.move(1);
    }

    @Override
    public boolean dropDown() {
        // in case the movement is not legal I save the old top
        final int oldTop = this.game.getCurrent().getTop();
        // top + 1
        this.game.getCurrent().setTop(this.game.getCurrent().getTop() + 1);
        if (this.game.isCurrentLegal()) {
            return true;
        } else {
            // it's not legal so I restore the old top
            this.game.getCurrent().setTop(oldTop);
            return false;
        }
    }

    /*
     * The algorithm used to rotate clockwise is: new_x = old_y + center_x -
     * center_y; new_y = center_x + center_y - old_x. The algorithm used to rotate
     * counterclockwise is: new_x = centre_x + centre_y - old_y; new_y = old_x +
     * center_y - centre_x. The method controls also the coordinates (x and y)
     * doesn't became negative, major than the matrix size (in that case they are
     * fixed) and it also cancels the empty columns. If the piece is straight on the
     * left or right wall, on the bottom or straight to dead pieces fixes are
     * applied to allow the rotation. The method sets the new coordinates only if
     * they are legal. I use a boolean, isClockwise, to choose witch kind of
     * rotation is required.
     */
    private void rotate(final boolean isClockwise) {
        // in case movement is not legal I save all the field of the piece before
        // rotate it
        final int oldLeft = this.game.getCurrent().getLeft();
        final int oldTop = this.game.getCurrent().getTop();
        final Pair<Integer, Integer> oldCenter = this.game.getCurrent().getCenter();
        final Set<Pair<Integer, Integer>> oldCoordinates = this.game.getCurrent().getCoordinates();
        // set for new coordinates
        final Set<Pair<Integer, Integer>> newCoordinates = new HashSet<>();
        // rotate
        if (isClockwise) {
            this.game.getCurrent().getCoordinates()
                    .forEach(coord -> newCoordinates.add(new Pair<>(
                            coord.getY() + this.game.getCurrent().getCenter().getX()
                            - this.game.getCurrent().getCenter().getY(),
                            this.game.getCurrent().getCenter().getX() + this.game.getCurrent().getCenter().getY()
                            - coord.getX())));
        } else {
            this.game.getCurrent().getCoordinates()
                    .forEach(coord -> newCoordinates.add(new Pair<>(
                            this.game.getCurrent().getCenter().getX() + this.game.getCurrent().getCenter().getY()
                            - coord.getY(),
                            coord.getX() + this.game.getCurrent().getCenter().getY()
                            - this.game.getCurrent().getCenter().getX())));
        }
        // set new coordinates
        this.game.getCurrent().setCoordinates(newCoordinates);
        // fix coordinates (restore fix for empty column, fix negative coords, empty cols
        // and over size cols)
        this.fixCoordinatesIfNecessary();
        // check the new position is legal
        if (!this.game.isCurrentLegal()) {
            // fix coordinates (rotation by the bottom or by the left or right
            // wall or dead piece)
            this.fixIfCurrentIsNotLegal(oldLeft);
        }
        // check the new position is legal
        if (!this.game.isCurrentLegal()) {
            // restore the piece as before the rotation
            this.game.getCurrent().setLeft(oldLeft);
            this.game.getCurrent().setTop(oldTop);
            this.game.getCurrent().setCenter(oldCenter);
            this.game.getCurrent().setCoordinates(oldCoordinates);
        }
    }

    /*
     * Calculates the max of X
     */
    private int maxX() {
        return this.game.getCurrent().getCoordinates()
                .stream()
                .map(x -> x.getX())
                .max(Comparator.naturalOrder())
                .get();
    }

    /*
     * Calculates the max of Y
     */
    private int maxY() {
        return this.game.getCurrent().getCoordinates()
                .stream()
                .map(x -> x.getY())
                .max(Comparator.naturalOrder())
                .get();
    }

    /*
     * Calculates the min of X
     */
    private int minX() {
        return this.game.getCurrent().getCoordinates()
                .stream()
                .map(x -> x.getX())
                .min(Comparator.naturalOrder())
                .get();
    }

    /*
     * Calculates the min of Y
     */
    private int minY() {
        return this.game.getCurrent().getCoordinates()
                .stream()
                .map(x -> x.getY())
                .min(Comparator.naturalOrder())
                .get();
    }

    /*
     * Calculates the Y size of the piece
     */
    private int yPieceSize() {
        return this.maxY() - this.minY() + 1;
    }

    /*
     * Calculates the X size of the piece
     */
    private int xPieceSize() {
        return this.maxX() - this.minX() + 1;
    }

    /*
     * Fix coordinates under zero. It also fixes the left and the center of the
     * piece if it is necessary.
     */
    private void fixUnderZero() {
        // set for new coordinates
        final Set<Pair<Integer, Integer>> newCoordinates = new HashSet<>();
        // calculate the min of x and y to check if they are negative
        final int minX = this.minX();
        final int minY = this.minY();

        final int fixX;
        final int fixY;
        // check the negative x
        if (minX < 0) {
            if (this.game.getCurrent().getTop() < 0) {
                this.game.getCurrent().setTop(0);
            }
            fixX = Math.abs(minX);
        } else {
            fixX = 0;
        }
        // check the negative y
        if (minY < 0) {
            if (this.game.getCurrent().getLeft() < 0) {
                this.game.getCurrent().setLeft(0);
            }
            fixY = Math.abs(minY);
        } else {
            fixY = 0;
        }
        // at least one of the two must be different from 0
        if (fixX != 0 || fixY != 0) {
            // all the coordinates, even the center, are translated to change all the
            // negative coordinates in positive
            // fix center
            this.game.getCurrent().setCenter(new Pair<>(this.game.getCurrent().getCenter().getX() + fixX,
                    this.game.getCurrent().getCenter().getY() + fixY));
            // fix coordinates
            this.game.getCurrent().getCoordinates()
                    .forEach(coord -> newCoordinates.add(new Pair<>(coord.getX() + fixX, coord.getY() + Math.abs(fixY))));
            // set new coordinates
            this.game.getCurrent().setCoordinates(newCoordinates);
        }
    }

    /*
     * Translates to left the piece in its "matrix" if there are some empty columns
     * on the left.
     */
    private void fixEmptyColumns() {
        // set for new coordinates
        final Set<Pair<Integer, Integer>> newCoordinates = new HashSet<>();
        // calculate the min of y to check the empty columns
        final int minY = this.minY();
        // in this case there are some empty column I fix coordinates and center to
        // eliminate them, and fix the left
        // increasing it by the quantity of empty rows to not "see" the fix
        if (minY > 0) {
            // this function save all the useful field to roll back the fix applied on the
            // left
            this.usedFixEmptyCol(minY);
            // fix center
            this.game.getCurrent().setCenter(new Pair<>(this.game.getCurrent().getCenter().getX(),
                    this.game.getCurrent().getCenter().getY() - minY));
            // fix left
            this.game.getCurrent().setLeft(this.game.getCurrent().getLeft() + minY);
            // fix coordinates
            this.game.getCurrent().getCoordinates()
                    .forEach(coord -> newCoordinates.add(new Pair<>(coord.getX(), coord.getY() - minY)));
            // set new coordinates
            this.game.getCurrent().setCoordinates(newCoordinates);
        } else {
            this.isFixed = false;
        }
    }

    /*
     * Saves all the parameters useful to revert the fix. 
     * The parameter used is minY
     * (the corrective factor used in the fix).
     */
    private void usedFixEmptyCol(final int minY) {
        this.isFixed = true;
        this.leftBeforeFix = this.game.getCurrent().getLeft();
        this.fixEmptyCol = minY;
        this.pieceFixed = this.game.getCurrent();
    }

    /*
     * Reverts the fix for the empty columns so the piece doesn't translate when it
     * rotates. This fix is applied just in case the piece is the same.
     */
    private void revertFixEmptyCol() {
        // if fix was applied on the piece itself
        if (this.isFixed && this.pieceFixed.equals(this.game.getCurrent())) {
            final int fix;
            // in case the left changed
            if (this.leftBeforeFix - this.game.getCurrent().getLeft() > 0) {
                fix = this.fixEmptyCol - (this.leftBeforeFix - this.game.getCurrent().getLeft());
            } else {
                fix = this.fixEmptyCol;
            }
            this.game.getCurrent().setLeft(this.game.getCurrent().getLeft() - Math.abs(fix));
        }
        this.isFixed = false;
    }

    /**
     * Fixes coordinates out of the "matrix" of the piece.
     */
    private void fixCoordinatesOverSize() {
        // set for new coordinates
        final Set<Pair<Integer, Integer>> newCoordinates = new HashSet<>();
        // to check the over size coordinates I calculate the max of x and y
        final int maxX = this.maxX();
        final int maxY = this.maxY();

        final int fixX;
        final int fixY;
        // in this case X are over size
        if (maxX > (Piece.MATRIXDIMENSION - 1)) {
            fixX = maxX - (Piece.MATRIXDIMENSION - 1);
        } else {
            fixX = 0;
        }
        // in this case Y are over size
        if (maxY > (Piece.MATRIXDIMENSION - 1)) {
            fixY = maxY - (Piece.MATRIXDIMENSION - 1);
        } else {
            fixY = 0;
        }
        // at least one of the two must be different from 0
        if (fixX != 0 || fixY != 0) {
            // in case they are over size I reduce the coordinates and the center by the
            // difference between the max and the matrix dimension
            // fix center
            this.game.getCurrent().setCenter(new Pair<>(this.game.getCurrent().getCenter().getX() - fixX,
                    this.game.getCurrent().getCenter().getY() - fixY));
            // fix coordinates
            this.game.getCurrent().getCoordinates()
                    .forEach(coord -> newCoordinates.add(new Pair<>(coord.getX() - fixX, coord.getY() - fixY)));
            // set new coordinates
            this.game.getCurrent().setCoordinates(newCoordinates);
        }
    }

    /*
     * Fixes all problems the piece could have rotating, such as coordinates become
     * negatives, the piece has empty columns on the left or the piece come out from
     * its own "matrix".
     */
    private void fixCoordinatesIfNecessary() {
        // revert the fix for the empty column possibly applied on the previous rotation
        this.revertFixEmptyCol();
        // fix negative coordinates
        this.fixUnderZero();
        // fix over size coordinates
        this.fixCoordinatesOverSize();
        // fix empty columns
        this.fixEmptyColumns();
    }

    /*
     * Tries every fix methods necessary if the final coordinates are not legal.
     */
    private void fixIfCurrentIsNotLegal(final int oldLeft) {
        final int replaceTop = this.game.getCurrent().getTop();
        // management of horizontal case
        this.fixRotationHorizontalCase();
        // check the new position is legal
        if (!this.game.isCurrentLegal()) {
            // restore the old top
            this.game.getCurrent().setTop(replaceTop);
            // management of vertical case
            this.fixRotationVerticalCase(oldLeft);
        }
    }

    /*
     * Fix the rotation if the isLegal method says the piece can't move, but he actually
     * can. In fact, in particular case, the piece has just not enough space to
     * rotate on the bottom. So this method changes the top of the piece so it can
     * move.
     */
    private void fixRotationHorizontalCase() {
        // if there are some dead piece on the bottom I calculate their length
        final Optional<Integer> length = this.game.getBoard().getBoard().keySet()
                .stream()
                .filter(x -> x.getY().equals(this.game.getCurrent().getLeft()))
                .map(x -> x.getX())
                .min(Comparator.naturalOrder());
        if (!length.isEmpty()) {
            // the new top is the length of the dead piece - the piece size
            this.game.getCurrent().setTop(length.get() - this.xPieceSize());
        } else {
            // if there aren't dead piece the new top is the board length - the piece size
            this.game.getCurrent().setTop(Board.COLLENGTH - this.xPieceSize());
        }

    }

    /*
     * Fix the rotation if the isLegal method says it can't move, but he actually
     * can. In fact, in particular case, the piece has just not enough space to
     * rotate on the right or on the left. So this method translate the left of the
     * piece so it can move. This method test two different case (to the left and to
     * the right).
     */
    private void fixRotationVerticalCase(final int oldLeft) {
        final int left = this.game.getCurrent().getLeft();
        // LEFT CASE
        // this is the case when the piece is not adjacent to the left but it can't
        // rotate
        // the first case is solved increasing by one the left
        this.game.getCurrent().setLeft(left + 1);
        // check the new position is legal
        if (!this.game.isCurrentLegal()) {
            // this is the case when the piece is adjacent on the left
            // I set the left equals to the old one
            this.game.getCurrent().setLeft(oldLeft);
        }
        if (!this.game.isCurrentLegal()) {
            // RIGHT CASE
            // this is the case when the piece is not adjacent to the right but it can't
            // rotate
            // the first case is solved reducing by one the left
            this.game.getCurrent().setLeft(left - 1);
            // check the new position is legal
            if (!this.game.isCurrentLegal()) {
                // this is the case when the piece is adjacent on the right
                // difference between the old and the new pos translated
                this.game.getCurrent().setLeft(left - (left + this.yPieceSize() - oldLeft - this.xPieceSize()));
            }
        }
    }

    /*
     * Shifts the piece of a factor "direction". 
     * The parameter used is direction:
     * the factor of translation.
     */
    private void move(final int direction) {
        // in case the movement is not legal I save the old left
        final int oldLeft = this.game.getCurrent().getLeft();
        // shift the coordinates
        this.game.getCurrent().setLeft(this.game.getCurrent().getLeft() + direction);
        // check if the new position is legal
        if (!this.game.isCurrentLegal()) {
            // in this case I restore the old left
            this.game.getCurrent().setLeft(oldLeft);
        }
    }
}
