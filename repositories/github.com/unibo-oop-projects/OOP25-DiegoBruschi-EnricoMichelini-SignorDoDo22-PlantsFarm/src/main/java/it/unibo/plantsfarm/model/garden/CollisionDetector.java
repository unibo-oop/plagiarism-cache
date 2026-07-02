package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;

import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.tiles.SolidBlock;
import it.unibo.plantsfarm.model.tiles.TileMap;

/**
 * Handles collision detection for the player in the game, preventing movement through solid blocks.
 */
public final class CollisionDetector {

    private Player player;
    private final TileMap map = new TileMap();

    /**
     * Creates a new CollisionDetector for the specified player.
     *
     * @param player The player for whom collision detection will be performed.
     */
    public CollisionDetector(final Player player) {
        this.map.loadMap("/maps/map.txt");
        setPlayer(player);
    }

    /**
     * Performs collision detection for the player, preventing movement through solid blocks
     * by checking the player's future position against the positions of solid blocks in the map.
     * If a collision is detected, the player's movement is not applied.
     */
    public void collisionDetection() {

        final double offsetX = player.getNextPosX() - player.getPosx();
        final double offsetY = player.getNextPosY() - player.getPosy();

        final Rectangle currentHitbox = player.getHitBox();

        final Rectangle futureHitbox = new Rectangle(
            (int) (currentHitbox.x + offsetX),
            (int) (currentHitbox.y + offsetY),
            currentHitbox.width,
            currentHitbox.height
        );

        for (final SolidBlock tile : map.getSolidBlocks()) {
            if (tile.getCoordinate().intersects(futureHitbox)) {
                return;
            }
        }
        player.applyMovement();
    }

    private void setPlayer(final Player givenPlayer) {
        this.player = givenPlayer;
    }
}
