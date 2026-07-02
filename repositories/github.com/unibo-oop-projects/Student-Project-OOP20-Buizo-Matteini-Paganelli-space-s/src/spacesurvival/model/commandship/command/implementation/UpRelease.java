package spacesurvival.model.commandship.command.implementation;

import spacesurvival.model.commandship.command.CommandShip;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.utilities.gameobject.VelocityUtils;

/**
 * Implementation for up release command.
 */
public class UpRelease implements CommandShip {

    /** 
     * Decelerate the ship.
     * 
     * @param ship the controlled ship 
     */
    @Override
    public void execute(final SpaceShipSingleton ship) {
        ship.setAcceleration(VelocityUtils.SPACESHIP_DECELERATION);
    }
}
