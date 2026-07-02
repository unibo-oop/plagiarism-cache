package it.unibo.game.app.model.pad;

import it.unibo.game.Pair;
import it.unibo.game.app.model.AbstractMovingObject;
import it.unibo.game.app.model.RectBoundingBox;
import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.dynamic.SpeedImpl;
import it.unibo.game.app.api.Dimension;

/*** Pad is a moving obj. */
public class Pad extends AbstractMovingObject {

  /**
   * set dimension of pad.
   * 
   * @param d
   */
  public Pad(final Dimension d) {
    super(new Pair<>(SizeCalculation.getWorldSize().getY() / 2 - d.getWidth() / 2,
        SizeCalculation.getWorldSize().getX() - 100), d);
    super.setBoundingBox(new RectBoundingBox(this));
    super.setSpeed(new SpeedImpl(10, 0));
  }

}
