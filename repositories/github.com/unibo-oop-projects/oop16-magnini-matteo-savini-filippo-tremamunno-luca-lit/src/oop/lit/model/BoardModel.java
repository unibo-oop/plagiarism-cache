package oop.lit.model;

import java.util.ArrayList;
import java.util.List;

import oop.lit.model.elements.BoardElement;
import oop.lit.model.groups.Board;
import oop.lit.util.Vector2D;
/**
 * A wrapper class for a board, hiding unneccessary methods to the controller and view.
 * Notifies observers when contained or selected elements change.
 */
public class BoardModel extends SelectableElementGroupModel {
    private final Board<? extends BoardElement> board;
    /**
     * @param board
     *      the board to be wrapped.
     */
    public BoardModel(final Board<? extends BoardElement> board) {
        super(board);
        this.board = board;
    }

    /**
     * @param playingPlayer
     *      the player asking to move selected elements
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can move selected elements
     * @see oop.lit.model.groups.Board#canMoveSelected(oop.lit.model.game.Player, java.util.List)
     */
    public boolean canMoveSelected(final PlayerModel playingPlayer, final List<PlayerModel> turnPlayers) {
        return this.board.canMoveSelected(playingPlayer, turnPlayers);
    }
    /**
     * Moves selected elements.
     *
     * @param delta
     *      how much the elements will be moved
     *
     * @throws IllegalStateException
     *      if no elements are selected
     * @see oop.lit.model.groups.Board#moveSelected(oop.lit.util.Vector2D)
     */
    public void moveSelected(final Vector2D delta) {
        this.board.moveSelected(delta);
    }

    /**
     * @param playingPlayer
     *      the player asking to scale selected elements
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can scale selected elements
     * @see oop.lit.model.groups.Board#canScaleSelected(oop.lit.model.game.Player, java.util.List)
     */
    public boolean canScaleSelected(final PlayerModel playingPlayer, final List<PlayerModel> turnPlayers) {
        return this.board.canScaleSelected(playingPlayer, turnPlayers);
    }
    /**
     * Scales selected elements.
     *
     * @param scalar
     *      must be greater than 0; a value representing how the elements size will change.
     *
     * @throws IllegalArgumentException
     *      if the scalar isn't greater than 0.
     * @throws IllegalStateException
     *      if no elements are selected
     * @see oop.lit.model.groups.Board#scaleSelected(double)
     */
    public void scaleSelected(final double scalar) {
        this.board.scaleSelected(scalar);
    }
    /**
     * Scales selected elements.
     * If an element, as a result of the scale operation, gets a scale outside of the provided range, its scale will be clamped to fit in the provided range.
     *
     * @param scalar
     *      must be greater than 0; a value representing how the elements size will change.
     * @param minScale
     *      the minimum scale a selected element can have.
     * @param maxScale
     *      the maximum scale a selected element can have.
     *
     * @throws IllegalArgumentException
     *      if the scalar isn't greater than 0.
     * @throws IllegalStateException
     *      if no elements are selected
     * @see oop.lit.model.groups.Board#scaleSelected(double, double, double)
     */
    public void scaleSelected(final double scalar, final double minScale, final double maxScale) {
        this.board.scaleSelected(scalar, minScale, maxScale);
    }

    /**
     * @param playingPlayer
     *      the player asking to move selected elements
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can move selected elements
     * @see oop.lit.model.groups.Board#canRotateSelected(oop.lit.model.game.Player, java.util.List)
     */
    public boolean canRotateSelected(final PlayerModel playingPlayer, final List<PlayerModel> turnPlayers) {
        return this.board.canRotateSelected(playingPlayer, turnPlayers);
    }
    /**
     * Rotates selected elements around their center.
     *
     * @param angle
     *      the angle of rotation in degrees. Positive value for clockwise rotation.
     *
     * @throws IllegalStateException
     *      if no elements are selected
     * @see oop.lit.model.groups.Board#rotateSelected(double)
     */
    public void rotateSelected(final double angle) {
        this.board.rotateSelected(angle);
    }

    /**
     * @return a list containing all selected elements as board elements.
     * @see oop.lit.model.groups.SelectableElementGroup#getSelected()
     */
    public List<BoardElementModel> getSelectedBoardElements() {
        return new ArrayList<>(board.getSelected());
    }
    /**
     * @return a list containing all elements as board elements.
     * @see oop.lit.model.groups.ElementGroup#getElements()
     */
    public List<BoardElementModel> getBoardElements() {
        return new ArrayList<>(board.getElements());
    }
}
