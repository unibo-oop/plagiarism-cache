package it.dpg.minigames.ballgame.model;

import java.awt.geom.Point2D;

public class HorizontalBoundary extends AbstractBoundary {

    public HorizontalBoundary(final double startX, final double endX, final double yPos, final CollisionType type) {
        super(startX, yPos, endX, yPos, type);
    }

    @Override
    public boolean isVertical() {
        return false;
    }

    @Override
    public boolean isHorizontal() {
        return true;
    }

    @Override
    public boolean isBallColliding(double ballCenterX, double ballCenterY, double ballRadius) {
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double yPos = start.getY();
        if (ballCenterX > minX && ballCenterX < maxX) { // the ball is above or below
            if (ballCenterY > yPos && (ballCenterY - ballRadius) < yPos) {//ball is above
                return true;
            }
            return ballCenterY < yPos && (ballCenterY + ballRadius) > yPos;//ball is below
        } else if (ballCenterX > (minX - ballRadius) && ballCenterX < minX) { // the ball is slightly to the left
            return Point2D.distance(ballCenterX, ballCenterY, minX, yPos) < ballRadius;
        } else if (ballCenterX > maxX && ballCenterX < (maxX + ballRadius)) { // the ball is slightly to the right
            return Point2D.distance(ballCenterX, ballCenterY, maxX, yPos) < ballRadius;
        }
        return false;
    }
}
