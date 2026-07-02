package controller.stagehandler.opponentbehaviour;

import java.util.Collection;

import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.weapon.Weapon;

/**
 * Defines all possible actions and controls all enemies' AI must have.
 */
public interface Behaviour {

     /**
      * Evaluate all enemy's possible actions.
      * @param player the player in the stage
      */
     void evaluateStrategy(PlayerShip player);

     /**
      * Evaluate when the enemy must shoot at the player.
      */
     void shoot();

     /**
      * Evaluate when the enemy must turn to the player.
      */
     void turn();

     /**
      * Evaluate when the enemy must follow the player.
      * @param player the player in the stage
      */
     void move(PlayerShip player);

     /**
      * Returns the current enemy.
      * @return the current enemy.
      */
     EnemyShip getEnemy();

     /**
      * Return all projectiles fired by the enemy.
      * @return all projectiles fired by the enemy.
      */
     Collection<Weapon.Projectile> getFiredProjectiles();

}
