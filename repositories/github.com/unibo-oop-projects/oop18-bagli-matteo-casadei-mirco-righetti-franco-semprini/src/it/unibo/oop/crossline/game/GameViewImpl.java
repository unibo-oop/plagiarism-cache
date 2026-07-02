package it.unibo.oop.crossline.game;

import java.util.List;
import java.util.Optional;
import org.lwjgl.util.Dimension;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.game.camera.Camera;
import it.unibo.oop.crossline.game.camera.CameraImpl;
import it.unibo.oop.crossline.game.wave.Wave;
import it.unibo.oop.crossline.skin.EntitySkin;
import it.unibo.oop.crossline.skin.attributes.PlayerState;

/**
 * This class is the game view, which manages the rendering of the game.
 */
public class GameViewImpl extends Game implements GameView {

    private static final float BACKGROUND_RED = 0.114f;
    private static final float BACKGROUND_GREEN = 0.129f;
    private static final float BACKGROUND_BLUE = 0.176f;
    private static final Dimension VIEWPORT_SIZE = new Dimension(512, 288);
    private static final float UNITY_SCALE = 1 / 16f;

    private Optional<Player> player;
    private Optional<List<? extends Robot>> enemies;
    private final Camera camera;
    private final Box2DDebugRenderer debugRenderer;
    private Optional<OrthogonalTiledMapRenderer> mapRenderer;
    private Optional<World> world;

    private EntitySkin<? extends Actor> skinPlayer;
    private EntitySkin<? extends Robot> skinEnemy;

    private final SpriteBatch spriteBatch;

    /**
     * Initialize the game view.
     */
    public GameViewImpl() {
        super();
        camera = new CameraImpl(VIEWPORT_SIZE);
        camera.setZoom(UNITY_SCALE);
        debugRenderer = new Box2DDebugRenderer();
        mapRenderer = Optional.empty();
        spriteBatch = new SpriteBatch();
        world = Optional.empty();
        enemies = Optional.empty();
    }

    @Override
    public final void dispose() {
        super.dispose();
        skinEnemy.dispose();
        skinPlayer.dispose();
    }

    @Override
    public final void render(final float delta) {
        Gdx.gl.glClearColor(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE, 1f);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        this.camera.update(delta);
        if (mapRenderer.isPresent()) {
            mapRenderer.get().setView((OrthographicCamera) this.camera);
            mapRenderer.get().render();
        }
        if (world.isPresent()) {
            debugRenderer.render(this.world.get(), this.camera.getCombined());
        }

        // Start to draw sprite

        if (player.isPresent() && !player.get().isQueuedForDestruction()) {
            spriteBatch.setProjectionMatrix(camera.getCombined());
            skinPlayer.setRenderAnimationtoDraw();
            spriteBatch.begin();
            skinPlayer.drawAnimation(spriteBatch, player.get().getPosition());
            spriteBatch.end();
        }

        if (enemies.isPresent()) {
            enemies.get().forEach((Robot robot) -> {
                if (!robot.isQueuedForDestruction()) {
                    skinEnemy.setRenderAnimationtoDraw();
                    spriteBatch.begin();
                    skinEnemy.drawAnimation(spriteBatch, robot.getPosition());
                    spriteBatch.end();
                }
            });
        }

        // End to draw sprite
    }

    @Override
    public final void setTiledMap(final TiledMap tiledMap) {
        mapRenderer = Optional.of(new OrthogonalTiledMapRenderer(tiledMap, UNITY_SCALE));

    }

    @Override
    public final void setWorld(final World world) {
        this.world = Optional.of(world);
    }

    @Override
    public final Camera getCamera() {
        return camera;
    }

    @Override
    public final void setPlayer(final Player player) {
        this.player = Optional.of(player);
        skinPlayer = new EntitySkin<Actor>(this.player.get());
        skinPlayer.setState(PlayerState.IS_IDLE); // to be more expressive
    }

    @Override
    public final void setEnemy(final Wave wave) {
        this.enemies = Optional.of(wave.getRobots());
        if (this.enemies.isPresent()) {
            skinEnemy = new EntitySkin<Robot>(this.enemies.get().stream().findFirst().get());
            skinEnemy.setState(PlayerState.IS_IDLE); // to be more expressive
        }
    }

    @Override
    public void create() {
    }

}
