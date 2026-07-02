package model.entity;

import java.awt.geom.Point2D.Double;
import javafx.scene.image.Image;
import model.Model;

/**
 * 
 * Class identifying a game's Obstacle.
 *
 */
public final class Obstacle extends AbstractDynamicEntity {

    private static final int DOWN_LIFE = -1;

    /**
     * 
     * @param coordinates the coordinates of the {@link Obstacle} on the screen.
     * @param image the image identifying an {@link Obstacle}.
     * @param level the level on which {@link Obstacle} should spawn.
     * @param type the type identifying an {@link Obstacle}.
     * @param distance distance after that next entity should spawn.
     */
    public Obstacle(final Double coordinates, final Image image, final SpawnLevel level, final EntityType type, final double distance) {
        super(coordinates, image, level, type, distance);
    }

    /**
     * Method called when an {@link Obstacle} collides with the player, it decreases 
     * player's lives. 
     * @param model the model that the actual game state. 
     */
    @Override
    protected void activateEffect(final Model model) {
        model.getGameState().getPlayer().setNumberOfLives(DOWN_LIFE);
    }
}
