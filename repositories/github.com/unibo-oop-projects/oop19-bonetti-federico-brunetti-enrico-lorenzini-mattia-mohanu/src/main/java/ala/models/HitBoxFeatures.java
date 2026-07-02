package ala.models;

public interface HitBoxFeatures {
    /**
      * check collisions between hitBox.
      * 
      * @param otherHitBox
      * @return boolean
      */
    boolean checkCollision(HitBox otherHitBox);

    /**
      * move hitBox setting specific values.
      * 
      * @param leftX
      * @param rightX
      * @param highY
      * @param lowY
      * 
      */
   void manualHitBoxMovement(double leftX, double rightX, double highY, double lowY);
}
