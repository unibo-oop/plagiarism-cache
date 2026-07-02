package it.unibo.oop.crossline.game;

import java.util.Observable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.player.PlayerImpl;
import it.unibo.oop.crossline.game.attributes.Destructible;
import it.unibo.oop.crossline.game.attributes.Updatable;
import it.unibo.oop.crossline.game.bullet.BulletBuilder;
import it.unibo.oop.crossline.game.bullet.BulletBuilderImpl;
import it.unibo.oop.crossline.game.physics.ContactListenerImpl;
import it.unibo.oop.crossline.game.wave.Wave;
import it.unibo.oop.crossline.game.wave.WaveImpl;
import it.unibo.oop.crossline.game.weapon.Weapon;
import it.unibo.oop.crossline.game.weapon.WeaponImpl;
import it.unibo.oop.crossline.game.world.arena.Arena;
import it.unibo.oop.crossline.game.world.arena.ArenaImpl;
import it.unibo.oop.crossline.game.world.platform.PlatformFactory;
import it.unibo.oop.crossline.game.world.platform.PlatformFactoryImpl;
import it.unibo.oop.crossline.game.world.spike.SpikeFactory;
import it.unibo.oop.crossline.game.world.spike.SpikeFactoryImpl;
import it.unibo.oop.crossline.io.InputController;

public class GameModelImpl extends Observable implements GameModel {

    private static final float EARTH_GRAVITY = -9.8f;
    private static final float TIME_STEP = 1 / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final float UNITY_SCALE = 1 / 16f;
    private static final float PLAYER_BULLET_DAMAGE = 100f;
    private static final float PLAYER_BULLET_SPEED = 8f;
    private static final float PLAYER_BULLET_SIZE = 0.25f;
    private static final int PLAYER_SHOT_DELAY = 100;

    private final Arena arena;
    private boolean exit;
    private final Player player;
    private Wave wave;
    private final World world;

    public GameModelImpl() {
        arena = new ArenaImpl("arena.tmx");
        world = new World(new Vector2(0, EARTH_GRAVITY), true);
        world.setContactListener(new ContactListenerImpl());

        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(arena.getCenter().scl(UNITY_SCALE));

        final BulletBuilder bulletBuilder = new BulletBuilderImpl()
                .setDamage(PLAYER_BULLET_DAMAGE)
                .setSpeed(PLAYER_BULLET_SPEED)
                .setSize(PLAYER_BULLET_SIZE);

        player = new PlayerImpl(world, arena.getCenter().scl(UNITY_SCALE));
        final InputController controller = new InputController((PlayerImpl) this.player);
        Gdx.input.setInputProcessor(controller);
        final Weapon weaponPlayer = new WeaponImpl(PLAYER_SHOT_DELAY, bulletBuilder);
        player.setWeapon(weaponPlayer);
        weaponPlayer.setOwner(player);

        wave = new WaveImpl(player, 1);

        final PlatformFactory platformFactory = new PlatformFactoryImpl(world);
        arena.getLayerTilesPositions(arena.getLayer("Platforms")).stream().forEach((position) -> platformFactory.createPlatform(position));
        arena.getLayerTilesPositions(arena.getLayer("Border")).stream().forEach((position) -> platformFactory.createPlatform(position));

        final SpikeFactory spikeFactory = new SpikeFactoryImpl(world);
        arena.getLayerTilesPositions(arena.getLayer("Spikes")).stream().forEach((position) -> spikeFactory.createSpike(position));

    }

    @Override
    public final World getWorld() {
        return world;
    }

    @Override
    public final TiledMap getTiledMap() {
        return arena.getTiledMap();
    }

    @Override
    public final void update() {
        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        final Array<Body> bodies = new Array<>(world.getBodyCount());
        world.getBodies(bodies);

        if (wave.hasEnded()) {
            wave = new WaveImpl(player, wave.getDifficulty() + 1);
            setChanged();
            notifyObservers();
        }

        bodies.forEach((body) -> {
            final Object userData = body.getUserData();
            if (userData instanceof Updatable) {
                final Updatable updatable = (Updatable) userData;
                updatable.update();
            }
            if (userData instanceof Destructible) {
                final Destructible destructible = (Destructible) userData;
                if (destructible.isQueuedForDestruction()) {
                    world.destroyBody(destructible.getBody());
                    if (destructible instanceof Player) {
                        exit = true;
                    }
                }
            }
        });
    }

    @Override
    public final boolean shouldExit() {
        return exit;
    }

    @Override
    public final Player getPlayer() {
        return player;
    }

    @Override
    public final Wave getCurrentWave() {
        return wave;
    }
}
