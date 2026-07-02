package it.unibo.model.worldconstructor.worldgenerator.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.gameobj.impl.PlatformImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BoundWorld;
import it.unibo.model.world.api.QueueWorld;
import it.unibo.model.worldconstructor.data.Difficult;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.PlatformPositionGenerator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPositionGeneratorImpl;
import it.unibo.model.worldconstructor.worldgenerator.api.Observer;
import it.unibo.model.worldconstructor.worldgenerator.api.WorldConstructor;

/**
 * Implementation of the WorldConstructor interface.
 * Generates the game world by creating platforms and add-ons (coins, monsters,
 * gadgets)
 * based on the current difficulty.
 */
public class WorldConstructorImpl implements WorldConstructor, Observer {

  private static final double EPSILON = 1e-4;

  /**
   * The current difficulty configuration.
   */
  private Difficult difficult;

  /**
   * Generators and creators for platforms positions.
   */
  private final PlatformPositionGenerator platformPositionGenerator;

  /**
   * Creators for platforms.
   */
  private final PlatformCreator platformCreator;

  /**
   * Creator for add-ons.
   */
  private final AddOnCreator addOnCreator;

  /**
   * Random number generator for generating random values.
   */
  private final Random random;

  /**
   * The boundary of the game world.
   */
  private final BoundWorld bound;

  /**
   * The position of the last created platform.
   */
  private Vector2d lastPlatformPos;

  /**
   * The last static platform created, used to determine where to place add-ons.
   */
  private Optional<Platform> lastStaticPlatform;

  /**
   * The creator for spawn pools, used to generate game objects.
   */
  private final SpawnPoolCreator spawnPoolCreator;

  /**
   * The game world to be filled with platforms and add-ons.
   */
  private final QueueWorld world;

  /**
   * Constructs a new WorldConstructorImpl.
   *
   * @param world            the game world to be filled with platforms and
   *                         add-ons.
   * @param difficult        the initial difficulty configuration.
   * @param spawnPoolCreator the creator for spawn pools, used to generate game
   *                         objects.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The spawn pool creator is necessary for the world "
      + "constructor to generate game objects based on the difficulty configuration")
  public WorldConstructorImpl(final QueueWorld world, final Difficult difficult,
      final SpawnPoolCreator spawnPoolCreator) {
    this.spawnPoolCreator = spawnPoolCreator;
    this.world = world;
    this.difficult = difficult;
    this.random = new Random();
    this.bound = world.getBoundWorld();
    final var pos = new Vector2dImpl(bound.getBoundX().x1() / 2, bound.getBoundY().maxY() - 100);
    final var firstPlatform = new PlatformImpl(pos, difficult.platformPool().getWidth(),
        difficult.platformPool().getHeight(), Optional.empty(), Optional.empty());
    world.addStaticPlatform(firstPlatform);
    this.lastPlatformPos = pos;
    this.platformPositionGenerator = new PlatformPositionGeneratorImpl(bound, pos, difficult.distance());
    this.platformPositionGenerator.setDistance(difficult.distance());
    this.addOnCreator = new AddOnCreatorImpl(difficult.addOnPool());
    this.platformCreator = new PlatformCreatorImpl(difficult.platformPool());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillWorld() {
    lastPlatformPos = getLastPlatformPos();
    while (lastPlatformPos.getY() > bound.getBoundY().minY() + difficult.distance().maxDistanceY()) {
      createPlatform();
    }
  }

  private void createPlatform() {
    final double chance = random.nextDouble(1.0);
    final double chanceAddOn = random.nextDouble(1.0);
    final Vector2d pos = platformPositionGenerator.generatePosition(difficult.platformPool().getWidth(),
        difficult.platformPool().getHeight(), lastPlatformPos);
    this.platformCreator.createPlatform(chance, pos);
    this.lastPlatformPos = pos;
    if (!world.getStaticPlatforms().isEmpty()) {
      this.lastStaticPlatform = Optional.of(world.getStaticPlatforms().getLast());
    } else {
      this.lastStaticPlatform = Optional.empty();
    }

    if (chanceAddOn < difficult.addOnPool().getChanceAddOn()
        && lastStaticPlatform.isPresent()
        && Math.abs(lastStaticPlatform.get().getPosY() - lastPlatformPos.getY()) < EPSILON) {
      createAddOn();
    }
  }

  private void createAddOn() {
    final double chance = random.nextDouble(1.0);
    addOnCreator.selectAddOn(chance, lastStaticPlatform.get());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(final Difficult dif) {
    this.platformPositionGenerator.setDistance(dif.distance());
    this.spawnPoolCreator.setSpawnPool(dif.spawnPool());
    this.platformCreator.setPlatformPool(dif.platformPool());
    this.addOnCreator.setAddOnPool(dif.addOnPool());
    this.difficult = dif;
  }

  private Vector2d getLastPlatformPos() {
    final List<Platform> platforms = new LinkedList<>();
    if (!world.getStaticPlatforms().isEmpty()) {
      platforms.add(world.getStaticPlatforms().getLast());
    }
    if (!world.getMovingPlatforms().isEmpty()) {
      platforms.add(world.getMovingPlatforms().getLast());

    }
    if (!world.getOnTouchPlatforms().isEmpty()) {
      platforms.add(world.getOnTouchPlatforms().getLast());
    }
    if (platforms.isEmpty()) {
      return new Vector2dImpl(bound.getBoundX().x1() / 2, bound.getBoundY().maxY());
    }
    var pos = new Vector2dImpl(platforms.getFirst().getPosX(), platforms.getFirst().getPosY());
    for (final Platform p : platforms) {
      if (p.getPosY() < pos.getY()) {
        pos = new Vector2dImpl(p.getPosX(), p.getPosY());
      }
    }
    return pos;
  }
}
