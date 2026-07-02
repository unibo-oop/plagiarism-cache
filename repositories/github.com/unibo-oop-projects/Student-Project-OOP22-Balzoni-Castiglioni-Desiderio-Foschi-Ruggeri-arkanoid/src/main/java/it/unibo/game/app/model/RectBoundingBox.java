package it.unibo.game.app.model;

import it.unibo.game.app.api.GameObject;

/**
 * Class that creates boundingBox for rectangular objects.
 */
public class RectBoundingBox extends AbstractBoundingBox {

  /**
   * constructor of this class.
   * 
   * @param obj
   */
  public RectBoundingBox(final GameObject obj) {
    super(obj.getDimension().getWidth(), obj.getDimension().getHeight(), obj.getPos());
  }

}
