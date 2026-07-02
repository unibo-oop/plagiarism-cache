package controller.output;

import java.util.List;
import model.utility.Pair;

/**
 * Interface of the controller output.
 */
public interface ControllerOutput {

    /**
     * Transform the position the list of {@link Projectile} from the {@link Model}
     * to the {@link View}.
     * 
     * @return a list of position as {@link Pair}.
     */
    List<Pair<Double, Double>> getProjectiles();

    /**
     * Getter of the {@link Projectile} dimension.
     * 
     * @return a {@link Pair} of the projectile dimension.
     */
    Pair<Double, Double> getProjectileDimension();

    /**
     * Getter of the {@link View} position of the player {@link Tank}.
     * 
     * @return the new position as a {@link Pair}.
     */
    Pair<Double, Double> getPlayerPosition();

    /**
     * Getter of the {@link View} position of the enemy {@link Tank}.
     * 
     * @return the new position as a {@link Pair}.
     */
    Pair<Double, Double> getEnemyPosition();

    /**
     * Getter of the lifes of the player {@link Tank}.
     * 
     * @return the current lifes of the tank.
     */
    int getPlayerLifes();

    /**
     * Getter of the lifes of the enemy {@link Tank}.
     * 
     * @return the current lifes of the tank.
     */
    int getEnemyLifes();

    /**
     * Getter of the {@link View} dimension of the {@link Tank}.
     * 
     * @return a {@link Pair} of dimension of the tank.
     */
    Pair<Double, Double> getTankDimension();

    /**
     * Check if the player {@link Tank} is alive or not.
     * 
     * @return true if it's alive, false otherwise.
     */
    boolean isPlayerAlive();

    /**
     * Check if the enemy {@link Tank} is alive or not.
     * 
     * @return true if it's alive, false otherwise.
     */
    boolean isEnemyAlive();

    /**
     * Getter of the {@link View} dimension of the {@link Cannon}.
     * 
     * @return a {@link Pair} of dimension of the cannon.
     */
    Pair<Double, Double> getCannonDimension();

    /**
     * Getter of the {@link View} position of the player {@link Cannon}.
     * 
     * @return a {@link Pair} of position of the player cannon.
     */
    Pair<Double, Double> getPlayerCannonPosition();

    /**
     * Getter of the {@link View} position of the enemy {@link Cannon}.
     * 
     * @return a {@link Pair} of position of the enemy cannon.
     */
    Pair<Double, Double> getEnemyCannonPosition();

    /**
     * Getter of the player {@link Cannon} angle.
     * 
     * @return the angle.
     */
    double getPlayerAngle();

    /**
     * Getter of the enemy {@link Cannon} angle.
     * 
     * @return the angle.
     */
    double getEnemyAngle();

}
