package test.matteini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.factories.AbstractFactoryGameObject;
import spacesurvival.model.gameobject.factories.ConcreteFactoryGameObject;
import spacesurvival.model.gameobject.fireable.Boss;
import spacesurvival.model.gameobject.fireable.FireEnemy;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.fireable.shootinglogic.implementation.BossFiringLogic;
import spacesurvival.model.gameobject.fireable.shootinglogic.implementation.EnemyFiringLogic;
import spacesurvival.model.gameobject.main.Asteroid;
import spacesurvival.model.gameobject.main.ChaseEnemy;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.moveable.movement.implementation.ChasingMovementLogic;
import spacesurvival.model.gameobject.moveable.movement.implementation.FixedMovementLogic;
import spacesurvival.model.gameobject.moveable.movement.implementation.RandomMovementLogic;
import spacesurvival.utilities.gameobject.DamageUtils;
import spacesurvival.utilities.gameobject.LifeUtils;
import spacesurvival.utilities.gameobject.VelocityUtils;

public class TestFactory {

    private final AbstractFactoryGameObject factory = new ConcreteFactoryGameObject();
    private static final int OBJECTS_NUMBER = 20;

    @Test
    public void testCreations() {
        final Set<GameObject> gameObjects = new HashSet<>();
        for (int i = 0; i < OBJECTS_NUMBER; i++) {
            gameObjects.add(factory.createAsteroid());
        }
        assertEquals(gameObjects.size(), OBJECTS_NUMBER);
    }

    @Test
    public void testAsteroid() {
        final MainObject asteroid = factory.createAsteroid();
        assertEquals(asteroid.getClass(), Asteroid.class);
        assertEquals(asteroid.getLife(), LifeUtils.ASTEROID_LIFE);
        assertEquals(asteroid.getImpactDamage(), DamageUtils.ASTEROID_DAMAGE);
        assertEquals(asteroid.getVelocity(), VelocityUtils.ASTEROID_VEL);
        assertEquals(asteroid.getBoundingBox().getClass(), CircleBoundingBox.class);
        assertEquals(asteroid.getMovement().getClass(), FixedMovementLogic.class);
        assertNull(asteroid.getTargetPosition());
    }

    @Test
    public void testChaseEnemy() {
        final MainObject chaseEnemy = factory.createChaseEnemy();
        assertEquals(chaseEnemy.getClass(), ChaseEnemy.class);
        assertEquals(chaseEnemy.getLife(), LifeUtils.CHASE_ENEMY_LIFE);
        assertEquals(chaseEnemy.getImpactDamage(), DamageUtils.CHASE_ENEMY_DAMAGE);
        assertEquals(chaseEnemy.getVelocity(), VelocityUtils.CHASE_ENEMY_VEL);
        assertEquals(chaseEnemy.getBoundingBox().getClass(), RectBoundingBox.class);
        assertEquals(chaseEnemy.getMovement().getClass(), ChasingMovementLogic.class);
        assertNull(chaseEnemy.getTargetPosition());
    }

    @Test
    public void testFireEnemy() {
        final FireableObject fireEnemy = factory.createFireEnemy();
        assertEquals(fireEnemy.getClass(), FireEnemy.class);
        assertEquals(fireEnemy.getLife(), LifeUtils.FIRE_ENEMY_LIFE);
        assertEquals(fireEnemy.getImpactDamage(), DamageUtils.FIRE_ENEMY_DAMAGE);
        assertEquals(fireEnemy.getVelocity(), VelocityUtils.FIRE_ENEMY_VEL);
        assertEquals(fireEnemy.getBoundingBox().getClass(), RectBoundingBox.class);
        assertEquals(fireEnemy.getMovement().getClass(), RandomMovementLogic.class);
        assertEquals(fireEnemy.getFiringLogic().getClass(), EnemyFiringLogic.class);
        assertNull(fireEnemy.getTargetPosition());
    }

    @Test
    public void testBoss() {
        final FireableObject boss = factory.createBoss();
        assertEquals(boss.getClass(), Boss.class);
        assertEquals(boss.getLife(), LifeUtils.BOSS_LIFE);
        assertEquals(boss.getImpactDamage(), DamageUtils.BOSS_DAMAGE);
        assertEquals(boss.getVelocity(), VelocityUtils.BOSS_VEL);
        assertEquals(boss.getBoundingBox().getClass(), RectBoundingBox.class);
        assertEquals(boss.getMovement().getClass(), RandomMovementLogic.class);
        assertEquals(boss.getFiringLogic().getClass(), BossFiringLogic.class);
        assertNull(boss.getTargetPosition());
    }

}
