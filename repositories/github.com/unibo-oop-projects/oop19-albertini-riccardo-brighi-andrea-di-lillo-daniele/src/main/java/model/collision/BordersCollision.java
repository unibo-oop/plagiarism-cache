package model.collision;

import model.ball.Ball;
import element.Point2D;
import element.Point2DImpl;
import element.Vector2D;
import element.Vector2DImpl;
import java.util.Optional;

import static java.lang.Math.tan;

/**
 * class that helps me deal with edge collisions
 */
final class BordersCollision extends CollisionImpl{

    /**
     * Constructor of BorderCollision
     *
     * @param topLeft the point that represent top left angle
     * @param bottomRight the point that represent bottom right angle
     */
    protected BordersCollision(Point2DImpl topLeft, Point2DImpl bottomRight) {
        super(null, topLeft, bottomRight);
    }

    private Optional<CollisionDetectedImpl> collisionWithBottomBorder(final Point2D newPosition){
        return Optional.of(new CollisionDetectedImpl(newPosition));
    }

    /**
     * Method to return the collision
     *
     * @param ball current ball
     * @param relocatedPosition new position of ball (where was the collision)
     * @return the collision if is present or empty
     */
    protected Optional<CollisionDetectedImpl> collisionWithBorders(final Ball ball, final Point2D relocatedPosition){
        Vector2D newDirection = ball.getDirection();

        if(relocatedPosition.getY() - ball.getRadius() == bottom){
            return this.collisionWithBottomBorder(relocatedPosition);
        }

        if(relocatedPosition.getY() + ball.getRadius() == top){
            newDirection = new Vector2DImpl(newDirection.getXComponent(),
                    newDirection.getYComponent() * -1);
        }

        if(relocatedPosition.getX() - ball.getRadius() == left ||
                relocatedPosition.getX() + ball.getRadius() == right){
            newDirection = new Vector2DImpl(newDirection.getXComponent() * -1,
                    newDirection.getYComponent());
        }
        return Optional.of(new CollisionDetectedImpl(newDirection, relocatedPosition));
    }

    //calculate X axis and fix y
    private Point2D changeXAxis(final Ball ball, final double pos, final double newY){
        double newX = getCorrectPosition(pos ,ball.getPosition().getY() , ball.getRadius(), true,
                ball.getPosition().getX(), ball.getDirection());
        return new Point2DImpl(newX, newY);
    }

    //calculate y axis and fix x
    private Point2D changeYAxis(final Ball ball, final double pos, final double newX){
        double newY = getCorrectPosition(pos ,ball.getPosition().getX() , ball.getRadius(), false,
                ball.getPosition().getY(), ball.getDirection());
        return new Point2DImpl(newX, newY);
    }

    private double getCorrectPosition(final double from, final double to,
                                      final double radius, boolean xAxis,
                                      final double currentPosition,  final Vector2D currentDirection){
        double x = fixNanOrInfinityValue(getBadMovementWithoutAngle(from, to, radius, xAxis, currentDirection));
        //double x1 = getBadMovementWithAngle(from, to, radius, radiansAngle);
        return currentDirection.getYComponent() >= 0 ? currentPosition + x : currentPosition - x;
    }

    /**
     * calculate the distance with trigonometric, not good idea!!
     *
     * @deprecated use {@link #getDistancePlusRadius(double, double, double)} instead.
     */
    @Deprecated
    private double getBadMovementWithAngle(final double from, final double to, final double radius, final double radiansAngle){
        return getDistancePlusRadius(from, to, radius) * tan(Math.PI/2 - Math.abs(radiansAngle));
    }

    private double fixNanOrInfinityValue(final Double distance){
        if(distance.isNaN() || distance.isInfinite()){
            return 0;
        }
        return distance;
    }

    private double getBadMovementWithoutAngle(final double from, final double to, final double radius,
                                              final boolean x, final Vector2D direction){
        if(x){
            return getDistancePlusRadius(from, to, radius) * direction.getNormalizedVector().getYComponent() /
                    direction.getNormalizedVector().getXComponent();
        }
        else{
            return getDistancePlusRadius(from, to, radius) * direction.getNormalizedVector().getXComponent() /
                    direction.getNormalizedVector().getYComponent();
        }
    }

    private double getDistancePlusRadius(final double from, final double to, final double radius){
        return (Math.abs(from - to) - radius);
    }

    /**
     * Relocate the ball if is out of bounds
     *
     * @param ball current ball
     * @return the new position (where was the collision) or the his position
     */
    protected Point2D relocateBall(final Ball ball){
        Point2D pos = ball.getPosition();
        //bottom top
        if(checkBallOutYComponent(ball)){
            if(ball.getPosition().getY() - ball.getRadius() < bottom){
                pos = changeXAxis(ball, bottom, bottom + ball.getRadius());
            }
            else if(ball.getPosition().getY() + ball.getRadius() > top){
                pos = changeXAxis(ball, top, top - ball.getRadius());
            }
        } else if(checkBallOutXComponent(ball)){ //sx dx
            if(ball.getPosition().getX() - ball.getRadius() < left){
                pos = changeYAxis(ball, left, left + ball.getRadius());
            }
            else if (ball.getPosition().getX() + ball.getRadius() > right){
                pos = changeYAxis(ball, right, right - ball.getRadius());
            }
        }
        return pos;
    }
}
