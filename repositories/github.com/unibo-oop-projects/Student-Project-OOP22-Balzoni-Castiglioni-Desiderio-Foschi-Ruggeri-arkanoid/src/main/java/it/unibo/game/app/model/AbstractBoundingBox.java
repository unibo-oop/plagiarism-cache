package it.unibo.game.app.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.game.Pair;
import it.unibo.game.app.api.BoundingBox;
import it.unibo.game.app.api.Corner;
import it.unibo.game.app.api.Side;

/**
 * Class that builds a boundig box around a GameObject to control collisions.
 */
public class AbstractBoundingBox implements BoundingBox {

  private Map<Corner, Pair<Double, Double>> corners = new HashMap<>();
  private static final int CENTRE_RANGE = 10;

  /**
   * 
   * @param w   GameObject width
   * @param h   GameObject height
   * @param pos GameObject position
   */
  public AbstractBoundingBox(final Double w, final Double h,
      final Pair<Double, Double> pos) {
    this.corners.put(Corner.LEFT_DOWN,
        new Pair<Double, Double>(pos.getX(), pos.getY() + h));
    this.corners.put(Corner.LEFT_UP, pos);
    this.corners.put(Corner.RIGHT_DOWN,
        new Pair<Double, Double>(pos.getX() + w, pos.getY() + h));
    this.corners.put(Corner.RIGHT_UP,
        new Pair<Double, Double>(pos.getX() + w, pos.getY()));

  }

  /**
   * 
   * @param c1 corner of this boundingBox
   * @param c2 corner of boundingBox b
   * @param b  bounidngBox to check
   * @return true if the two corners collide
   */
  private boolean checkCorners(final Corner c1, final Corner c2, final BoundingBox b) {
    return this.range(this.corners.get(c1).getX(), b.getBox().get(c2).getX())
        && this.range(this.corners.get(c1).getY(), b.getBox().get(c2).getY());
  }

  /**
   * {@inheritDoc}
   */
  public Optional<Side> checkCentre(final BoundingBox b) {
    Double d1 = (this.corners.get(Corner.LEFT_DOWN).getX()
        + this.corners.get(Corner.RIGHT_DOWN).getX()) / 2;
    Double d2 = (b.getBox().get(Corner.LEFT_UP).getX()
        + b.getBox().get(Corner.RIGHT_UP).getX()) / 2;
    if (d1 <= d2 + CENTRE_RANGE && d1 >= d2 - CENTRE_RANGE) {
      return Optional.of(Side.PAD_CENTRE);
    } else {
      return Optional.empty();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Side> collideWith(final BoundingBox b) {
    if (this.checkCorners(Corner.LEFT_UP, Corner.RIGHT_DOWN, b)
        || this.checkCorners(Corner.LEFT_DOWN, Corner.RIGHT_UP, b)
        || this.checkCorners(Corner.RIGHT_UP, Corner.LEFT_DOWN, b)
        || this.checkCorners(Corner.RIGHT_DOWN, Corner.LEFT_UP, b)) {
      return Optional.of(Side.CORNER);
    } else if ((this.range(this.corners.get(Corner.LEFT_DOWN).getY(),
        b.getBox().get(Corner.LEFT_UP).getY())
        || this.range(this.corners.get(Corner.LEFT_UP).getY(),
            b.getBox().get(Corner.LEFT_DOWN).getY()))
        && (this.corners.get(Corner.LEFT_UP).getX() <= b.getBox().get(Corner.RIGHT_DOWN)
            .getX()
            && this.corners.get(Corner.RIGHT_UP).getX() >= b.getBox()
                .get(Corner.LEFT_DOWN).getX())) {
      return Optional.of(Side.UP_DOWN);

    } else if ((this.range(this.corners.get(Corner.RIGHT_DOWN).getX(),
        b.getBox().get(Corner.LEFT_DOWN).getX())
        || this.range(this.corners.get(Corner.LEFT_DOWN).getX(),
            b.getBox().get(Corner.RIGHT_DOWN).getX()))
        && (this.corners.get(Corner.RIGHT_DOWN).getY() >= b.getBox().get(Corner.LEFT_UP)
            .getY()
            && this.corners.get(Corner.RIGHT_UP).getY() <= b.getBox()
                .get(Corner.LEFT_DOWN).getY())) {
      return Optional.of(Side.LEFT_RIGHT);
    }

    return Optional.empty();
  }

  /**
   * 
   * @param d1
   * @param d2
   * @return Returns true if d1 is within the range.
   */
  private boolean range(final Double d1, final Double d2) {
    return (d1 >= d2 - 3 && d1 <= d2 + 3);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Corner, Pair<Double, Double>> getBox() {
    return Collections.unmodifiableMap(this.corners);
  }

}
