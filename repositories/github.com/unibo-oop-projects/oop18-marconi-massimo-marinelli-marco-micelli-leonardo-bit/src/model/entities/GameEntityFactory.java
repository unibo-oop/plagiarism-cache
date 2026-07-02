package model.entities;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import model.entities.components.HealthComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import model.entities.components.PlayerController;
import model.entities.components.enemies.EnemyBehaviour;
import model.entities.components.enemies.EnemyController;
import model.entities.components.enemies.SensorComponent;
import model.entities.components.items.BluePotionEffect;
import model.entities.components.items.RedPotionEffect;
/**
 * This class is responsible for spawning every game entity in the game world.
 */
public class GameEntityFactory implements EntityFactory {

    /**
     * 
     * @param data
     *                          The GameEntity spawn data.
     * @param playerClass
     *                          The in-game player class.
     * @return The player.
     */
    public Entity newPlayer(final SpawnData data, final GameEntityClasses playerClass, final int attackRadius, final int damage) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        final PlayerController controller = new PlayerController(playerClass, physics, attackRadius, damage);

        final Integer playerHealth = Integer.valueOf(FXGL.getApp().getGameState().getProperties().get("PlayerHealth"));
        final HealthComponent health = new HealthComponent(playerHealth);

        return Entities.builder()
                       .from(data)
                       .type(GameEntityTypes.PLAYER)
                       .with(physics)
                       .with(controller)
                       .with(health)
                       .with(new CollidableComponent(true))
                       .bbox(new HitBox(BoundingShape.circle(16)))
                       .build();
    }

    /**
     * 
     * @param data
     *                      The GameEntity spawn data.
     * @return The player with a Knight texture.
     */
    @Spawns("PlayerKnight")
    public Entity newPlayerKnight(final SpawnData data) {
        return newPlayer(data, GameEntityClasses.KNIGHT, 60, 10);
    }

    /**
     * 
     * @param data
     *                      The GameEntity spawn data.
     * @return The player with an Archer texture.
     */
    @Spawns("PlayerArcher")
    public Entity newPlayerArcher(final SpawnData data) {
        return newPlayer(data, GameEntityClasses.ARCHER, 120, 5);
    }

    /**
     * 
     * @param data
     *                      The GameEntity spawn data.
     * @return The player with a Knight texture.
     */
    @Spawns("PlayerThief")
    public Entity newPlayerThief(final SpawnData data) {
        return newPlayer(data, GameEntityClasses.THIEF, 50, 15);
    }

    /**
     * 
     * @param data
     *                      The GameEntity spawn data.
     * @return The Enemy with a Skeleton texture.
     */
    @Spawns("EnemySkeleton")
    public Entity newEnemySkeleton(final SpawnData data) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        final EnemyController controller = new EnemyController(GameEntityClasses.SKELETON, EnemyBehaviour.LOOKING, 5);

        final HealthComponent health = new HealthComponent(500);

        return Entities.builder()
                       .from(data)
                       .type(GameEntityTypes.ENEMY)
                       .with(physics)
                       .with(controller)
                       .with(health)
                       .with(new SensorComponent(controller, 50))
                       .build();
    }

    /**
     * 
     * @param data
     *                      The GameEntity spawn data.
     * @return The Enemy with a Goblin texture.
     */
    @Spawns("EnemyGoblin")
    public Entity newEnemyGoblin(final SpawnData data) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        final EnemyController controller = new EnemyController(GameEntityClasses.GOBLIN, EnemyBehaviour.LOOKING, 1);

        final HealthComponent health = new HealthComponent(200);


        return Entities.builder()
                       .from(data)
                       .type(GameEntityTypes.ENEMY)
                       .with(physics)
                       .with(controller)
                       .with(health)
                       .with(new SensorComponent(controller, 100))
                       .build();
    }

    /**
     * 
     * @param data
     *                      The GameEntity spawn data.
     * @return The Enemy with a boss texture.
     */
    @Spawns("EnemyBoss")
    public Entity newEnemyBoss(final SpawnData data) {
        final PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        final EnemyController controller = new EnemyController(GameEntityClasses.BOSS, EnemyBehaviour.LOOKING, 6);

        final HealthComponent health = new HealthComponent(1000);


        return Entities.builder()
                       .from(data)
                       .type(GameEntityTypes.BOSS)
                       .with(physics)
                       .with(controller)
                       .with(health)
                       .with(new SensorComponent(controller, 100))
                       .build();
    }

    /**
     * 
     * @param data
     *               The GameEntity spawn data.
     * @return a Red Potion.
     */
    @Spawns("RedPotion")
    public Entity newRPotion(final SpawnData data) {
        return Entities.builder()
                       .from(data)
                       .type(GameEntityTypes.RED_POTION)
                       .viewFromTexture("RedPotion.png")
                       .with(new RedPotionEffect())
                       .with(new CollidableComponent(true))
                       .bbox(new HitBox(BoundingShape.circle(16)))
                       .build();
    }

    /**
     * 
     * @param data
     *               The GameEntity spawn data.
     * @return a Blue Potion.
     */
    @Spawns("BluePotion")
    public Entity newBPotion(final SpawnData data) {
        return Entities.builder()
                       .from(data)
                       .type(GameEntityTypes.BLUE_POTION)
                       .viewFromTexture("BluePotion.png")
                       .with(new BluePotionEffect())
                       .with(new CollidableComponent(true))
                       .bbox(new HitBox(BoundingShape.circle(16)))
                       .build();
    }

}
