package model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

import game.engine.GameObjectFactory;
import game.engine.GameObjectFactoryImpl;
import game.theme.Theme;
import graphics.Sprite;
import graphics.SpriteSheet;
import utilities.Position;

/**
 * This class manage the whole game world.
 */
public class WorldImpl implements World {

    private static final int START_SAFE_ZONE = 1;
    private static final int END_SAFE_ZONE = 2;
    private static final int N_BREAKABLE_BLOCKS = 10;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 18;
    private static final int N_ENEMIES = 5;
    private final GameObjectFactory factory;
    private final Set<Block> blocks;
    private final Set<Position> freeTiles;
    private final Set<DynamicObject> enemies;
    private Door door;
    private final Set<PickableObject> pickableObjects;
    private Optional<PickableObject> key;
    private DynamicObject player;
    private final Sprite grassSprite;

    /**
     * Constructor.
     * @param theme : the theme of the sprites
     */
    public WorldImpl(final Theme theme) {
        this.factory = new GameObjectFactoryImpl(theme);
        this.blocks = new TreeSet<>();
        this.freeTiles = new TreeSet<>();
        this.enemies = new TreeSet<>();
        this.pickableObjects = new TreeSet<>();
        this.createLevel();
        //TODO instead to call back getWallSprite it has to call back getGrassSprite
        this.grassSprite = theme.getSprites().getWallSprite();
    }

    private void createLevel() {
        this.initializeFreePositions();
        this.initializeUnbreakableBlocks();
        this.initializeBreakableBlocks();
        this.initializeKey();
        this.initializeDoor();
        this.initializePlayer();
        this.initilizeEnemies();
    }

    /**
     * Initializations of free tiles.
     */
    private void initializeFreePositions() {
        IntStream.range(LOWER_BOUND, UPPER_BOUND)
                 .mapToObj(n -> IntStream.range(LOWER_BOUND, UPPER_BOUND)
                                         .mapToObj(m -> new Position(m * SpriteSheet.SPRITE_SIZE_IN_GAME, 
                                                 n * SpriteSheet.SPRITE_SIZE_IN_GAME))
                                         .collect(Collectors.toSet()))
                 .forEach(s -> this.freeTiles.addAll(s));
    }

    /**
     * Initializations of unbreakable blocks.
     */
    private void initializeUnbreakableBlocks() {
        this.getFreePosition().stream()
                              .filter(p -> p.getX() % 2 == 0 && p.getY() % 2 == 0)
                              .map(p -> this.factory.createUnbreakableBlock(p))
                              .forEach(b -> {
                                  this.blocks.add(b);
                                  this.freeTiles.remove(b.getPosition());
                              });
    }

    /**
     * Initializations of unbreakable blocks.
     */
    private void initializeBreakableBlocks() {
        final Random r = new Random();
        IntStream.of(N_BREAKABLE_BLOCKS)
                 .mapToObj(i -> r.nextInt(this.freeTiles.size() - this.getSafeZone().size()))
                 .map(i -> this.factory.createBreakableBlock(this.freeTiles.stream()
                                                                           .filter(p -> !this.getSafeZone().contains(p))
                                                                           .collect(Collectors.toList())
                                                                           .get(i)))
                 .forEach(b -> {
                     this.blocks.add(b);
                     this.freeTiles.remove(b.getPosition());
                 });
    }

    /**
     * Initialization of the key.
     */
    private void initializeKey() {
        final Random r = new Random();
        this.key = Optional.of(this.factory.createKey(this.getBlocks().stream()
                                                                      .filter(b -> b.isBreakable())
                                                                      .collect(Collectors.toList())
                                                                      .get(r.nextInt(N_BREAKABLE_BLOCKS))
                                                                      .getPosition()));
        this.pickableObjects.add(this.key.get());
    }

