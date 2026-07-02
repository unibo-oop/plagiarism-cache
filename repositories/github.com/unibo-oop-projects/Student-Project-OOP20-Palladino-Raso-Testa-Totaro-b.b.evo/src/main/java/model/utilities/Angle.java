package model.utilities;

/**
 * 
 * Returns an angle based on where the paddle hits. 
 * The more the ball hits to the left, the wider the angle will be.
 * 
 */
public enum Angle { 
    /**
     * Used to represent the left edge of the paddle.
     */
    LEFT(150),
    /**
     * Used to represent the middle-left portion of the paddle.
     */
    MIDDLE_LEFT(130),
    /**
     * Used to represent the middle-right portion of the paddle.
     */
    MIDDLE_RIGHT(50),
    /**
     * Used to represent the right edge of the paddle.
     */
    RIGHT(30);

    private int angle;
    private static int versor = 1;

    /**
     * 
     * @param angle sets the angle based on hit by the ball.
     */
    Angle(final int angle) {
        this.angle = angle;
    }

    /**
     * Used to calculate the angle hit by the ball.
     * @return DirVector final direction 
     */
    public DirVector getAngleVector() {
        return new DirVector(Math.cos(Math.toRadians(angle)) * versor, Math.sin(Math.toRadians(angle)) * versor);
    }
}
