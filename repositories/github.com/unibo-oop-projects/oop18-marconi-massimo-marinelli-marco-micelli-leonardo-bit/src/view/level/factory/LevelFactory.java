package view.level.factory;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.SpawnSymbol;
import com.almasb.fxgl.entity.TextEntityFactory;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import model.entities.GameEntityTypes;
import view.level.spawning.SpawnPointTypes;

/**
 * This is the basic factory for level spawning.
 */
public class LevelFactory implements EntityFactory, TextEntityFactory {

    private final String floorTexture;
    private final String wallTexture;
    private final DepthSupplier depthSupplier = new DepthSupplierImpl();

    /**
     * 
     * @param depth
     *                  the level depth.
     */
    public LevelFactory(final LevelDepth depth) {
        this.floorTexture = depthSupplier.getDepthMap().get(depth).getFloorTexture();
        this.wallTexture = depthSupplier.getDepthMap().get(depth).getWallTexture();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return floor
     */
    @SpawnSymbol('F')
    public Entity newFloor(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .build();
        };

    /**
     * @param data
     *            entity's spawning data
     * @return wall
     */
     @SpawnSymbol('W')
     public Entity newWall(final SpawnData data) {
        final PhysicsComponent muro = new PhysicsComponent();
        muro.setBodyType(BodyType.STATIC);

        return Entities.builder()
                       .from(data)
                       .viewFromTexture(wallTexture)
                       .with(muro)
                       .with(new CollidableComponent(true))
                       .bbox(new HitBox(BoundingShape.box(64, 64)))
                       .build();
        }

     /**
      * @param data
      *            entity's spawning data
      * @return door
      */
     @SpawnSymbol('d')
     public Entity newEntranceDoor(final SpawnData data) {
         final PhysicsComponent muro = new PhysicsComponent();
         muro.setBodyType(BodyType.STATIC);

         return Entities.builder()
                 .from(data)
                 .viewFromTexture("door.jpg")
                 .with(muro)
                 .with(new CollidableComponent(true))
                 .bbox(new HitBox(BoundingShape.box(64, 64)))
                 .build();
     }

    /**
     * @param data
     *            entity's spawning data
     * @return door
     */
    @SpawnSymbol('D')
    public Entity newExitDoor(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture("door.jpg")
                .with(new CollidableComponent(true))
                .bbox(new HitBox(BoundingShape.circle(16)))
                .type(GameEntityTypes.DOOR)
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return door
     */
    @SpawnSymbol('L')
    public Entity newEntryStair(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(GameEntityTypes.STAIRS_UP)
                .viewFromTexture("stone_stairs_up.png")
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return door
     */
    @SpawnSymbol('U')
    public Entity newExitStair(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(GameEntityTypes.STAIRS_DOWN)
                .viewFromTexture("stone_stairs_down.png")
                .bbox(new HitBox(BoundingShape.box(64, 64)))
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return fog
     */
    @SpawnSymbol('N')
    public Entity newFog(final SpawnData data) {
        return Entities.builder()
               .from(data)
               .viewFromTexture("nero.jpg")
               .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return playerSpawnPoint
     */
    @SpawnSymbol('K')
    public Entity newSpawnKnight(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.KNIGHT)
                .build();
        }

    /**
     * @param data
     *            entity's spawning data
     * @return playerSpawnPoint
     */
    @SpawnSymbol('A')
    public Entity newSpawnArcher(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.ARCHER)
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return playerSpawnPoint
     */
    @SpawnSymbol('T')
    public Entity newSpawnThief(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.THIEF)
                .build();
        }

    /**
     * @param data
     *            entity's spawning data
     * @return enemiesSpawnPoint
     */
    @SpawnSymbol('s')
    public Entity newSpawnSkeleton(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.SKELETON)
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return enemiesSpawnPoint
     */
    @SpawnSymbol('g')
    public Entity newSpawnGoblin(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.GOBLIN)
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return enemiesSpawnPoint
     */
    @SpawnSymbol('B')
    public Entity newSpawnBoss(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.BOSS)
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return itemSpawnPoint
     */
    @SpawnSymbol('r')
    public Entity newSpawnRPotion(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.RED_POTION)
                .build();
    }

    /**
     * @param data
     *            entity's spawning data
     * @return itemSpawnPoint
     */
    @SpawnSymbol('b')
    public Entity newSpawnBPotion(final SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTexture(floorTexture)
                .type(SpawnPointTypes.BLUE_POTION)
                .build();
    }
    
    /**
     * Getter for block height in pixel.
     */
    @Override
    public int blockHeight() {
        return 64;
    }

    /**
     * Getter for block width in pixel.
     */
    @Override
    public int blockWidth() {
        return 64;
    }

    /**
     * This overridden method is necessary to implement TextEntityFactory.
     */
    @Override
    public char emptyChar() {
        return 0;
    }

}
