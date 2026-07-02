package controller.stagehandler.opponentbehaviour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import controller.Binder;
import controller.ControllerEvent;
import controller.stagehandler.Time;
import javafx.application.Platform;
import model.ship.EnemyShip;
import model.ship.PlayerShip;
import model.ship.basic.BasicEnemyShip;
import model.weapon.Weapon;
import utilities.math.Vector2D;
import utilities.math.Vector2DImpl;

/**
 * The controller that manages a basic enemy ship movement, turning
 * and shooting.
 * @see Behaviour
 */
public class BasicOpponentAIImpl implements Behaviour {

   private final BasicEnemyShip enemy;
   private final Map<Weapon, Time> turnToFire = new HashMap<>();
   private final Collection<Weapon.Projectile> fired;
   private Vector2D target;
   /** This defines the distance up to which the enemy will not fire. */
   private static final double TRIGGER_DISTANCE = 40.0D;
   /** This defines a range of distance created to have multiple frame in which a enemy can fire after deceleration,
    *  to prevent the enemy to stands still in front of the player without shooting after reaching the trigger distance. */
   private static final double TRIGGER_DISTANCE_TOLERANCE = 2.0D;
   /** This defines the angle in which the enemies can fire. */
   private static final double FIELD_OF_VIEW = Math.PI / 45;

   public BasicOpponentAIImpl(final BasicEnemyShip enemy) {
       this.enemy = enemy;
       this.turnToFire.putAll(this.enemy.getWeapons().stream()
               .map(value -> Map.<Weapon, Time>entry(value, new Time()))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
       this.fired = new ArrayList<>();
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void evaluateStrategy(final PlayerShip player) {
       this.target = new Vector2DImpl(this.enemy.getPosition(), player.getPosition());
       if (isNearThePlayer(player)) {
           if (canSeeThePlayer()) {
               shoot();
           } else {
               turn();
           }
       } else {
           if (!canSeeThePlayer()) {
               turn();
           }
       } 
       move(player);
   }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void turn() {
        if ((this.target.angleOfThisVector() - enemy.getRotation()) % (Math.PI * 2) > (Math.PI)) {
            enemy.rotateClockwise(enemy.getMaxAngularSpeed());
        } else {
                enemy.rotateAnticlockwise(enemy.getMaxAngularSpeed());
        }
    }

    /**
     * {@inheritDoc} 
     */

    @Override
    public void move(final PlayerShip player) {
        if (enemy.getSpeed().equals(new Vector2DImpl())) {
            this.enemy.accelerate(new Vector2DImpl(this.enemy.getMaxAcceleration(), this.enemy.getRotation()));
        } else if (Math.abs(this.enemy.getPosition().distance(player.getPosition()) - TRIGGER_DISTANCE) > TRIGGER_DISTANCE_TOLERANCE) {
            if (!isNearThePlayer(player) && canSeeThePlayer()) {
                this.enemy.accelerate(this.target.multiplyByScalar(this.enemy.getMaxAcceleration() / this.target.module()));
            } else if (isNearThePlayer(player) && canSeeThePlayer()) {
                this.enemy.decelerate(this.target.multiplyByScalar(this.enemy.getMaxAcceleration() / this.target.module()));
            } else if (isNearThePlayer(player) && !canSeeThePlayer()) {
                this.enemy.decelerate(new Vector2DImpl(this.enemy.getMaxAcceleration(), this.enemy.getRotation()));
            }
        }
    }


    /**
     * {@inheritDoc} 
     */
    @Override
    public void shoot() {
        for (final Entry<Weapon, Time> weapon : this.turnToFire.entrySet()) {
            if (weapon.getValue().getElapsedTime() > weapon.getKey().getRateOfFire()) {
                this.fired.addAll(weapon.getKey().shoot(this.enemy.getPosition().translate(this.enemy.getSpeed()
                        .add(new Vector2DImpl(this.enemy.getRadialHitbox(), this.enemy.getRotation())).components()), this.enemy.getRotation()));
                weapon.setValue(new Time());
                Platform.runLater(() -> Binder.getView().receiveEvent(ControllerEvent.SHOOT));
            }
        }
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public EnemyShip getEnemy() {
        return this.enemy;
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public Collection<Weapon.Projectile> getFiredProjectiles() {
        final Collection<Weapon.Projectile> fired = new ArrayList<>(this.fired);
        this.fired.clear();
        return fired;
    }

    /**
     * Check if the enemy can see the player, based on a field of view tolerance.
     * @return true if the angle of the vector between the enemy and the player minus
     * the angle of the enemy rotation is lesser than the field of view angle.
     */
    private boolean canSeeThePlayer() {
        return (Math.abs(this.target.angleOfThisVector() - this.enemy.getRotation())) % (Math.PI * 2) <= FIELD_OF_VIEW;
    }

    /**
     * Check if the enemy is near the player in the stage, based on a trigger distance.
     * @param player the current player in the stage
     * @return true if the distance between the enemy and the player position
     * is lesser than the trigger distance
     */
    private boolean isNearThePlayer(final PlayerShip player) {
        return this.enemy.getPosition().distance(player.getPosition()) <= (TRIGGER_DISTANCE + TRIGGER_DISTANCE_TOLERANCE);
    }

}
