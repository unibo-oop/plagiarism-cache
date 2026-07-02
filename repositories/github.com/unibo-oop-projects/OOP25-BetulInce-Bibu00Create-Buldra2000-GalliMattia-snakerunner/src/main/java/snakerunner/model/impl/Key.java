package snakerunner.model.impl;

import snakerunner.audio.AudioPlayer;
import snakerunner.commons.Point2D;
import snakerunner.model.Collectible;
import snakerunner.model.CollectibleType;
import snakerunner.model.GameModel;

/**
 * Represents a key collectible in the Snake Runner game. 
 * When consumed, it can trigger specific effects such as opening doors.
 */
public class Key implements Collectible {
    private static final String KEY_SOUND = "sounds/key.wav";
    private final Point2D<Integer, Integer> position;

    /**
     * Constructs a Key collectible at the specified position.
     *
     * @param position The (x, y) coordinates where the key is located in the game world.
     */
    public Key(final Point2D<Integer, Integer> position) {
        this.position = new Point2D<>(position.getX(), position.getY());
    }

    /** 
     * Consumes the key, applying its effect to the game model. 
     * This method can be expanded to include specific logic for what happens when the key is consumed.
     *
     * @param model The game model to which the key's effect will be applied.
     */
    @Override
    public void consume(final GameModel model) {
        AudioPlayer.playSound(KEY_SOUND);
        model.openDoor();
    }

    /** 
     * Gets the position of the key in the game world.
     *
     * @return A Point2D representing the (x, y) coordinates of the key.
     */
    @Override
    public Point2D<Integer, Integer> getPosition() {
        return new Point2D<>(position.getX(), position.getY());
    }

    /**
     * Gets the type of the collectible, which is KEY for this class.
     * 
     * @return The CollectibleType of this collectible, which is KEY.
     */
    @Override
    public CollectibleType getType() {
        return CollectibleType.KEY;
    }
}
