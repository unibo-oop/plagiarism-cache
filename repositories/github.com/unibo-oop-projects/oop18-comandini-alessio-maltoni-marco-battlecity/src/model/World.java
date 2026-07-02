package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.common.Position;
import model.common.PositionImpl;
import model.entities.Block;
import model.entities.BlockImpl;
import model.entities.Bullet;
import model.entities.GameEntity;
import model.entities.Tank;
import model.entities.tankcomponents.ShooterComponent;
import enums.Sprite;

/**
 * The world of the game. It contains all entities, boundaries and phisics
 * mechanism.
 *
 */
public class World {

    private static final double HEIGHT_BORDER = 26;
    private static final double WEIGHT_BORDER = 26;
    private static final Position DEF_BASE_POSITION = new PositionImpl(12.0, 24.0);
    private static final Position ENEMY_FIRST_SPAWN = new PositionImpl(0.0, 0.0);
    private static final Position ENEMY_SECOND_SPAWN = new PositionImpl(12.1, 0.0);
    private static final Position ENEMY_THIRD_SPAWN = new PositionImpl(24.3, 0.0);

    private List<Tank> playersTank;
    private List<Block> blocks;
    private final List<Tank> enemyTank;
    private final List<Bullet> bullets;
    private final Block base;
    private final Iterator<Position> enemyPositions;
    private final List<Event> events;
    private final BulletEngine bulletEngine;

    /**
     * Default constructor.
     */
    public World() {
        this.base = new BlockImpl(Sprite.BASE, DEF_BASE_POSITION, Block.Type.BASE);
        this.events = new LinkedList<>();
        this.playersTank = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.enemyTank = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.enemyPositions = getEnemySpawnIterator();
        this.bulletEngine = new BulletEngine(this);
    }

    /**
     * 
     * @param map         a list of blocks that define the map in the world
     * @param playersTank a list of tank controlled by the players
     */
    public void setup(final List<Tank> playersTank, final List<Block> map) {
        playersTank.forEach(t -> t.attach(new ShooterComponent(bulletEngine, t)));
        this.playersTank = playersTank;
        this.blocks = map;
    }

    /**
     * 
     * @return a list of game entities for rendering and update purpose
     */
    public List<GameEntity> getWorldEntity() {
        return Stream
                .concat(Stream.concat(Stream.concat(playersTank.stream(), bullets.stream()),
                        Stream.concat(enemyTank.stream(), blocks.stream())), Stream.of(base))
                .collect(Collectors.toList());
    }

    /**
     * Update the current state of all the game object in the game.
     */
    public void updateState() {
        this.getWorldEntity().stream().forEach(GameEntity::updateState);
    }

    /**
     * 
     * @return the enemy now in the world
     */
    public List<Tank> getEnemy() {
        return this.enemyTank;
    }

    /**
     * As a new enemy in the world.
     * 
     * @param enemyTank is a new enemy to add in the world.
     */
    public void addEnemy(final Tank enemyTank) {
        enemyTank.setPosition(enemyPositions.next());
        enemyTank.attach(new ShooterComponent(bulletEngine, enemyTank));
        this.enemyTank.add(enemyTank);

    }

    /**
     * Notify word of a given event.
     * 
     * @param event an event to handel by the world
     */
    public void notifyEvent(final Event event) {
        this.events.add(event);
    }

    /**
     * Process every event in the queue.
     */
    public void processEvents() {
        this.events.forEach(e -> {
        });// TODO implements working code
        events.clear();
    }

    /**
     * method that return a iterator of default enemy positions.
     * 
     * @return a default enemy position to spawn enemy.
     */
    private Iterator<Position> getEnemySpawnIterator() {
        return new Iterator<Position>() {
            private List<Position> enemySpawn = Stream.of(ENEMY_FIRST_SPAWN, ENEMY_SECOND_SPAWN, ENEMY_THIRD_SPAWN)
                    .collect(Collectors.toList());
            private Iterator<Position> iterator = enemySpawn.iterator();

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Position next() {
                if (!iterator.hasNext()) {
                    iterator = enemySpawn.iterator();
                }
                return iterator.next();
            }
        };
    }

    public final void addBullet(final Bullet bullet) {
        this.bullets.add(bullet);
    }

}
