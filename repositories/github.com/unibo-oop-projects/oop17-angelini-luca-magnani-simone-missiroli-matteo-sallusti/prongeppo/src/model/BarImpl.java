package model;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import utility.Direction;
import utility.GameValues;
import view.PongElement;

/**
 * @author Paolo
 *
 */
public class BarImpl implements Bar {

    private int speed = 10;
    private final PongElement bar;

    /**
     * @param bar **the PongElement bind to this Bar**
     */
    public BarImpl(final PongElement bar) {
        this.bar = bar;
    }

    /**
     * @return **the actual speed of this bar**
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @return **the actual position (x, y) of this Bar**
     */
    @Override
    public Point getPosition() {
        return this.bar.getPosition();
    }

    /**
     * @param p **set the position (x, y) of this Bar**
     */
    @Override
    public void setPosition(final Point p) {
        this.bar.setPosition(p);
    }

    /**
     * @param speed **set the speed of this Bar**
     */
    @Override
    public void setSpeed(final int speed) {
       this.speed = speed;
    }

    /**
     * @param d **the direction where the Bar has to move**
     */
    @Override
    public void move(final Direction d) {
        if (d == Direction.UP) {
            if (this.getPosition().getY() > this.speed) {
                this.setPosition(moveY(this.getPosition().getY() - this.speed));
            } else if (this.getPosition().getY() > 0) {
                this.setPosition(moveY(0));
            }
        } else if (d == Direction.DOWN) {
            final int botBound = GameValues.WORLDHEIGHT - GameValues.BARY;
            if (this.getPosition().getY() < (botBound + this.speed)) {
                this.setPosition(moveY(this.getPosition().getY() + this.speed)); 
            } else if (this.getPosition().getY() < (botBound)) {
                this.setPosition(moveY(botBound));
            }
        }  else if (d != Direction.STOP) {
            throw new IllegalStateException();
        }
    }

    /**
     * 
     * @param y **the position in the Y_AXIS where the bar has to move**
     * @return **the new position of the Bar**
     */
    private Point moveY(final double y) {
        return new Point((int) this.getPosition().getX(), (int) y);
    }

    /**
     * @return **the rectangle shape of the actual position of this Bar**
     */
    @Override
    public Rectangle2D getHitbox() {
        return new Rectangle2D.Double(this.getPosition().getX(), this.getPosition().getY(), GameValues.BARX, GameValues.BARY);
    }
}
