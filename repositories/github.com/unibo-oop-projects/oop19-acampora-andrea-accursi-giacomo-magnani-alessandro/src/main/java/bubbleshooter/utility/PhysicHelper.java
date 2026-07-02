package bubbleshooter.utility;

import bubbleshooter.model.bubble.Bubble;
import javafx.geometry.Point2D;

/**
 * Class used to implement physics methods.
 *
 */
 public final class PhysicHelper {

    private static final double MAXANGLE =  75.0;
    private static final double MINANGLE = -75.0;
    private static final double SENSIBILITY = 0.01;

    /** 
     * private constructor used to avoid initializations of this class.
     */
    private PhysicHelper() {
    }

    /**
     * 
     * @param inputPosition The position of the mouse.
     * @param bubblePosition the position of the {@link ShootingBubble}.
     * @return the direction to shoot the bubble.
     */
    public static Point2D calculateShootingDirection(final Point2D inputPosition, final Point2D bubblePosition) {
        final double angle = Math.atan2(inputPosition.getY() - bubblePosition.getY(), inputPosition.getX() - bubblePosition.getX());
        final double xVel = Math.cos(angle);
        final double yVel = Math.sin(angle);
        return new Point2D(xVel, yVel);
    }

    /**
     * Method to make the {@link Bubble} bounce.
     * @param shootingBubble The bubble to bounce.
     */
    public static void bounce(final Bubble shootingBubble) {
        shootingBubble.setDirection(new Point2D(shootingBubble.getDirection().get().getX() * -1, shootingBubble.getDirection().get().getY()));
    }

    /**
     * Method to calculate the angle between input and bubble.
     * @param eventPosition The position of the mouse.
     * @param shootingBubblePosition The position of the bubble.
     * @return the angle between input and bubble.
     */
    public static double calculateAngle(final Point2D eventPosition, final Point2D shootingBubblePosition) {
        final double hypotenuse = Math.sqrt(Math.pow(eventPosition.getX() - shootingBubblePosition.getX(), 2) 
                                + Math.pow(eventPosition.getY() - shootingBubblePosition.getY(), 2));
        final double cathetus = eventPosition.getX() - shootingBubblePosition.getX();
        return checkAngle(Math.toDegrees(Math.asin(cathetus / hypotenuse)));
    }

    /**
     * Private method that check if ana angle is too big or too small.
     * If it is necessary it change the angle.
     * 
     * @param  angle the angle to check.
     * @return the angle.
     */
    private static double checkAngle(final double angle) {
        if (angle > MAXANGLE) {
            return MAXANGLE;
        } else if (angle < MINANGLE) {
            return MINANGLE;
        }
        return angle;
    }

    /**
     * Method to calculate the angular coefficient used for the {@link} HelpLine}.
     * @param startPointSecondLine The starting point of the second line.
     * @param endPointSecondLine The ending point of the second line.
     * @return the angular coefficient between the two points.
     */
    public static double calculateAngularCoefficient(final Point2D startPointSecondLine, final Point2D endPointSecondLine) {
        return (endPointSecondLine.getY() - startPointSecondLine.getY()) / (endPointSecondLine.getX() - startPointSecondLine.getX());
    }

    /**
     * Private method that calculates the intercepts passing two points.     * 
     * @param startPointSecondLine The starting point of the second line.
     * @param endPointSecondLine The ending point of the second line.
     * @return The point of intersection of the lines.
     */
    public static double calculateIntercepts(final Point2D startPointSecondLine, final Point2D endPointSecondLine) {
        return (endPointSecondLine.getX() * startPointSecondLine.getY() 
              - startPointSecondLine.getX() * endPointSecondLine.getY()) / (endPointSecondLine.getX() - startPointSecondLine.getX());
    }

    /**
     * Private method that check if an angle created by two points is too high or too small.
     * @param startPointFirstLine The starting point of the first line.
     * @param startPointSecondLine The starting point of the second line.
     * @return True if the angle is lower than {@link ShootingBubble} position.
     */
    public static boolean angleTooHigh(final Point2D startPointFirstLine, final Point2D startPointSecondLine) {
        final double angle = PhysicHelper.calculateAngle(startPointFirstLine, startPointSecondLine);
        return !(angle > MAXANGLE - SENSIBILITY || angle < MINANGLE + SENSIBILITY);
    }
}
