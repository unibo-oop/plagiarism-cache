package it.unibo.model.launchedgame.impl;

import java.util.List;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.camera.impl.AltitudeManager;
import it.unibo.model.camera.impl.CameraImpl;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.launchedgame.api.AbstractLaunchedState;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.physics.collision.impl.CollisionManagerImpl;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.world.impl.BoundWorldImpl;
import it.unibo.model.world.impl.BoundY;
import it.unibo.model.world.impl.Boundary;
import it.unibo.model.world.impl.RealWorld;
import it.unibo.model.world.impl.UpperWorld;
import it.unibo.model.world.impl.World;
import it.unibo.model.worldconstructor.data.Difficult;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPoolMedium;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.GameObjDimension;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolCreatorImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.impl.SpawnPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolEasy;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PlatformPoolMedium;
import it.unibo.model.worldconstructor.worldgenerator.impl.WorldConstructorImpl;
import it.unibo.model.worldconstructor.worldgenerator.impl.WorldDifficultImpl;

/**
 * Represents the initial state of a launched game.
 * Used for setup and initialization before the game starts running.
 */
public class InitialState extends AbstractLaunchedState {

    /**
     * Constructs a new InitialState.
     * 
     * @param launchedGame the game context
     */
    public InitialState(final LaunchedGame launchedGame) {
        super(launchedGame);
    }

    /**
     * {@inheritDoc}
     * Performs initialization tasks.
     */
    @Override
    public void onEnter() {
        final var boundX = new Boundary(0, 600);
        final var boundY = new BoundY(-1000, 1000);
        final var boundary = new BoundWorldImpl(boundY, boundX);
        final var upperWorld = new UpperWorld(boundary);
        final var alien = new AlienImpl(new Vector2dImpl(300, 900), new Vector2dImpl(0, -1000.0), 100, 100,
                new ActiveUpgradesImpl(this.launchedGame.getMenu().getInventory()));
        final var realWorld = new RealWorld(alien, boundary);
        final var world = new World(upperWorld, realWorld);
        this.launchedGame.setWorld(world);
        final var distanceEasy = new Distance(200, 100, 300);
        final var distanceMedium = new Distance(300, 150, 250);
        final var spawnPoolCreator = new SpawnPoolCreatorImpl(upperWorld);
        final var spawnPoolEasy = new SpawnPoolEasy(GameObjDimension.EASY_PLATFORM_WIDTH,
                GameObjDimension.EASY_PLATFORM_HEIGHT, this.launchedGame.getMenu().getScoreManager());
        final var platformPoolEasy = new PlatformPoolEasy(spawnPoolCreator, GameObjDimension.EASY_PLATFORM_WIDTH,
                GameObjDimension.EASY_PLATFORM_HEIGHT);
        final var platformPoolMedium = new PlatformPoolMedium(spawnPoolCreator, GameObjDimension.MEDIUM_PLATFORM_WIDTH,
                GameObjDimension.MEDIUM_PLATFORM_HEIGHT);
        final var addOnPoolEasy = new AddOnPoolEasy(spawnPoolCreator, 0.3);
        final var addOnPoolMedium = new AddOnPoolMedium(spawnPoolCreator, 0.5);
        final var difficultList = List.of(new Difficult(10_000, distanceEasy, spawnPoolEasy, addOnPoolEasy, platformPoolEasy),
                new Difficult(20_000, distanceMedium, spawnPoolEasy, addOnPoolMedium, platformPoolMedium));
        spawnPoolCreator.setSpawnPool(spawnPoolEasy);
        final var worldConstructor = new WorldConstructorImpl(upperWorld, difficultList.getFirst(), spawnPoolCreator);
        final var worldDifficult = new WorldDifficultImpl(difficultList);
        worldDifficult.registerObserver(worldConstructor);
        final var altitudeManager = new AltitudeManager(alien, 300);
        altitudeManager.addObserver(worldDifficult);
        final var camera = new CameraImpl(boundX.x1(), boundY.maxY() - boundY.minY(), world, worldConstructor);
        altitudeManager.addObserver(camera);
        final var scoreManager = this.launchedGame.getMenu().getScoreManager();
        scoreManager.resetScore();
        altitudeManager.addObserver((AltitudeObserver) scoreManager);
        final var collisionManager = new CollisionManagerImpl(boundX);
        worldConstructor.fillWorld();
        camera.update(0);
        launchedGame.setState(new RunningState(launchedGame, world, collisionManager, altitudeManager, scoreManager));
    }
}
