package snakerunner.model.impl;

import snakerunner.audio.AudioPlayer;
import snakerunner.commons.Point2D;
import snakerunner.model.Collectible;
import snakerunner.model.CollectibleType;
import snakerunner.model.GameModel;

/**
 * The Flag class represents a collectible item in the Snake Runner game that,
 *  when consumed, completes the level.
 */
public final class Flag implements Collectible {
    private static final String SOUND_FLAG = "sounds/flag.wav";
    private final Point2D<Integer, Integer> position;

    /**
     * Constructs a Flag collectible at the specified position.
     *
     * @param position The (x, y) coordinates where the flag is located in the game world.
     */
    public Flag(final Point2D<Integer, Integer> position) {
        this.position = new Point2D<>(position.getX(), position.getY());
    }

    @Override
    public void consume(final GameModel model) {
        AudioPlayer.playSound(SOUND_FLAG);
        model.completeLevel(); /* A level is completed when the flag is consumed */
    }

    @Override
    public CollectibleType getType() {
    return CollectibleType.FLAG;

    }

    @Override
    public Point2D<Integer, Integer> getPosition() {
        return new Point2D<>(position.getX(), position.getY());
    }
}
