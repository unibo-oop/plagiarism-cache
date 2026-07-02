package spacesurvival.model.commandship.command.implementation;

import spacesurvival.utilities.gameobject.VelocityUtils;
import spacesurvival.model.commandship.command.CommandShip;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;

/**
 * Implementation for up command.
 */
public class Up implements CommandShip {

    /** 
     * Accelerate the ship forward.
     * 
     * @param ship the controlled ship 
     */
    @Override
    public void execute(final SpaceShipSingleton ship) {
        V2d vel = ship.getVelocity();
        if (Math.abs(vel.getY()) < VelocityUtils.SPACESHIP_TOLLERANCE_RESTART) {
            vel = new V2d(vel.getX(), -VelocityUtils.SPACESHIP_STARTING_VEL);
            ship.setVelocity(vel);
        }
        ship.setAcceleration(VelocityUtils.SPACESHIP_ACCELERATION);
    }
}
