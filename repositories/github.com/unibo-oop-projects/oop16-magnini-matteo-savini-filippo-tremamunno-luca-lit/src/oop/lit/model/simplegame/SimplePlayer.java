package oop.lit.model.simplegame;

import oop.lit.model.PlayerModel;
import oop.lit.model.game.Player;

/**
 * A player with the concept of gm players; a gm player should be able to perform an action even if he has no permissions to perform it.
 */
public class SimplePlayer extends Player {
    /**
     * 
     */
    private static final long serialVersionUID = 2200275777852698910L;
    private final boolean gm;
    /**
     * @param name
     *      the player name.
     * @param gm
     *      if the player is a gm.
     */
    public SimplePlayer(final String name, final boolean gm) {
        super(name);
        this.gm = gm;
    }

    /**
     * @return
     *      if the player is a gm.
     */
    public boolean isGM() {
        return this.gm;
    }

    /**
     * @param player
     *      a player
     * @return
     *      if the player is a (SimplePlayer) gm.
     */
    public static boolean isPlayerGM(final PlayerModel player) {
        return (player instanceof SimplePlayer) && ((SimplePlayer) player).isGM();
    }
}
