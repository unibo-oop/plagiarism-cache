package model.shootstate;

import java.util.List;
import java.util.Optional;

import model.entities.Bullet;
import model.entities.properties.Position;

/**
 * 
 * Encapsulates the behavior associated with a state.
 *
 */
public interface ShootState {

    /**
     * Set the next state.
     * 
     * @param nextState
     *          the next state.
     */
    void setNextState(ShootState nextState);

    /**
     * 
     * Set the previous state.
     * 
     * @param previosState
     *          the previosState.
     */
    void setPreviosState(ShootState previosState);

    /**
     * 
     * @return the next state.
     */
    Optional<ShootState> getNextState();

    /**
     * 
     * @return the previous state.
     */
    Optional<ShootState> getPreviosState();

    /**
     * 
     * @param spaceshipPosition
     *          the spaceship's position.
     * @param bulletDamage
     *          the damage of the bullet.
     * @return list of the new bullets.
     */
    List<Bullet> shoot(Position spaceshipPosition, double bulletDamage);

}
