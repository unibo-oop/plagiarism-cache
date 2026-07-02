package it.unibo.game.app.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.game.Pair;

/**
 * interface for creating BoundiBox.
 */
public interface BoundingBox {

  /**
   * 
   * @return Map of Corners and their respective coordinates.
   */
  Map<Corner, Pair<Double, Double>> getBox();

  /**
   * 
   * @param b BoundinBox of the GameObject with which the collision could occur.
   * @return the side where the box collide or an empty optional if no collision.
   *         occurs
   */
  Optional<Side> collideWith(BoundingBox b);

  /**
   * 
   * @param b BoundinBox of the GameObject with which the collision could occur.
   * @return Side.PAD_CENTRE if the collision occurred in the centre of b.
   */
  Optional<Side> checkCentre(BoundingBox b);
}
