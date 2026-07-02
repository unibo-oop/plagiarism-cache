package it.unibo.bmbman.controller.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import it.unibo.bmbman.model.Level;
import it.unibo.bmbman.model.Terrain;
import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.entities.Monster;
import it.unibo.bmbman.model.entities.powerups.BonusBombNum;
import it.unibo.bmbman.model.entities.powerups.BonusBombRange;
import it.unibo.bmbman.model.entities.powerups.BonusLife;
import it.unibo.bmbman.model.entities.powerups.BonusVelocity;
import it.unibo.bmbman.model.entities.powerups.Door;
import it.unibo.bmbman.model.entities.powerups.Key;
import it.unibo.bmbman.model.entities.powerups.MalusFreeze;
import it.unibo.bmbman.model.entities.powerups.MalusInvert;
import it.unibo.bmbman.model.entities.powerups.MalusLife;
import it.unibo.bmbman.model.entities.powerups.MalusSlow;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.model.utilities.PowerUpType;
import it.unibo.bmbman.view.entities.BlockView;
import it.unibo.bmbman.view.entities.EntityView;
import it.unibo.bmbman.view.entities.HeroView;
import it.unibo.bmbman.view.entities.MonsterView;
import it.unibo.bmbman.view.entities.PowerUpView;
import it.unibo.bmbman.view.entities.TileView;
import it.unibo.bmbman.view.entities.WallView;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;

/**
 * Used to load a level.
 */
public class LoadWorld {
    private final GameController gc;
    private final Terrain terrain;
    private final Level level;
    /**
     * Construct the level.
     * @param gc {@link GameController}
     */
    public LoadWorld(final GameController gc) {
        this.gc = gc;
        this.level = gc.getLevel();
        this.terrain =  new TerrainFactoryImpl().create(level.getBlocksNumber());
    }
    /**
     * Loads all the entity.
     */
    public void loadEntity() {
        final List<Entity> mosterList = new ArrayList<>();
        for (int i = 0; i < TerrainFactoryImpl.TERRAIN_COLUMNS; i++) {
            for (int j = 0; j < TerrainFactoryImpl.TERRAIN_ROWS; j++) {
                this.gc.addEntity(terrain.getEntity(i, j), this.getEntityView(terrain.getEntity(i, j)));
            }
        }
        terrain.getBlocks().stream().forEach((i) -> gc.addEntity(i, this.getEntityView(i)));
        this.gc.addEntity(new HeroImpl(), new  HeroView(TerrainFactoryImpl.PLAYER_POSITION));
        loadPowerUp();
        IntStream.iterate(0, i -> i + 1).limit(level.getMonstersNumber()).forEach(i -> mosterList.add(new Monster(terrain.getFreeRandomPosition())));
        mosterList.forEach(i -> gc.addEntity(i, new MonsterView(i.getPosition())));

    }
    /**
     * Used to associate model object to the relative view.
     * @param entity the entity that you what know the sprite 
     * @return the sprite of the entity in input
     */
    private EntityView getEntityView(final Entity entity) {
        switch (entity.getType()) {
        case TILE:
            return new TileView(entity.getPosition());
        case WALL:
            return new WallView(entity.getPosition());
        case BLOCK:
            return new BlockView(entity.getPosition());
        default:
            return new TileView(entity.getPosition());
        }
    }
    private void loadPowerUp() {
        this.gc.addEntity(new Door(), new PowerUpView(new Position(TerrainFactoryImpl.DOOR_POSITION.getX() / ScreenToolUtils.SCALE,
                TerrainFactoryImpl.DOOR_POSITION.getY() / ScreenToolUtils.SCALE), PowerUpType.DOOR.toString()));
        Position position = terrain.getRandomBlockPosition();
        this.gc.addEntity(new Key(position), new PowerUpView(position, PowerUpType.KEY.toString()));
        for (int i = 0; i < level.getBonusLifeNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new BonusLife(position), new PowerUpView(position, PowerUpType.BONUS_LIFE.toString()));
        }
        for (int i = 0; i < level.getBonusBombNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new BonusBombNum(position), new PowerUpView(position, PowerUpType.BONUS_BOMB.toString()));
        }
        for (int i = 0; i < level.getBonusRangeNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new BonusBombRange(position), new PowerUpView(position, PowerUpType.BONUS_RANGE.toString()));
        }
        for (int i = 0; i < level.getBonusVelocityNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new BonusVelocity(position), new PowerUpView(position, PowerUpType.BONUS_SPEED.toString()));
        }
        for (int i = 0; i < level.getMalusFreezeNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new MalusFreeze(position), new PowerUpView(position, PowerUpType.MALUS_FREEZE.toString()));
        }
        for (int i = 0; i < level.getMalusLifeNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new MalusLife(position), new PowerUpView(position, PowerUpType.MALUS_LIFE.toString()));
        }
        for (int i = 0; i < level.getMalusSlowNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new MalusSlow(position), new PowerUpView(position, PowerUpType.MALUS_SLOW.toString()));
        }
        for (int i = 0; i < level.getMalusInvertNumber(); i++) {
            position = terrain.getRandomBlockPosition();
            this.gc.addEntity(new MalusInvert(position), new PowerUpView(position, PowerUpType.MALUS_INVERT.toString()));
        }
    }
}
