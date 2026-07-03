package model;

import java.awt.Point;
import java.awt.geom.Ellipse2D;

import utility.GameValues;
import view.PongElement;

/**
 * @author Missi
 *
 */
public class BallImpl implements Ball {
    private final PongElement ball;
    private Point speed = new Point(0, 0);
    private Combo combo = Combo.NULL;

    /**
     * @param b **the graphic element associated to this element** 
     */
    public BallImpl(final PongElement b) {
        this.ball = b;
    }

    /**
     * @see model.Ball#getSpeed()
     */
    @Override
    public Point getSpeed() {
        return this.speed;
    }

    /**
     * @see model.Ball#setSpeed(java.awt.Point)
     */
    @Override
    public void setSpeed(final Point i) {
        if (Math.abs(i.x) < GameValues.BARX) {
            this.speed = i;
        } else {
            this.speed = i.x > 0 ? new Point(GameValues.BARX - 1, i.y) : new Point(-(GameValues.BARX - 1), i.y);
        }
    }

    /**
     * @see model.Element#getPosition()
     */
    @Override
    public Point getPosition() {
        return this.ball.getPosition();
    }

    /**
     * @see model.Ball#setPosition(java.awt.Point)
     */
    @Override
    public void setPosition(final Point p) {
        this.ball.setPosition(p);
    }
    /**
     * @see model.Ball#setCombo(model.Ball.Combo)
     */
    @Override
    public void setCombo(final Combo combo) {
        this.removeCombo();
        this.combo = combo;
        this.letsCombo();
    }
    private void removeCombo() {
        switch (this.combo) {
        case FAST :
            this.setSpeed(new Point(this.getSpeed().x / GameValues.FAST_SPEED, this.getSpeed().y));
            break;
        case INCREMENTAL :
            this.setSpeed(new Point(this.getSpeed().x / 2, this.getSpeed().y / 2));
            break;
        default:
        }
    }

    private void letsCombo() {
        switch (this.combo) {
        case FAST :
            this.setSpeed(new Point(this.getSpeed().x * GameValues.FAST_SPEED, 0));
            break;
        case ZIGZAG :
            this.setSpeed(new Point(this.getSpeed().x, GameValues.ZIGZAG_SPEED));
            break;
        case INCREMENTAL :
            this.setSpeed(new Point(this.getSpeed().x * 2, this.getSpeed().y * 2));
            break;
        default:
        }
    }
    /**
     * @see model.Ball#getCombo()
     */
    @Override
    public Combo getCombo() {
        return this.combo;
    }

    /**
     * @see model.Ball#move()
     */
    @Override
    public void move() {
        if (this.speed.y <= 0) {
            if (this.getPosition().getY() > Math.abs(this.speed.y)) {
                this.setPosition(new Point((int) (this.getPosition().getX() + this.getSpeed().getX()),
                                           (int) (this.getPosition().getY() + this.getSpeed().getY())));
            } else if (this.getPosition().getY() > 0) {
                this.setPosition(new Point((int) (this.getPosition().getX() + this.getSpeed().getX()),
                                           (int) Math.abs(this.getPosition().getY() + this.getSpeed().getY())));
                this.setSpeed(new Point(this.getSpeed().x, this.getSpeed().y * -1));
            }
        } else if (this.speed.y > 0) {
        final int botBound = GameValues.WORLDHEIGHT - GameValues.BALL_DIMENSION;
        if (this.getPosition().getY() < (botBound - this.speed.y)) {
            this.setPosition(new Point((int) (this.getPosition().getX() + this.getSpeed().getX()),
                                       (int) (this.getPosition().getY() + this.getSpeed().getY())));
            } else if (this.getPosition().getY() < (botBound)) {
                this.setPosition(new Point((int) (this.getPosition().getX() + this.getSpeed().getX()),
                                           (int) (this.getPosition().getY() + (this.getSpeed().getY() - (this.getPosition().getY() - botBound)))));
                this.setSpeed(new Point(this.getSpeed().x, this.getSpeed().y * -1));
            }
        }
    }

    /**
     * @param b **set the visibility of this ball**
     */
    @Override
    public void setVisible(final boolean b) {
        ball.setVisible(b);
    }

    /**
     * @see model.Ball#getHitbox()
     */
    @Override
    public Ellipse2D getHitbox() {
        return new Ellipse2D.Double(this.getPosition().getX(), this.getPosition().getY(), GameValues.BALL_DIMENSION, GameValues.BALL_DIMENSION);
    }

    /**
     * @see model.Ball#isVisible()
     */
    @Override
    public boolean isVisible() {
        return this.ball.isVisible();
    }

}
