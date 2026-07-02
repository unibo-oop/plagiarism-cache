package thatlevelagain.character.player.collision;


import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.End;

/**
 * 
 */
public final class EndLevelCollision {

    /**
     * 
     */
    private EndLevelCollision() {
    }

    /**
     * 
     * @param player
     *          Object of class Player.
     * @param end
     *          Object of class End.
     */
    public static void intersection(final Player player, final End end) {
        if (player.getRectRight().intersects(end.getRectLeft())) {
            player.getMap().nextLevel();
        }
    }

}
