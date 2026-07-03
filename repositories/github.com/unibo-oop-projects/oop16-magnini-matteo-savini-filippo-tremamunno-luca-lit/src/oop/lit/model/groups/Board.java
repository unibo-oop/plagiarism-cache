package oop.lit.model.groups;

import java.util.List;

import oop.lit.model.PlayerModel;
import oop.lit.model.elements.BoardElement;
import oop.lit.util.Vector2D;

/**
 * The container of all board elements.
 * 
 * @param <H> the type of BoardElements held.
 */
public interface Board<H extends BoardElement> extends SelectableElementGroup<H> {
    /**
     * @param playingPlayer
     *      the player asking to move selected elements
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can move selected elements
     */
    boolean canMoveSelected(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);
    /**
     * Moves selected elements.
     *
     * @param delta
     *      how much the elements will be moved
     *
     * @throws IllegalStateException
     *      if no elements are selected
     */
    void moveSelected(Vector2D delta);

    /**
     * @param playingPlayer
     *      the player asking to scale selected elements
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can scale selected elements
     */
    boolean canScaleSelected(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);
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
     */
    void scaleSelected(double scalar);
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
     */
    void scaleSelected(double scalar, double minScale, double maxScale);

    /**
     * @param playingPlayer
     *      the player asking to move selected elements
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can move selected elements
     */
    boolean canRotateSelected(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);
    /**
     * Rotates selected elements around their center.
     *
     * @param angle
     *      the angle of rotation in degrees. Positive value for clockwise rotation.
     *
     * @throws IllegalStateException
     *      if no elements are selected
     */
    void rotateSelected(double angle);
}
