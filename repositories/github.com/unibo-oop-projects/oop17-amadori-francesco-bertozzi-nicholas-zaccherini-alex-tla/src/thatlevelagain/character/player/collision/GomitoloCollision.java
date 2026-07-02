package thatlevelagain.character.player.collision;

import thatlevelagain.character.enemies.GomitoloImpl;
import thatlevelagain.character.player.Player;

/**
 * 
 */
public final class GomitoloCollision {




    private GomitoloCollision() {
    }

    /**
     * 
     * @param player
     *          Object of class Player.
     * @param gomitolo
     *          Object if class GomitoloImpl.
     */
    public static void intersection(final Player player, final GomitoloImpl gomitolo) {
        if (player.getRectRight().intersects(gomitolo.getRectLeft())
                || player.getRectRight().intersects(gomitolo.getRectRight())
                    || player.getRectUp().intersects(gomitolo.getRectBottom())
                        || player.getRectBottom().intersects(gomitolo.getRectUp())) {
            player.restartLevel();
            gomitolo.restartLevel();
        }
    }

}
