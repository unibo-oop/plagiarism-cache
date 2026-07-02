package rogue.model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.util.Pair;
import rogue.model.Entity;
import rogue.model.creature.Combat;
import rogue.model.creature.Combat.Result;
import rogue.model.creature.CombatImpl;
import rogue.model.creature.Creature;
import rogue.model.creature.Monster;
import rogue.model.creature.Player;
import rogue.model.items.Item;
import rogue.model.items.inventory.InventoryIsFullException;

/**
 * the default level implementation.
 */
public class LevelImpl implements Level {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private static final int FOOD_DECREASE_ON_COMBAT = 2;

    private static final Logger LOG = LoggerFactory.getLogger(Level.class);
    private final Random random = new Random();
    private final Combat combat = new CombatImpl();
    private final Table<Integer, Integer, Tile> tileMap = HashBasedTable.create();
    private Player player;
    private final BiMap<Entity, Tile> entityMap = HashBiMap.create();

    // freeTiles cache
    private final List<Tile> freeTiles = new ArrayList<>();

    /**
     * @return a random tile from the freeTiles list
     */
    private final Supplier<Tile> getRandomFreeTile = () -> freeTiles.get(random.nextInt(freeTiles.size()));

    /**
     * @param t the tile
     * @return can place an entity in tile t?
     */
    private final Predicate<Tile> canPlaceEntity = t -> freeTiles.contains(t);

    /**
     * remove the entity and update the free tiles list.
     * 
     * @param e the entity to be removed
     */
    private final Consumer<Entity> removeEntity = e -> {
        freeTiles.add(entityMap.get(e));
        entityMap.remove(e);
    };

    /**
     * @param e the entity to be placed
     * @param t the tile for the entity to be placed in
     */
    private final BiConsumer<Entity, Tile> placeEntity = (e, t) -> {
        if (entityMap.containsKey(e)) {
            removeEntity.accept(e);
        }

        entityMap.put(e, t);
        freeTiles.remove(t);
    };

    /**
     * gets the tile next to an entity.
     * 
     * @param e the entity
     * @param d the direction used to determine the tile
     * @return the tile next to e
     */
    private final BiFunction<Entity, Direction, Tile> getRelativeTile = (e, d) -> {
        final Tile currentTile = entityMap.get(e);
        if (currentTile == null) {
            return null;
        }

        int nextX = currentTile.getX(), nextY = currentTile.getY();

        switch (d) {
            case NORTH:
                nextY--;
                break;
            case EAST:
                nextX++;
                break;
            case SOUTH:
                nextY++;
                break;
            case WEST:
                nextX--;
                break;
            default:
                break;
        }

        return tileMap.get(nextX, nextY);
    };

    /**
     * place an entity in a random tile.
     * 
     * @param e the entity to be spawned
     */
    private final Consumer<Entity> spawn = e -> placeEntity.accept(e, getRandomFreeTile.get());

    /**
     * generate the level map using {@link CaveGenerator}.
     */
    private final Runnable generate = () -> {
        final CaveGenerator cg = new CaveGeneratorImpl(WIDTH, HEIGHT);
        final boolean[][] cave = cg.getCave();

        // tileMap
        IntStream.range(0, WIDTH).forEach(x -> {
            IntStream.range(0, HEIGHT).forEach(y -> {
                final var isWall = cave[x][y];

                final var t = new TileImpl(x, y, isWall);

                // redundant but not slow
                tileMap.put(x, y, t);

                // cache free tiles
                if (!isWall) {
                    freeTiles.add(t);
                }
            });
        });

        // door to next level
        final var door = getRandomFreeTile.get();
        door.setDoor();
    };

    /**
     * @param e the entity
     * @return the best direction to reach the player
     */
    private final Function<Entity, Direction> nearestDirectionToPlayer = e -> {
        final Tile playerTile = entityMap.get(player);
        final Tile entityTile = entityMap.get(e);
        if (playerTile == null || entityTile == null) {
            return Direction.NONE;
        }

        final int east = playerTile.getX() - entityTile.getX();
        final int west = entityTile.getX() - playerTile.getX();
        final int south = playerTile.getY() - entityTile.getY();
        final int north = entityTile.getY() - playerTile.getY();

        final Pair<Direction, Integer> xDirection = east > 0 ? new Pair<>(Direction.EAST, east)
                : new Pair<>(Direction.WEST, west);
        final Pair<Direction, Integer> yDirection = south > 0 ? new Pair<>(Direction.SOUTH, south)
                : new Pair<>(Direction.NORTH, north);

        return xDirection.getValue() > yDirection.getValue() ? xDirection.getKey() : yDirection.getKey();
    };

    /**
     * moves the player.
     * 
     * @param d the player's movement direction
     */
    private Predicate<Direction> movePlayer = d -> {
        final var nextTile = getRelativeTile.apply(player, d);
        if (nextTile == null) {
            return false;
        }
        final var nextEntity = entityMap.inverse().get(nextTile);

        if (nextTile.isDoor()) {
            return true;
        } else if (canPlaceEntity.test(nextTile)) {
            placeEntity.accept(player, nextTile);
        } else if (nextEntity instanceof Creature) {
            if (combat.attack(player, (Creature<?>) nextEntity) == Result.DEAD) {
                removeEntity.accept(nextEntity);
            }

            // combat is exausting
            player.getLife().decreaseFood(FOOD_DECREASE_ON_COMBAT);
        } else if (nextEntity instanceof Item) {
            try {
                player.getInventory().addItem((Item) nextEntity);
                removeEntity.accept(nextEntity);
                placeEntity.accept(player, nextTile);
            } catch (InventoryIsFullException e1) {
                LOG.info("Inventory full!");
            }
        }

        return false;
    };

    /**
     * moves a monster.
     * 
     * @param e the monster
     */
    private Consumer<Entity> moveMonster = e -> {
        final Monster m = (Monster) e;
        final var nextTile = getRelativeTile.apply(m, m.monsterMove(nearestDirectionToPlayer.apply(m)));
        if (nextTile == null) {
            return;
        }
        final var nextEntity = entityMap.inverse().get(nextTile);

        if (canPlaceEntity.test(nextTile)) {
            placeEntity.accept(e, nextTile);
        } else if (nextEntity instanceof Player) {
            combat.attack((Creature<?>) e, (Player) nextEntity);
        } else if (nextEntity instanceof Item) {
            // crush item
            removeEntity.accept(nextEntity);
        }
    };

    public final int getWidth() {
        return WIDTH;
    }

    public final int getHeight() {
        return HEIGHT;
    }

    public final Stream<Tile> getTileStream() {
        return tileMap.values().stream();
    }

    public final Map<Entity, Tile> getEntityMap() {
        return entityMap;
    }

    public final void spawnEntity(final Entity e) {
        spawn.accept(e);
    }

    public final void spawnEntities(final List<Entity> l) {
        l.forEach(spawn);
    }

    public final boolean moveEntities(final Direction d) {
        final var monsters = entityMap.keySet().stream().filter(e -> e instanceof Monster).collect(Collectors.toList());
        monsters.forEach(moveMonster);

        // if player is entering a door
        var nextLevel = movePlayer.test(d);

        // if player is killed
        if (player.getLife().isDead()) {
            entityMap.clear();
            LOG.info("Player dead");
        }
        return nextLevel;
    }

    /**
     * Create a new level.
     * 
     * @param list   the entity list (player included)
     * @param player the player instance
     */
    public LevelImpl(final List<Entity> list, final Player player) {
        this.player = player;
        generate.run();
        spawnEntities(list);
    }
}
