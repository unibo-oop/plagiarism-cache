package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.BASIC_ENEMY;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.oop.exceptions.CollisionHandlingException;
import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;;

/**
 * Basic monster that implements {@link AbstractEnemy} that follows the
 * {@link MainCharacter} and tries to catch him.
 */
public class BasicMonster extends AbstractEnemy {

    private static final int SCORE_VALUE = 10;
    private final static int DMG = 1;

    /**
     * Constructors that creates a {@link BasicMonster} that is moving with a
     * movementVector form a starting {@link Position}
     * 
     * @param startingX
     *            The X position where the Monster is created
     * @param startingY
     *            The Y position where the Monster is created
     * @param movementVector
     *            The initial movement of the monster
     */
    public BasicMonster(final double startingX, final double startingY, final Vector2 movementVector) {
        super(startingX, startingY, movementVector, BASIC_ENEMY.getSpeed());
        this.attachBehavior(new BasicEnemyBehavior(this));
    }

    /**
     * Method that check if the monster collides with {@link Wall},
     * {@link Enemy}
     * @param newPosition The next position where the function has to check collisions
     */
    public void checkCollision(final Position newPosition) throws CollisionHandlingException {
        final BasicMonster tmpEnemy = Factory.EnemiesFactory.generateStillBasicEnemy(newPosition.getIntX(),
                newPosition.getIntY());
        // Checks if the entity in the next move is inside the rectanuglar Arena
        if (!this.getEnvironment().getArena().isInside(tmpEnemy)) {
            throw new CollisionHandlingException("Next movement not inside the arena");
        }
        final long numWallCollisions = this.getEnvironment().getStableList().stream().filter(x -> x instanceof Wall)
                .filter(tmpEnemy::intersecate).count();

        if (numWallCollisions > 0) {
            throw new CollisionHandlingException("Next movement collides a wall");
        }
        final List<AbstractEnemy> enemyCollisions = this.getEnvironment().getMovableList().stream()
                .filter(x -> x instanceof AbstractEnemy).filter(tmpEnemy::intersecate).map(x -> (AbstractEnemy) x)
                .collect(Collectors.toList());
        // If the monster collides with other enemies except himself (1)
        if (enemyCollisions.size() > 1) {
            throw new CollisionHandlingException();
        }

    }

    /**
     * The {@link BasicMonster} Height
     * @return Entity height
     */
    protected int getEntityHeight() {
        return BASIC_ENEMY.getHeight();
    }

    /**
     * The {@link BasicMonster} Width
     * @return Entity width
     */
    protected int getEntityWidth() {
        return BASIC_ENEMY.getWidth();
    }

    /**
     * The score you gain killing the {@link BasicMonster}
     */
    public int getScoreValue() {
        return BasicMonster.SCORE_VALUE;
    }

    /**
     * The damage that the {@link BasicMonster} deals.
     */
    public int getDamage() {
        return BasicMonster.DMG;
    }
}