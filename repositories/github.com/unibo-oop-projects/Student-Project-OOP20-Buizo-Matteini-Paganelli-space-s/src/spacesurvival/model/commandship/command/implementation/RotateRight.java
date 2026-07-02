package spacesurvival.model.commandship.command.implementation;

import java.awt.geom.AffineTransform;

import spacesurvival.model.commandship.command.CommandShip;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.utilities.gameobject.VelocityUtils;

/**
 * Implementation for rotate right command.
 */
public class RotateRight implements CommandShip {

    /** 
     * Rotate right the ship.
     * 
     * @param ship the controlled ship 
     */
    @Override
    public void execute(final SpaceShipSingleton ship) {
        final AffineTransform transform = ship.getTransform();
        transform.rotate(Math.toRadians(VelocityUtils.SPACESHIP_ROTATION), ship.getSize().getWidth() / 2, ship.getSize().getHeight() / 2);
        ship.setTransform(transform);
    }
}
