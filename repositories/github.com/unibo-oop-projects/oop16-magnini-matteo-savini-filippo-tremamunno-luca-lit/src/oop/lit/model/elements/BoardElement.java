package oop.lit.model.elements;

import java.util.List;

import oop.lit.model.BoardElementModel;
import oop.lit.model.PlayerModel;
import oop.lit.util.Vector2D;

/**
 * A game element on a board.
 */
public interface BoardElement extends GameElement, BoardElementModel {
    /**
     * Moves the element.
     * Should notify Observers.
     *
     * @param delta
     *      how much the element will be moved
     */
    void move(Vector2D delta);
    /**
     * @param playingPlayer
     *      the player asking to move this element
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can move this element now.
     */
    boolean canMove(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);

    /**
     * Scales the element.
     * Should notify Observers.
     *
     * @param scalar
     *      must be greater than 0; a value representing how the element size will change. For example if it is 2 the element size will double, if it is 0.5 it will halves.
     * @throws IllegalArgumentException
     *      if the scalar isn't greater than 0.
     */
    void scale(double scalar);

    /**
     * If the element scale is not in the provided range, sets it to the closest value in the range.
     * @param minScale
     *      the minimum possible value for the scale.
     * @param maxScale
     *      the maximum possible value for the scale.
     * @throws IllegalArgumentException
     *      if minScale isn't more than 0, or maxScale is less than minScale 
     */
    void clampScale(double minScale, double maxScale);

    /**
     * @param playingPlayer
     *      the player asking to scale this element
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can scale this element
     */
    boolean canScale(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);

    /**
     * Rotates the element around its center.
     * Should notify Observers.
     *
     * @param angle
     *      the angle of rotation in degrees. Positive value for clockwise rotation.
     */
    void rotate(double angle);
    /**
     * @param playingPlayer
     *      the player asking to rotate this element
     * @param turnPlayers
     *      the players playing the current turn
     * @return
     *      if the player can rotate this element
     */
    boolean canRotate(PlayerModel playingPlayer, List<? extends PlayerModel> turnPlayers);
}
