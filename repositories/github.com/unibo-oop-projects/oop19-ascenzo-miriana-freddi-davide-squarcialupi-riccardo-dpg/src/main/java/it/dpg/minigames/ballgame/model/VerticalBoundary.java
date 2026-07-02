package it.dpg.minigames.ballgame.model;

import java.awt.geom.Point2D;

public class VerticalBoundary extends AbstractBoundary {
    public VerticalBoundary(double xPos, double startY, double endY, CollisionType type) {
        super(xPos, startY, xPos, endY, type);
    }

    @Override
    public boolean isVertical() {
        return true;
    }

    @Override
    public boolean isHorizontal() {
        return false;
    }

    @Override
    public boolean isBallColliding(double ballCenterX, double ballCenterY, double ballRadius) {
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());
        double xPos = start.getX();
        if (ballCenterY > minY && ballCenterY < maxY) { // the ball is left or right
            if (ballCenterX > xPos && (ballCenterX - ballRadius) < xPos) {//ball is right
                return true;
            }
            return ballCenterX < xPos && (ballCenterX + ballRadius) > xPos;//ball is left
        } else if (ballCenterY > (minY - ballRadius) && ballCenterY < minY) { // the ball is slightly higher
            return Point2D.distance(ballCenterX, ballCenterY, xPos, minY) < ballRadius;
        } else if (ballCenterY > maxY && ballCenterY < (maxY + ballRadius)) { // the ball is slightly lower
            return Point2D.distance(ballCenterX, ballCenterY, xPos, maxY) < ballRadius;
        }
        return false;
    }
}
