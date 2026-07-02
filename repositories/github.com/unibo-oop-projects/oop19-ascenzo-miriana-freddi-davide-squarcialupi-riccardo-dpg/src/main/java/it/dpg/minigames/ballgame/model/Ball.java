package it.dpg.minigames.ballgame.model;

public class Ball {
    private final double radius;
    private final double startX;
    private final double startY;
    private final double ballAcceleration;
    private final double ballDeceleration;
    private final double maxSpeed;
    private double centerX;
    private double centerY;
    private double xSpeed = 0;
    private double ySpeed = 0;

    public Ball(double radius, double startX, double startY, double ballAcceleration, double ballDeceleration, double maxSpeed) {
        this.ballAcceleration = ballAcceleration;
        this.ballDeceleration = ballDeceleration;
        this.maxSpeed = maxSpeed;
        this.radius = radius;
        this.startX = startX;
        this.startY = startY;
        this.centerX = startX;
        this.centerY = startY;
    }

    public double getRadius() {
        return radius;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * reset the ball to the starting point, reset speeds to 0
     */
    public void reset() {
        this.centerY = startY;
        this.centerX = startX;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    /**
     * calculate the position of the ball in the next deltaT time
     * @param deltaT quantity of time that passed from the last movement
     */
    public void calculateNextPosition(boolean isGoingUp, boolean isGoingDown, boolean isGoingLeft, boolean isGoingRight, double deltaT) {
        calculateSpeed(isGoingUp, isGoingDown, isGoingLeft, isGoingRight, deltaT);
        calculatePosition(deltaT);
    }

    private void calculateSpeed(boolean isGoingUp, boolean isGoingDown, boolean isGoingLeft, boolean isGoingRight, double deltaT) {
        double xAcc;
        double yAcc;
        xAcc = computeAcceleration(xSpeed, isGoingRight, isGoingLeft);
        yAcc = computeAcceleration(ySpeed, isGoingUp, isGoingDown);
        double xSpeedPrev = this.xSpeed;
        double ySpeedPrev = this.ySpeed;
        xSpeed = xSpeed + (xAcc * deltaT);
        ySpeed = ySpeed + (yAcc * deltaT);
        if ((xSpeed > 0 && xSpeedPrev < 0) || (xSpeed < 0 && xSpeedPrev > 0)) {
            xSpeed = 0;
        }
        if ((ySpeed > 0 && ySpeedPrev < 0) || (ySpeed < 0 && ySpeedPrev > 0)) {
            ySpeed = 0;
        }
        xSpeed = limitVal(xSpeed, -maxSpeed, maxSpeed);
        ySpeed = limitVal(ySpeed, -maxSpeed, maxSpeed);
    }

    private double computeAcceleration(double speed, boolean isGoingForward, boolean isGoingBackwards) {
        double a;
        if ((isGoingForward && isGoingBackwards) || (!isGoingForward && !isGoingBackwards)) {
            if (speed > 0) {
                return -ballDeceleration;
            } else if (speed < 0) {
                return ballDeceleration;
            }
            return 0;
        } else if (isGoingForward) {
            a = ballAcceleration;
            if (speed < 0) {
                a += ballDeceleration;
            }
        } else {
            a = -ballAcceleration;
            if (speed > 0) {
                a -= ballDeceleration;
            }
        }
        return a;
    }

    private void calculatePosition(double deltaT) {
        this.centerX = centerX + xSpeed * deltaT;
        this.centerY = centerY + ySpeed * deltaT;
    }

    private double limitVal(double val, double lowerBound, double upperBound) {
        val = Math.min(val, upperBound);
        return Math.max(val, lowerBound);
    }
}
