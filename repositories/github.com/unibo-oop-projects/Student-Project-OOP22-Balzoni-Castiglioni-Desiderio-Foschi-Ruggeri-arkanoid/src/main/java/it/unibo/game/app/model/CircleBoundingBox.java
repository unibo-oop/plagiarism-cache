package it.unibo.game.app.model;

import it.unibo.game.app.api.MovingObject;

/**
 * Class that creates boundingBox for circular objects.
 */
public class CircleBoundingBox extends AbstractBoundingBox {

  /**
   * constructor of this class.
   * 
   * @param obj
   */
  public CircleBoundingBox(final MovingObject obj) {
    super(obj.getDimension().getWidth(), obj.getDimension().getHeight(), obj.getPos());

  }

}
