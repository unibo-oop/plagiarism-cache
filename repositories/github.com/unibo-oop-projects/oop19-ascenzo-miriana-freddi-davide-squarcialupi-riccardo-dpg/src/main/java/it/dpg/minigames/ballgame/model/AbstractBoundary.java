package it.dpg.minigames.ballgame.model;

import java.awt.geom.Point2D;
import java.util.Objects;

public abstract class AbstractBoundary implements Boundary {
    protected final Point2D start;
    protected final Point2D end;
    private final CollisionType collisionType;

    AbstractBoundary(final double startX, final double startY, final double endX, final double endY, final CollisionType type) {
        start = new Point2D.Double(startX, startY);
        end = new Point2D.Double(endX, endY);
        this.collisionType = type;
    }

    @Override
    public double getStartX() {
        return this.start.getX();
    }

    @Override
    public double getStartY() {
        return this.start.getY();
    }

    @Override
    public double getEndX() {
        return this.end.getX();
    }

    @Override
    public double getEndY() {
        return this.end.getY();
    }

    @Override
    public CollisionType getCollisionType() {
        return this.collisionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBoundary)) return false;
        AbstractBoundary that = (AbstractBoundary) o;
        return start.equals(that.start) &&
                end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