    /**
     * Initialization of the door.
     */
    private void initializeDoor() {
        final Random r = new Random();
        final List<Position> freeBreakableBlocks = this.getBlocks().stream()
                                                                   .filter(b -> b.isBreakable())
                                                                   .map(b -> b.getPosition())
                                                                   .filter(p -> !this.pickableObjects.stream()
                                                                                                    .map(o -> o.getPosition())
                                                                                                    .filter(o -> o.compareTo(p))
                                                                                                    .findAny().isPresent())
                                                                   .collect(Collectors.toList());
        this.door = this.factory.createDoor(freeBreakableBlocks.get(r.nextInt(freeBreakableBlocks.size())));
    }

    /**
     * Initialization of the player.
     */
    private void initializePlayer() {
        this.player = this.factory.createPlayer(new Position(START_SAFE_ZONE, START_SAFE_ZONE));
    }

    /**
     * Initializations of enemies.
     */
    private void initilizeEnemies() {
        //TODO create other kinds of enemies
        final Random r = new Random();
        IntStream.of(N_ENEMIES)
                 .mapToObj(i -> r.nextInt(this.freeTiles.size() - this.getSafeZone().size()))
                 .map(i -> this.factory.createBalloomEnemy(this.freeTiles.stream()
                                                                         .filter(p -> !this.getSafeZone().contains(p))
                                                                         .collect(Collectors.toList())
                                                                         .get(i)))
                 .forEach(e -> this.enemies.add(e));
    }

    /**
     * Get the zone where, at the start of the game, the player can start play safely. 
     * @return the safe zone
     */
    private Set<Position> getSafeZone() {
        final Set<Position> safeZone = new TreeSet<>();
        IntStream.range(START_SAFE_ZONE, END_SAFE_ZONE)
                        .mapToObj(n -> IntStream.range(START_SAFE_ZONE, END_SAFE_ZONE)
                                                .mapToObj(m -> new Position(n, m))
                                                .collect(Collectors.toSet()))
                        .forEach(s -> safeZone.addAll(s));
        return safeZone;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Position> getFreePosition() {
        return Collections.unmodifiableSet(this.freeTiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getAllGameObjects() {
        final Set<GameObject> allGameObjects = new TreeSet<>();
        allGameObjects.addAll(this.blocks);
        allGameObjects.addAll(this.pickableObjects);
        allGameObjects.addAll(this.enemies);
        allGameObjects.add(this.player);
        allGameObjects.add(this.door);
        return Collections.unmodifiableSet(allGameObjects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Block> getBlocks() {
        return Collections.unmodifiableSet(this.blocks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<DynamicObject> getEnemies() {
        return Collections.unmodifiableSet(this.enemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DynamicObject getPlayer() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door getDoor() {
        return this.door;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<PickableObject> getKey() {
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObject(final GameObject object) {
        if (this.blocks.contains(object)) {
            this.blocks.remove(object);
            this.freeTiles.add(object.getPosition());
        } else if (this.pickableObjects.contains(object)) {
            this.pickableObjects.remove(object);
            if (this.key.isPresent() && this.key.get().equals(object)) {
                this.key = Optional.empty();
            }
        } else if (this.enemies.contains(object)) {
            this.enemies.remove(object);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final long elapsedTime) {
        this.enemies.forEach(e -> e.update(elapsedTime));
        this.player.update(elapsedTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g) {
        //TODO render the blocks at borders
        this.renderGrass(g);
        this.pickableObjects.forEach(o -> o.render(g));
        this.door.render(g);
        this.blocks.forEach(b -> b.render(g));
        this.enemies.forEach(e -> e.render(g));
        this.player.render(g);
    }

    private void renderGrass(final Graphics2D g) {
        IntStream.range(LOWER_BOUND, UPPER_BOUND)
                 .mapToObj(x -> IntStream.range(LOWER_BOUND, UPPER_BOUND)
                                         .mapToObj(y -> new Position(x, y))
                                         .collect(Collectors.toSet()))
                 .forEach(s -> s.forEach(p -> g.drawImage(this.grassSprite.getBufferedImage(), 
                                                 AffineTransform.getTranslateInstance(p.getX(), p.getY()), null)));
    }
}
