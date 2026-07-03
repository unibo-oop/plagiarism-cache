package it.unibo.roguekong.model.game.impl;

import it.unibo.roguekong.model.game.Hitbox;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import javafx.geometry.Rectangle2D;

public class HitboxImpl implements Hitbox {
    Rectangle2D hitbox ;

    /**
     * void constructor with standard values
     */
    public HitboxImpl() { //void constructor
        setHitbox(new Rectangle2D(0,0,0,0));
    }

    /**
     * constructor with Position param
     * @param tl position of the object that implements a hitbox
     */
    public HitboxImpl(PositionImpl tl) { //base constructor
        setHitbox(new Rectangle2D(tl.getX(), tl.getY(), 32, 32));
    }

    /**
     * full parametric constructor
     * @param tl position of the object that implements a hitbox
     * @param width width of the object that implements a hitbox
     * @param height height of the object that implements a hitbox
     */
    public HitboxImpl(PositionImpl tl, double width, double height) { //constructor with width and height
        setHitbox(new Rectangle2D(tl.getX(), tl.getY(), width, height));
    }

    /**
     * check if the hitbox is moving without colliding with another Hitbox
     * @param x position in x
     * @param y position in y
     * @param hitbox the other hitbox to check
     */
    public void moveHitBox(double x, double y, HitboxImpl hitbox) {
        if(!isColliding(hitbox)) {
            moveHitBox(x, y);
        }
    }

    /**
     * check if the hitbox is within screen bounds
     * @param x position in x
     * @param y position in y
     */
    public void moveHitBox(double x, double y) {
        final double SCREEN_W = 960;
        final double SCREEN_H = 640;

        final Rectangle2D screen = new Rectangle2D(0, 0, SCREEN_W, SCREEN_H);

        Rectangle2D candidate = new Rectangle2D(x, y, getBounds().getWidth(), getBounds().getHeight());

        if (screen.contains(candidate)) {
            setHitbox(candidate);
        }
    }

    /**
     * return true when the other hitbox is touching this one
     * @param hb hitbox to check if it is colliding with
     * @return boolean value
     */
    public boolean isColliding(HitboxImpl hb) {
        return getBounds().intersects(hb.getBounds());
    }

    public Rectangle2D getBounds() {
        return hitbox;
    }

    /**
     * set the hitbox to an object that uses hitbox
     * @param rect Rectangle2D, class of javafx.geometry.Rectangle2D;
     */
    private void setHitbox(Rectangle2D rect) {
        this.hitbox = rect;
    }
}
