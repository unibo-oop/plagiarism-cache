package zombietsunami;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import zombietsunami.model.obstaclemodel.api.Bomb;
import zombietsunami.model.obstaclemodel.api.Breakable;
import zombietsunami.model.obstaclemodel.api.ObstacleManager;
import zombietsunami.model.obstaclemodel.impl.BombImpl;
import zombietsunami.model.obstaclemodel.impl.BreakableImpl;
import zombietsunami.model.obstaclemodel.impl.ObstacleManagerImpl;
import zombietsunami.model.personmodel.api.Person;
import zombietsunami.model.personmodel.api.PersonsManager;
import zombietsunami.model.personmodel.impl.PersonImpl;
import zombietsunami.model.personmodel.impl.PersonsManagerImpl;
import zombietsunami.model.zombiemodel.impl.ZombieImpl;
import zombietsunami.model.CollisionImpl;
import zombietsunami.model.MapData;
import zombietsunami.model.ModelImpl;
import zombietsunami.model.mapmodel.api.GameMap;
import zombietsunami.model.mapmodel.impl.GameMapImpl;

// CHECKSTYLE: MagicNumber OFF
/**
 * Test class for CollisionImpl.
 * 
 * This class contains unit tests for various functionalities of the
 * CollisionImpl
 * class.
 */
class TestCollision {

    private final ModelImpl model = new ModelImpl();
    private final ZombieImpl zombie = new ZombieImpl();
    private final Bomb bomb = new BombImpl();
    private final Breakable breakable = new BreakableImpl(1);
    private final ObstacleManager obstacleManager = new ObstacleManagerImpl();
    private final Person person = new PersonImpl();
    private final PersonsManager personsManager = new PersonsManagerImpl();
    private final CollisionImpl collision = new CollisionImpl();
    private final GameMap gameMap = new GameMapImpl();

    /**
     * This function generates two obstacles and a Person, namely a bomb and
     * a breakable object. The screen coordinates of both obstacles and Person
     * are intentionally set to match those of the zombie.
     * Subsequently, these two obstacles and Person are added to their respective
     * lists, and the collision method is invoked to determine whether
     * a collision between the zombie and the two obstacles occurs and Person.
     * In the event of a collision, the two obstacles and Person are
     * expected to be marked as null.
     */
    @Test
    void checkIfCollides() {
        bomb.setX(75);
        bomb.setY(288);
        bomb.setDamage(1);

        breakable.setX(75);
        breakable.setY(288);

        person.setX(75);
        person.setY(288);

        obstacleManager.getBreakableList().add(0, breakable);
        obstacleManager.getBombList().add(0, bomb);
        personsManager.getPersonList().add(0, person);
        zombie.increaseStrength();
        zombie.increaseStrength();
        zombie.increaseStrength();
        zombie.increaseStrength();
        zombie.increaseStrength();
        collision.collisionZombieObstacle(obstacleManager.getBombList(), obstacleManager.getBreakableList(),
                MapData.getTitSize(), zombie, gameMap);
        collision.collisionZombiePersons(personsManager.getPersonList(),
                MapData.getTitSize(), zombie, gameMap);
        assertNull(obstacleManager.getBombList().get(0));
        assertNull(obstacleManager.getBreakableList().get(0));
        assertNull(personsManager.getPersonList().get(0));
    }

    /**
     * This function decreases the strength of the zombies
     * to 0, and upon collision with an obstacle, it is
     * intended to transition the game to a game-over state.
     */
    @Test
    void checkIfGameover() {
        zombie.decreaseStrength();

        bomb.setX(75);
        bomb.setY(288);
        bomb.setDamage(1);

        breakable.setX(75);
        breakable.setY(288);

        obstacleManager.getBreakableList().add(0, breakable);
        obstacleManager.getBombList().add(0, bomb);

        collision.collisionZombieObstacle(obstacleManager.getBombList(), obstacleManager.getBreakableList(),
                MapData.getTitSize(), zombie, gameMap);

        assertTrue(collision.isGameOver());
    }

    /**
     * Assures that the model functions behave correctly
     * compared to the Collision interface.
     */
    @Test
    void testCollisionWithModel() {
        assertEquals(collision.isGameOver(), model.isGameOver());
    }
}
