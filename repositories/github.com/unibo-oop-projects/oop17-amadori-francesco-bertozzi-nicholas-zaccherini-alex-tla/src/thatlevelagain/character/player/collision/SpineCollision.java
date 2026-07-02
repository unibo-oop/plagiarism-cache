package thatlevelagain.character.player.collision;

import java.util.List;

import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.Spine;
import thatlevelagain.view.sprite.SpineAlte;

/**
 *
 */
public final class SpineCollision {

    /**
     * 
     */
    private SpineCollision() {
    }

    /**
     * @param player
     *          Object of class Player.
     * @param upperPlug
     *          Spines located with their tips downward.
     * @param lowerPlug
     *          Spines located with their tips upward.
     * @return a boolean that indicate if the player is colliding to plug.
     */
    public static boolean intersection(final Player player, final List<SpineAlte> upperPlug, final List<Spine> lowerPlug) {
        final int notPlug = 20;
        for (final SpineAlte plug : upperPlug) {
            if (plug.getTime() == notPlug) {
                return false;
            } else if (player.getRectBottom().intersects(plug.getRectUp())) {
                return true;
            }
        }
        for (final Spine plug : lowerPlug) {
            if (plug.getTime() == notPlug) {
                return false;
            } else if (player.getRectUp().intersects(plug.getRectBottom())) {
                return true;
            }
        }
        return false;
    }

}
