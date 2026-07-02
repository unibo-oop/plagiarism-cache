package ala.models;

/**
 * HitBox class.
 * 
 */
public class HitBox implements HitBoxFeatures {
    //Attributes:
    private double leftX;
    private double rightX;
    private double highY;
    private double lowY;

    //Constructor:
    /**
     * Constructor.
     * 
     * @param leftX
     * @param rightX
     * @param highY
     * @param lowY
     * 
     */
    public HitBox(final double leftX, final double rightX, final double highY, final double lowY) {
        this.leftX = leftX;
        this.rightX = rightX;
        this.highY = highY;
        this.lowY = lowY;
    }

    //Getters&Setters:
    public final double getLeftX() {
        return leftX;
    }

    public final void setLeftX(final double leftX) {
        this.leftX = leftX;
    }

    public final double getRightX() {
        return rightX;
    }

    public final void setRightX(final double rightX) {
        this.rightX = rightX;
    }

    public final double getHighY() {
        return highY;
    }

    public final void setHighY(final double highY) {
        this.highY = highY;
    }

    public final double getLowY() {
        return lowY;
    }

    public final void setLowY(final double lowY) {
        this.lowY = lowY;
    }

    //Methods:
    /**
     * check collisions between hitBox.
     * 
     * @param otherHitBox
     * 
     */
    @Override
    public final boolean checkCollision(final HitBox otherHitBox) {
        if (this.getRightX() >= otherHitBox.getLeftX() && this.getLeftX() <= otherHitBox.getRightX()) {
            if (this.getHighY() <= otherHitBox.getLowY() && this.getLowY() >= otherHitBox.getHighY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * move hitBox setting specific values.
     * 
     * @param leftX
     * @param rightX
     * @param highY
     * @param lowY
     * 
     */
   @Override
    public final void manualHitBoxMovement(final double leftX, final double rightX, final double highY, final double lowY) {
        this.setLeftX(leftX);
        this.setRightX(rightX);
        this.setLowY(lowY);
        this.setHighY(highY);
    }
}
