package zombieversity.model.world;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.geometry.Point2D;
import zombieversity.model.entities.passive.Obstacle;
import zombieversity.model.entities.passive.PassiveEntity;
import zombieversity.model.entities.passive.PlayerSpawn;
import zombieversity.model.entities.passive.ZombieSpawn;
import zombieversity.view.world.graphics.TileType;

/**
 * Implementation of the World concept.
 *
 */
public class WorldImpl implements World {

    private final double tileSize;
    private final double width;
    private final double height;
    private final Map<Point2D, Integer> blocks;
    private List<PassiveEntity> objs;
    private double scale = 1.0;
    private List<PassiveEntity> zSpawn;
    private Optional<PassiveEntity> pSpawn;

    /**
     * Constructor, requires the blocks that compose the world, the size of the
     * world, and the size of a single block.
     * 
     * @param b  - blocks collection.
     * @param w  - width of the map.
     * @param h  - height of the map.
     * @param ts - size of each block.
     */
    public WorldImpl(final Map<Point2D, Integer> b, final double w, final double h, final double ts) {
        this.tileSize = ts;
        this.width = w;
        this.height = h;
        this.blocks = b;
    }

    /**
     * Internal method used to transform approximated blocks in it's corresponding
     * model object.
     * 
     * @return List<PassiveEntity>
     */
    private List<PassiveEntity> loadObj() {
        final List<PassiveEntity> result = new LinkedList<>();
        blocks.forEach((p, id) -> {
            final int block = id;
            if (block == TileType.PLAYER_SPAWN.getId()) {
                this.pSpawn = Optional
                        .of(new PlayerSpawn(p.multiply(tileSize * scale), tileSize * scale, tileSize * scale));
            } else if (block == TileType.ZOMBIE_SPAWN.getId()) {
                if (this.zSpawn == null) {
                    this.zSpawn = new LinkedList<>();
                }
                this.zSpawn.add(new ZombieSpawn(p.multiply(tileSize * scale), tileSize * scale, tileSize * scale));
            } else if (block == TileType.BOX.getId() || block == TileType.WALL.getId()) {
                result.add(new Obstacle(p.multiply(tileSize * scale), tileSize * scale, tileSize * scale));
            }
        });
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setScale(final double scale) {
        this.scale = scale;
        this.objs = loadObj();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<Point2D, Integer> getBlocks() {
        return this.blocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<? extends PassiveEntity> getObstacles() {
        return this.objs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<? extends PassiveEntity> getZombieSpawnPoints() {
//            if (zSpawn == null) {
//                zSpawn = this.objs.stream().filter(t -> t instanceof ZombieSpawn).collect(Collectors.toList());
//            }
        return zSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<? extends PassiveEntity> getPlayerSpawnPoint() {
        if (this.pSpawn == null) {
            for (final PassiveEntity e : this.objs) {
                if (e instanceof PlayerSpawn) {
                    pSpawn = Optional.of(e);
                    break;
                }
            }
        }
        return pSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getWidth() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getHeight() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getTileSize() {
        return this.tileSize;
    }
}
