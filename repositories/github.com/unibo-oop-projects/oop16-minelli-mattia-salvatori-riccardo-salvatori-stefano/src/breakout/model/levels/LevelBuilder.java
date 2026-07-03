package breakout.model.levels;

import java.util.Objects;

import breakout.model.entities.BrickType;
import breakout.view.graphics.Backgrounds;
import breakout.view.graphics.Colors;
import javafx.util.Pair;

/**
 * The class to create a new Level.
 *
 */
public class LevelBuilder {

    private final LevelImpl level = new LevelImpl();

    /**
     * Add the list of bricks in the new level.
     * 
     * @param brickList
     *            the list to add
     * @return himself
     * @throws IllegalArgumentException
     *             if the argument is null or is an empty string
     */
    public LevelBuilder list(final Grid<Pair<BrickType, Colors>> brickList) throws IllegalArgumentException {
        if (!brickList.isEmpty()) {
            this.level.setList(brickList);
            return this;
        } else {
            throw new IllegalArgumentException("No bricks in the list");
        }
    }

    /**
     * Add the background image in the new level.
     * 
     * @param image
     *            the background image
     * @return himself
     * @throws IllegalArgumentException
     *             if the argument is null or is an empty string
     * @throws NullPointerException
     *             if the argument is null or is an empty string
     */
    public LevelBuilder background(final Backgrounds image) throws IllegalArgumentException, NullPointerException {
        this.level.setBackground(image);
        return this;
    }

    /**
     * Add the name in the new level.
     * 
     * @param name
     *            the name
     * @return himself
     * @throws IllegalArgumentException
     *             if the argument is null or is an empty string
     * @throws NullPointerException
     *             if the argument is null or is an empty string
     */
    public LevelBuilder name(final String name) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(name);
        if (!name.isEmpty()) {
            this.level.setName(name);
            return this;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    /**
     * Add the spawn probability in the new level.
     * @param prob
     * the probability to spawn bonus
     * @return himself
     * @throws IllegalArgumentException
     * if the argument is less the 0 or it's greater then 100
     */
    public LevelBuilder spawnProb(final int prob) throws IllegalArgumentException {
        if (prob >= 0 && prob <= 100) {
            this.level.setSpawn(prob);
            return this;
        } else {
            throw new IllegalArgumentException("invalid probability");
        }
    }

    /**
     * 
     * @return the complete level
     */
    public LevelImpl build() {
        return this.level;
    }
}
