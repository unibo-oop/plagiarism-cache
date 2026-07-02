package thatlevelagain.character.player.collision;

import thatlevelagain.character.enemies.CarImpl;
import thatlevelagain.character.player.Player;

/**
 * 
 */
public final class AccalappiaGattiCollision {



    private AccalappiaGattiCollision() {
    }

    /**
     * @param player
     *          Object of class Player.
     * @param accalappiaGatti
     *          Object of class CarImpl.
     */
    public static void intersection(final Player player, final CarImpl accalappiaGatti) {
        if (player.getRectRight().intersects(accalappiaGatti.getRectLeft())
                || player.getRectLeft().intersects(accalappiaGatti.getRectRight())
                    || player.getRectUp().intersects(accalappiaGatti.getRectBottom())
                        || player.getRectBottom().intersects(accalappiaGatti.getRectUp())) {
            player.restartLevel();
            accalappiaGatti.restartLevel();
        }
    }
}
