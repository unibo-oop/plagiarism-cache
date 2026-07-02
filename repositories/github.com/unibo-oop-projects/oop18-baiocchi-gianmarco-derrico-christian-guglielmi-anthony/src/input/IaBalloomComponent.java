package input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import graphics.SpriteSheet;
import model.BalloomEnemy;
import physics.Direction;

/**
 * This class implements BalloomEnemy InputComponent.
 * It generates a new random move to be performed by A BalloomEnemy,
 * every three second passed by last move.
 */
public class IaBalloomComponent extends AbstractInputComponent implements EnemyInputComponent {

    private static final int TILES_PER_SECONDS = 3;
    private final List<Direction> directions;
    private int difficultyLvl = 1;
    private final double pixelToSeconds;
    private final Random randomizer;

    /**
     * Creates IaBalloomComponent.
     * @param en : BalloomEnemy new command is referred to.
     */
    public IaBalloomComponent(final BalloomEnemy en) {
        super(en);
        this.directions = new ArrayList<Direction>(Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        this.pixelToSeconds = (TILES_PER_SECONDS * SpriteSheet.SPRITE_SIZE_IN_GAME) * difficultyLvl;
        this.randomizer = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDifficultyLevel() {
        return this.difficultyLvl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementDifficultyLevel() {
        this.difficultyLvl += 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateCommand() {
        final int directionsNumber = this.directions.size();
        final Direction dir = this.directions.get(this.randomizer.nextInt(directionsNumber));
        super.createCommand(dir, pixelToSeconds);
    }

}
