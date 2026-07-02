package rogue.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import rogue.model.creature.Player;
import rogue.model.world.Direction;
import rogue.model.world.World;
import rogue.model.world.WorldImpl;
import rogue.view.WorldBox;

/**
 * the {@link World} controller.
 */
public class WorldController {
    private final World world;
    private final WorldBox worldBox;

    /**
     * @param player the player instance
     */
    public WorldController(final Player player) {
        this.world = new WorldImpl(player);
        this.worldBox = new WorldBox(this.world.getWidth(), this.world.getHeight());
        this.worldBox.drawTiles(this.world.getTiles());
        this.worldBox.drawEntities(this.world.getEntityMap());
    }

    /**
     * @return the current level view
     */
    public final WorldBox getWorldBox() {
        return worldBox;
    }

    /**
     * move player and perform a game round.
     * @param event the key press event
     */
    public void movePlayer(final KeyEvent event) {
        // game over
        if (this.world.getPlayer().getLife().isDead()) {
            worldBox.drawGameOver();
            return;
        }

        final KeyCode key = event.getCode();

        Direction direction = Direction.NONE;

        switch (key) {
            case LEFT:
            case H:
                direction = Direction.WEST;
                break;
            case RIGHT:
            case L:
                direction = Direction.EAST;
                break;
            case UP:
            case K:
                direction = Direction.NORTH;
                break;
            case DOWN:
            case J:
                direction = Direction.SOUTH;
                break;
            default:
                break;
        }

        // update tiles only if level is changed
        if (world.round(direction)) {
            worldBox.drawTiles(this.world.getTiles());
        }

        // always update entities
        worldBox.drawEntities(this.world.getEntityMap());
    }
}
