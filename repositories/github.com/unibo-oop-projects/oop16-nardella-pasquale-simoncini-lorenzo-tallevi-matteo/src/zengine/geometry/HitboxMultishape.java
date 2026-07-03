package zengine.geometry;

import java.util.ArrayList;
import java.util.List;

import zengine.constants.ZengineColor;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class represents a shape composed by multiple circles, rectangles and
 * points. The coordinates of its components are relative to the center of the
 * multishape.
 */
public class HitboxMultishape implements Hitbox {

    private final GameEngine ze = Zengine.getEngine();

    private double angle; // = 0;
    private double x; // = 0;
    private double y; // = 0;

    // private final List<HitboxInfo<Hitbox>> hitboxList = new ArrayList<>();
    private final List<HitboxInfo<HitboxPoint>> pointList = new ArrayList<>();
    private final List<HitboxInfo<HitboxCircle>> circleList = new ArrayList<>();
    private final List<HitboxInfo<HitboxRectangle>> rectangleList = new ArrayList<>();

    /**
     * builds a new empty multishape.
     * 
     * @param x
     *            x coordinate of the center of the multishape
     * @param y
     *            y coordinate of the center of the multishape
     */
    public HitboxMultishape(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    private double getAngle() {
        return angle;
    }

    private void setAngle(final double angle) {
        this.angle = angle;
        for (final HitboxInfo<HitboxPoint> i : pointList) {
            final HitboxPoint p = i.getHitbox();
            p.setX(ze.pointXrotate(i.getStartX(), i.getStartY(), 0, 0, angle) + getX());
            p.setY(ze.pointYrotate(i.getStartX(), i.getStartY(), 0, 0, angle) + getY());
        }
        for (final HitboxInfo<HitboxCircle> i : circleList) {
            final HitboxCircle p = i.getHitbox();
            p.setX(ze.pointXrotate(i.getStartX(), i.getStartY(), 0, 0, angle) + getX());
            p.setY(ze.pointYrotate(i.getStartX(), i.getStartY(), 0, 0, angle) + getY());
        }
        for (final HitboxInfo<HitboxRectangle> i : rectangleList) {
            final HitboxRectangle p = i.getHitbox();
            p.setX(ze.pointXrotate(i.getStartX(), i.getStartY(), 0, 0, angle) + getX());
            p.setY(ze.pointYrotate(i.getStartX(), i.getStartY(), 0, 0, angle) + getY());
        }
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public void setX(final double x) {
        this.x = x;
        for (final HitboxInfo<HitboxPoint> i : pointList) {
            final HitboxPoint p = i.getHitbox();
            p.setX(i.getStartX() + getX());
        }
        for (final HitboxInfo<HitboxCircle> i : circleList) {
            final HitboxCircle p = i.getHitbox();
            p.setX(i.getStartX() + getX());
        }
        for (final HitboxInfo<HitboxRectangle> i : rectangleList) {
            final HitboxRectangle p = i.getHitbox();
            p.setX(i.getStartX() + getX());
        }
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(final double y) {
        this.y = y;
        for (final HitboxInfo<HitboxPoint> i : pointList) {
            final HitboxPoint p = i.getHitbox();
            p.setY(i.getStartY() + getY());
        }
        for (final HitboxInfo<HitboxCircle> i : circleList) {
            final HitboxCircle p = i.getHitbox();
            p.setY(i.getStartY() + getY());
        }
        for (final HitboxInfo<HitboxRectangle> i : rectangleList) {
            final HitboxRectangle p = i.getHitbox();
            p.setY(i.getStartY() + getY());
        }
    }

    @Override
    public boolean isTouching(final HitboxPoint other) {
        boolean ret = false;
        ret = compareWith(other, circleList);
        if (!ret) {
            ret = compareWith(other, rectangleList);
        }
        return ret;
    }

    @Override
    public boolean isTouching(final HitboxCircle other) {
        boolean ret = false;
        ret = compareWith(other, pointList);
        if (!ret) {
            ret = compareWith(other, circleList);
        }
        if (!ret) {
            ret = compareWith(other, rectangleList);
        }
        return ret;
    }

    @Override
    public boolean isTouching(final HitboxRectangle other) {
        boolean ret = false;
        ret = compareWith(other, pointList);
        if (!ret) {
            ret = compareWith(other, circleList);
        }
        if (!ret) {
            ret = compareWith(other, rectangleList);
        }
        return ret;
    }

    @Override
    public boolean isTouching(final HitboxMultishape other) {
        boolean ret = false;

        final List<HitboxInfo<HitboxPoint>> pl = other.getPoints();
        for (final HitboxInfo<HitboxPoint> oth : pl) {
            ret = this.isTouching(oth.getHitbox());
            if (ret) {
                return true;
            }
        }

        // declaring a variable here is bad, but pmd complains
        final List<HitboxInfo<HitboxCircle>> cl = other.getCircles();
        for (final HitboxInfo<HitboxCircle> oth : cl) {
            ret = this.isTouching(oth.getHitbox());
            if (ret) {
                return true;
            }
        }

        // declaring a variable here is bad, but pmd complains
        final List<HitboxInfo<HitboxRectangle>> rl = other.getRectangles();
        for (final HitboxInfo<HitboxRectangle> oth : rl) {
            ret = this.isTouching(oth.getHitbox());
            if (ret) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a point to the list of points this multishape is composed of.
     * 
     * @param point
     *            point to be added
     */
    public void add(final HitboxPoint point) {
        pointList.add(new HitboxInfo<HitboxPoint>(point.getX(), point.getY(), point));
    }

    /**
     * Adds a circle to the list of circles this multishape is composed of.
     * 
     * @param circle
     *            circle to be added
     */
    public void add(final HitboxCircle circle) {
        circleList.add(new HitboxInfo<HitboxCircle>(circle.getX(), circle.getY(), circle));
    }

    /**
     * Adds a rectangle to the list of rectangles this multishape is composed
     * of.
     * 
     * @param rectangle
     *            rectangle to add
     */
    public void add(final HitboxRectangle rectangle) {
        rectangleList.add(new HitboxInfo<HitboxRectangle>(rectangle.getX(), rectangle.getY(), rectangle));
    }

    /**
     * clears this multishape of all its shape components.
     */
    public void clearAll() {
        clearPoints();
        clearCircles();
        clearRectangles();
    }

    /**
     * removes all point components from this multishape.
     */
    public void clearPoints() {
        pointList.clear();
    }

    /**
     * removes all circle components from this multishape.
     */
    public void clearCircles() {
        circleList.clear();
    }

    /**
     * removes all rectangle components from this multishape.
     */
    public void clearRectangles() {
        rectangleList.clear();
    }

    private class HitboxInfo<T extends Hitbox> {

        private final double startX;
        private final double startY;
        private final T hitbox;

        HitboxInfo(final double x, final double y, final T hitbox) {
            startX = x;
            startY = y;
            this.hitbox = hitbox;
        }

        public double getStartX() {
            return startX;
        }

        public double getStartY() {
            return startY;
        }

        public T getHitbox() {
            return hitbox;
        }
    }

    // private List<HitboxInfo<Hitbox>> getHitboxes() {
    // return hitboxList;
    // }

    private List<HitboxInfo<HitboxPoint>> getPoints() {
        return pointList;
    }

    private List<HitboxInfo<HitboxCircle>> getCircles() {
        return circleList;
    }

    private List<HitboxInfo<HitboxRectangle>> getRectangles() {
        return rectangleList;
    }

    private <O extends Hitbox, M extends Hitbox> boolean compareWith(final O hitbox, final List<HitboxInfo<M>> list) {
        // compares an hitbox with all the given hitboxinfos, returns true if at
        // least
        // one collision is found
        for (final HitboxInfo<M> i : list) {
            final M myp = i.getHitbox();
            if (myp.isTouching(hitbox)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setRotation(final double angle) {
        setAngle(angle);
    }

    @Override
    public double getRotation() {
        return getAngle();
    }

    @Override
    public void drawSelf(final ZengineColor color, final boolean filled) {
        for (final HitboxInfo<HitboxPoint> i : pointList) {
            final HitboxPoint p = i.getHitbox();
            p.drawSelf(color, filled);
        }
        for (final HitboxInfo<HitboxCircle> i : circleList) {
            final HitboxCircle p = i.getHitbox();
            p.drawSelf(color, filled);
        }
        for (final HitboxInfo<HitboxRectangle> i : rectangleList) {
            final HitboxRectangle p = i.getHitbox();
            p.drawSelf(color, filled);
        }
    }

}
