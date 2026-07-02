package it.unibo.pacman.model.entities;

import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
import it.unibo.pacman.model.utilities.Status;
/**
 * An implementation of {@link EntityFactory}.
 */
public class EntityFactoryImpl implements EntityFactory {
    private static final int PILLWIDTH = 26;
    private static final int PILLHEIGHT = 26;
    private static final int WALLWIDTH = 30;
    private static final int WALLHEIGHT = 30;
    private static final int GHOSTWIDTH = 29;
    private static final int GHOSTHEIGHT = 29;
    private static final int GHOSTSPEED = 2;
    private static final int GHOSTSPEEDHARD = 3;
    private static final int PACMANWIDTH = 30;
    private static final int PACMANHEIGHT = 30;
    private static final int PACMANSPEED = 2;
    private static final int PACMANLIVES = 3;
    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPill(final Position position) {
        return new SimpleGameObj(PILLWIDTH, PILLHEIGHT, position, EntityType.PILL);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPowerPill(final Position position) {
        return new SimpleGameObj(PILLWIDTH, PILLHEIGHT, position, EntityType.POWERPILL);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWall(final Position position) {
        return new SimpleGameObj(WALLWIDTH, WALLHEIGHT, position, EntityType.WALL);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Mortal createPacMan(final Position position) {
        return new MortalGameObj(PACMANWIDTH, PACMANHEIGHT, position, EntityType.PACMAN, Direction.LEFT, Status.CHASED,
                PACMANSPEED, PACMANLIVES);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createInky(final Position position, final Difficulty difficuty) {
        if (difficuty.equals(Difficulty.HARD)) {
            return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.INKY, Direction.UP, Status.CHASING,
                    GHOSTSPEEDHARD);
        } else {
        return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.INKY, Direction.UP, Status.CHASING,
                GHOSTSPEED);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createBlinky(final Position position, final Difficulty difficuty) {
        if (difficuty.equals(Difficulty.HARD)) {
            return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.BLINKY, Direction.UP, Status.CHASING,
                    GHOSTSPEEDHARD);
        } else {
        return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.BLINKY, Direction.UP, Status.CHASING,
                GHOSTSPEED);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createPinky(final Position position, final Difficulty difficuty) {
        if (difficuty.equals(Difficulty.HARD)) {
            return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.PINKY, Direction.UP, Status.CHASING,
                    GHOSTSPEEDHARD);
        } else {
        return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.PINKY, Direction.UP, Status.CHASING,
                GHOSTSPEED);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Movable createClyde(final Position position, final Difficulty difficuty) {
        if (difficuty.equals(Difficulty.HARD)) {
          return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.CLYDE, Direction.UP, Status.CHASING,
                    GHOSTSPEEDHARD);
        } else {
        return new MovableGameObj(GHOSTWIDTH, GHOSTHEIGHT, position, EntityType.CLYDE, Direction.UP, Status.CHASING,
                GHOSTSPEED);
        }
    }
}
