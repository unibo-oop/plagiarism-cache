package spacesurvival.model.commandship.command.implementation;

import spacesurvival.model.commandship.command.CommandShip;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.utilities.path.SoundPath;

/**
 * Implementation for fire command.
 */
public class Fire implements CommandShip {
    /**
     * Fires the shot from the ship's weapon if it is present.
     * 
     * @param ship the controlled ship
     */
    @Override
    public void execute(final SpaceShipSingleton ship) {
        if (ship.getWeapon().canShoot()) {
            ship.getWeapon().canShoot(false);
            new Thread(() -> {
                ThreadUtils.sleep(Delay.BULLET_SHOT);
                ship.getWeapon().canShoot(true);
            }).start();
            ship.fire();
            ship.getSoundQueue().add(SoundPath.SHOOT);
        }
    }
}
