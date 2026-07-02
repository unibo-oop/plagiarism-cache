package it.unibo.scat.model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.Constants;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.model.api.EntityFactory;
import it.unibo.scat.model.game.entity.AbstractEntity;
import it.unibo.scat.model.game.entity.Invader;
import it.unibo.scat.model.game.entity.Player;
import it.unibo.scat.model.game.entity.Shot;

/**
 * Class that represents the game world and holds the game's state.
 */
@SuppressFBWarnings(value = "DMI_RANDOM_USED_ONLY_ONCE", justification = "Random instance used once intentionally.")
public class GameWorld {
    private static final String EI_EXPOSE_REP = "EI_EXPOSE_REP";
    private static final String EXPOSE_REP_JUSTIFICATION = "Intentional exposure of internal collections inside the model layer";
    private final EntityFactory entityFactory;
    private final List<AbstractEntity> entities = Collections.synchronizedList(new ArrayList<>());
    private final List<Invader> invaders = Collections.synchronizedList(new ArrayList<>());
    private final List<Shot> shots = Collections.synchronizedList(new ArrayList<>());
    private Player player;
    private Invader bonusInvader;

    /**
     * GameWorld constructor.
     * 
     * @param entityFactory the factory of entities.
     */
    public GameWorld(final EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
        player = null;
        bonusInvader = null;
    }

    /**
     * Initializes the game entities by loading them from a file.
     * 
     * @param filename the file containing the entities configuration.
     */
    public void initEntities(final String filename) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(
                                getClass().getClassLoader().getResourceAsStream(filename)),
                        StandardCharsets.UTF_8))) {

            String line;
            int x;
            int y;
            EntityType type;
            AbstractEntity newEntity;

            line = reader.readLine();
            while (line != null) {
                final String[] field = line.trim().split(";");

                type = EntityType.valueOf(field[0]);
                x = Integer.parseInt(field[1]);
                y = Integer.parseInt(field[2]);

                newEntity = entityFactory.createEntity(type, x, y);
                addEntity(newEntity);

                line = reader.readLine();
            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load entities from file: " + filename + "Exception: ", e);
        }
    }

    /**
     * Entities getter.
     * 
     * @return the list of entities.
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = EXPOSE_REP_JUSTIFICATION)
    public List<AbstractEntity> getEntities() {
        return entities;
    }

    /**
     * Shots getter.
     * 
     * @return the list of shots.
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = EXPOSE_REP_JUSTIFICATION)
    public List<Shot> getShots() {
        return shots;
    }

    /**
     * Invaders getter.
     * 
     * @return the list of invaders.
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = EXPOSE_REP_JUSTIFICATION)
    public List<Invader> getInvaders() {
        return invaders;
    }

    /**
     * @return the player entity.
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = EXPOSE_REP_JUSTIFICATION)
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Adds an entity to the game world and the appropriate internal list.
     * 
     * @param e the entity to add.
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = EXPOSE_REP_JUSTIFICATION)
    public void addEntity(final AbstractEntity e) {
        entities.add(e);

        if (e instanceof Invader) {
            if (e.getType() == EntityType.BONUS_INVADER) {

                bonusInvader = (Invader) e;
                return;
            } else {
                invaders.add((Invader) e);
                return;
            }
        }

        if (e instanceof Player) {
            player = (Player) e;
        }

        if (e instanceof Shot) {
            shots.add((Shot) e);
        }
    }

    /**
     * Removes an entity from the game world and from the appropriate internal list.
     * 
     * @param e the entity to remove.
     */
    public void removeEntity(final AbstractEntity e) {
        entities.remove(e);

        if (e instanceof Invader) {
            if (e.getType() == EntityType.BONUS_INVADER) {
                bonusInvader = null;
            } else {
                invaders.remove((Invader) e);
            }
        }

        if (e instanceof Shot) {
            shots.remove((Shot) e);
        }
    }

    /**
     * Changes the movement direction of the invaders.
     */
    public void changeInvadersDirection() {

        for (final Invader x : invaders) {
            if (x.getCurrDirection() == Direction.DOWN) {
                x.setCurrDirection(x.getNextDirection());
                x.setNextDirection(Direction.DOWN);
            } else {
                x.setNextDirection(x.getCurrDirection() == Direction.LEFT ? Direction.RIGHT : Direction.LEFT);
                x.setCurrDirection(Direction.DOWN);
            }
        }
    }

    /**
     * Checks if the invaders need to change direction.
     * 
     * @return true if the direction should change, false otherwise.
     */
    public boolean shouldInvadersChangeDirection() {

        for (final Invader x : invaders) {
            if (x.getCurrDirection() == Direction.DOWN && x.isAlive()) {
                return true;
            }
        }

        final boolean hitRight = didInvadersHitRight();
        final boolean hitLeft = didInvadersHitLeft();

        return hitLeft || hitRight;
    }

    /**
     * Checks if any invader has reached the right border.
     * 
     * @return true if an invader hit the right border.
     */
    private boolean didInvadersHitRight() {
        for (final Invader invader : invaders) {
            if ((invader.getPosition().getX() + invader.getWidth()) >= Constants.BORDER_RIGHT && invader.isAlive()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if any invader has reached the left border.
     * 
     * @return true if an invader hit the left border.
     */
    private boolean didInvadersHitLeft() {
        for (final Invader invader : invaders) {
            if (invader.getPosition().getX() <= Constants.BORDER_LEFT && invader.isAlive()) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return returns the bonusInvader, it could also be null!
     */
    @SuppressFBWarnings(value = EI_EXPOSE_REP, justification = EXPOSE_REP_JUSTIFICATION)
    public Invader getBonusInvader() {
        return bonusInvader;
    }

    /**
     * @return returns true if the bonusInvader is alive (not null).
     *         Because when the bonusInvader is not alive it is setted to null.
     * 
     */
    public boolean isBonusInvaderAlive() {
        return bonusInvader != null;
    }

    /**
     * .Creates and adds a bonus invader at a random side of the game world.
     */
    public void spawnBonusInvader() {
        final int leftPos = -1;
        final int rightPos = Constants.BORDER_RIGHT - Constants.BONUS_INVADER_WIDTH;

        final boolean left = new java.util.Random().nextBoolean();
        final Direction direction = left ? Direction.RIGHT : Direction.LEFT;
        final int x = left ? leftPos : rightPos;
        final int y = 1;

        final Invader invader = new Invader(EntityType.BONUS_INVADER, x, y, Constants.BONUS_INVADER_WIDTH,
                Constants.BONUS_INVADER_HEIGHT, 1);
        invader.setCurrDirection(direction);
        invader.setNextDirection(direction);

        addEntity(invader);
    }
}
