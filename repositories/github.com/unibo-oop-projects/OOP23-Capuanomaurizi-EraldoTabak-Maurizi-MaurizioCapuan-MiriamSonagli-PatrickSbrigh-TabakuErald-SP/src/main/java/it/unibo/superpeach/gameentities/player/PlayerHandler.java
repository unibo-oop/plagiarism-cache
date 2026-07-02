package it.unibo.superpeach.gameentities.player;

import java.awt.Graphics;
import java.util.Optional;

/**
 * This class manages the player.
 * @author Patrick Sbrighi
 */
public final class PlayerHandler {
    private Optional<Player> player;

    /**
     * Class constructor.
     */
    public PlayerHandler() {
        this.player = Optional.empty();
    }

    /**
     * This method allows to set the player.
     * @param p The player to set
     */
    public void takePlayer(final Player p) {
        if (!this.player.isPresent()) {
            this.player = Optional.of(p);
        }
    }

    /**
     * This method returns the player.
     * @return Return the player
     */
    public Player getPlayer() {
        if (!this.player.isPresent()) {
            return null;
        }
        return this.player.get();
    }

    /**
     * This method removes the player.
     */
    public void removePlayer() {
        if (!player.isPresent()) {
            this.player = Optional.empty();
        }
    }

    /**
     * This method calls the player tick.
     */
    public void tick() {
        this.player.get().tick();
    }

    /**
     * This method calls the player render.
     * @param g The graphics
     */
    public void render(final Graphics g) {
        if (this.player.isPresent()) {
            this.player.get().render(g);
        }
    }
}
