package ala.models;

import ala.models.LevelGeneratorPatternModel.OBJECTSTYPE;

/**
 * DynamicGameObjectModel class.
 * 
 */
public abstract class DynamicGameObjectModel extends GameObjectModel {
    //Attributes:
     private double dx;
     private double dy;
     private double dr;

     //constructors:
     public DynamicGameObjectModel() { }

     /**
      * Constructor.
      * 
      * @param x
      * @param y
      * @param r
      * @param type
      * @param width
      * @param height
      * @param dx
      * @param dy
      * @param dr
      * @param damageOnContact
      * 
      */
     public DynamicGameObjectModel(final double x, final double y, final double r, final OBJECTSTYPE type, final double width, final double height, final double dx, final double dy, final double dr, final double damageOnContact) {
         super(x, y, r, type, width, height, damageOnContact);
         this.dx = dx;
         this.dy = dy;
         this.dr = dr;
     }

     //Getters&Setters:
     public final double getDx() {
         return dx;
     }
     public final void setDx(final double dx) {
         this.dx = dx;
     }
     public final double getDy() {
         return dy;
     }
     public final void setDy(final double dy) {
         this.dy = dy;
     }
     public final double getDr() {
         return dr;
     }
     public final void setDr(final double dr) {
         this.dr = dr;
     }

    //Methods:
     /**
      * make DynamicGameObject move.
      * 
      */
    public void move() {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
        this.setR(this.getR() + dr);

        this.moveHitBox(dx, dy);
    }

    /**
     * make hitBox move.
     * 
     * @param dx
     * @param dy
     * 
     */
    public final void moveHitBox(final double dx, final double dy) {
        this.getHitBox().setLeftX(this.getHitBox().getLeftX() + dx);
        this.getHitBox().setRightX(this.getHitBox().getRightX() + dx);
        this.getHitBox().setLowY(this.getHitBox().getLowY() + dy);
        this.getHitBox().setHighY(this.getHitBox().getHighY() + dy);
    }

    /**
     * make manual hitBox move.
     * 
     * @param leftX
     * @param rightX
     * @param highY
     * @param lowY
     * 
     */
    public final void manualHitBoxMovement(final double leftX, final double rightX, final double highY, final double lowY) {
        this.getHitBox().setLeftX(leftX);
        this.getHitBox().setRightX(rightX);
        this.getHitBox().setLowY(lowY);
        this.getHitBox().setHighY(highY);
    }
}
