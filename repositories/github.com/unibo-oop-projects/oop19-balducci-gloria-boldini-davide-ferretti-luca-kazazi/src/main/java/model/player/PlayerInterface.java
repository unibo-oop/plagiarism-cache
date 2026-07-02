package model.player;

import controllers.movement.Movement;
import controllers.movement.animation.Animation;
import controllers.timer.GameTime;
import model.staticObject.ActiveStaticObject;

/**
 * Interface for entity Player.
 * 
 *
 */
public interface PlayerInterface {

    /**
     * @param speed the new player speed.
     */
    void setSpeed(double speed);

    /**
     * @return speed of the player
     */
     double getSpeed();

     /**
      *  method called when the player collides with an enemy or his ray.
      */
     void removeLife();

     /**
      * @return the health of the player
      */
     int getHealth();

     /**
      * @return gameOver true if the game is over, false otherwise
      */
     boolean isGameOver();

     /**
      * @param gameOver boolean, true if the game is over (won or lost)
      */
     void setGameOver(boolean gameOver);

     /**
      *  called when the player picks up the lifeup powerup.
      */
     void addLife();

     /**
      * @param newKnife boolean to set
      */
     void setKnife(boolean newKnife);

     /**
      * @param recovering boolean to set
      */
     void setRecovering(boolean recovering);

     /**
      * @return recovering -> true if the player is recovering
      */
     boolean isRecovering();

     /**
      * @return the object animation
      */
      Animation getAnimation();

      /**
       * @return the object movement
       */
      Movement getMovement();

      /**
       * @return the active powerup/debuff
       */
      ActiveStaticObject getActivePowerUpDebuff();

      /**
       * @return true if player has knife
       */
      boolean hasKnife();

      /**
       * removes the active static object.
       */
      void removePowerUpDebuff();

      /**
       * @param staticObject the new active powerup/debuff that has to be displayed
       */
      void setPowerUpDebuff(ActiveStaticObject staticObject);

      /**
       * @return timer
       */
      GameTime getTimer();

      /**
       * @param time the timer that has to be set
       */
      void setTimer(GameTime time);

}
