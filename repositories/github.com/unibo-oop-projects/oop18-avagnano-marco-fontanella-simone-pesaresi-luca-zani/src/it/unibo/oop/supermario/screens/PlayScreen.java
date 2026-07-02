package it.unibo.oop.supermario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.supermario.character.MarioControllerImpl;
import it.unibo.oop.supermario.character.MarioImpl;
import it.unibo.oop.supermario.character.MarioViewImpl;
import it.unibo.oop.supermario.enemies.EnemiesControllerImpl;
import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.CollisionFinder;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.powerup.FireballController;
import it.unibo.oop.supermario.powerup.FireballModel;
import it.unibo.oop.supermario.powerup.FireballView;
import it.unibo.oop.supermario.powerup.PowerUpItem;
import it.unibo.oop.supermario.world.Brick;
import it.unibo.oop.supermario.world.BrickController;
import it.unibo.oop.supermario.world.BrickControllerImpl;
import it.unibo.oop.supermario.world.CoinController;
import it.unibo.oop.supermario.world.Flag;
import it.unibo.oop.supermario.world.FlagController;
import it.unibo.oop.supermario.world.FlagControllerImpl;
import it.unibo.oop.supermario.world.FlagView;
import it.unibo.oop.supermario.world.SolidWorldCreator;
import it.unibo.oop.supermario.world.SolidWorldCreatorImpl;

/**
 * Class that allows all object to be drawn on the screen.
 */
public class PlayScreen implements Screen {
    /**
     * Set step parameter time frequency in world.
     */
    public static final float TIME_STEP = 1 / 60f;
    /**
     * Set step parameters velocity iterations and position iterations in world.
     */
    public static final int VEL_ITERATIONS = 6,
            POS_ITERATIONS = 2;

    private final GameManager gameManager;
    private final Vector2 gravity = new Vector2(0, -10);
    private final SuperMario game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Hud hud;
    private World world;
    private Box2DDebugRenderer b2dr;
    private FlagController flagItem;
    private MarioControllerImpl marioController;
    private EnemiesControllerImpl enemiesController;
    private Array<FireballController> fireballs;
    private Array<PowerUpItem> powerUps;
    private Array<CoinController> coinItems;
    private Array<BrickController> bricksToBreak;
    private boolean enableDebug;
    /**
     * Playscreen constructor.
     * 
     * @param marioBros the main class mario
     */
    public PlayScreen(final SuperMario marioBros) {
        this.game = marioBros;
        this.gameManager = GameManager.instance;
        if (!this.gameManager.getDisableAudio()) {
            GameManager.music.play();
        }
    }

    @Override
    public final void show() {
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(GameManager.V_WIDTH / GameManager.PPM, GameManager.V_HEIGHT / GameManager.PPM,
                gamecam);
        hud = new Hud(game.batch);
        final TmxMapLoader ml = new TmxMapLoader();
        map = ml.load("levels/" + "level" + this.gameManager.getCurrentLevel() + ".tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / GameManager.PPM);
        ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
        gamecam.position.set(GameManager.V_WIDTH / 2 / GameManager.PPM, GameManager.V_HEIGHT / 2 / GameManager.PPM, 0);
        world = new World(gravity, true);
        b2dr = new Box2DDebugRenderer();
        marioController = new MarioControllerImpl(new MarioImpl(this, 32 / GameManager.PPM, 32 / GameManager.PPM), new MarioViewImpl());
        enemiesController = new EnemiesControllerImpl(this, map);
        world.setContactListener(new CollisionFinder());
        enableDebug = false;
        final SolidWorldCreator solidWorld = new SolidWorldCreatorImpl(this);
        solidWorld.initSolidWorld();
        powerUps = new Array<>();
        fireballs = new Array<FireballController>();
        coinItems = new Array<>();
        bricksToBreak = new Array<>();
    }


    /**Method that add brick to array that will allows it to draw and render on the screen.
     * @param brick brick object to add
     */
    public void addBrickToBreak(final Brick brick) {
        bricksToBreak.add(new BrickControllerImpl(brick));
    }

    /** Add fire balls to array that will allows it to draw and render on the screen.
     *
     * @param x the x-axis
     * @param y the y-axis
     * @param runningRight check the direction on Mario
     */
    public void addFireball(final float x, final float y, final boolean runningRight) {
        fireballs.add(new FireballController(new FireballModel(this, x, y, runningRight), new FireballView()));
    }

    /**
     * Method that add power up to array that will allows it to draw and render on
     * the screen.
     * 
     * @param powerUp power up item
     */
    public void addPowerUp(final PowerUpItem powerUp) {
        powerUps.add(powerUp);
    }

    /**
     * Method that add coin to array that will allows it to draw and render on the
     * screen.
     * 
     * @param cController coin controller manages coin
     */
    public void addCoinItems(final CoinController cController) {
        coinItems.add(cController);
    }

    /**
     * Method that initializes Flag Controller.
     * 
     * @param fModel flag controller manages flag
     */
    public void setFlag(final Flag fModel) {
        flagItem = new FlagControllerImpl(fModel, new FlagView());
    }

    private void cleanUpObject() {
        // clean up fireballs from Screen
        for (int i = 0; i < fireballs.size; i++) {
            if (fireballs.get(i).isDestroyed()) {
                fireballs.removeIndex(i);
            }
        }

        for (int i = 0; i < powerUps.size; i++) {
            if (powerUps.get(i).isDestroyed()) {
                powerUps.removeIndex(i);
            }
        }
        for (int i = 0; i < coinItems.size; i++) {
            if (coinItems.get(i).isDestroyed()) {
                coinItems.removeIndex(i);
            }
        }
        for (int i = 0; i < bricksToBreak.size; i++) {
            if (bricksToBreak.get(i).isDestroyed()) {
                bricksToBreak.removeIndex(i);
            }
        }
    }

    /**
     * Update game's every frame.
     *
     * @param dt delta time of the frame
     */
    public void update(final float dt) {
        world.step(TIME_STEP, VEL_ITERATIONS, POS_ITERATIONS);
        hud.update(dt);

        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            enableDebug = !enableDebug;
        }

        marioController.update(dt);
        enemiesController.update(dt);
        for (final BrickController brck : bricksToBreak) {
            if (brck.isToDestroy()) {
                brck.update(dt);
            }
        }
        for (final FireballController fireball : fireballs) {
            fireball.update(dt);
        }
        for (final PowerUpItem mI : powerUps) {
            mI.update(dt);
        }
        for (final CoinController cI : coinItems) {
            cI.update(dt);
        }

        flagItem.update(dt);
        if (flagItem.isFlagFalling()) {
            flagItem.setFlagDown();
            GameManager.music.dispose();
            flagItem.worldClearSound();
        }
        if (marioController.getBody().getPosition().x >= GameManager.V_WIDTH / 2 / GameManager.PPM) {
            gamecam.position.x = marioController.getBody().getPosition().x;
        }
        gamecam.update();
        mapRenderer.setView(gamecam);
    }

    @Override
    public final void render(final float delta) {
        update(delta);

        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();
        game.batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        // draw mario on the screen
        marioController.draw(game.batch);
        //draw enemies
        enemiesController.draw(game.batch);

        flagItem.draw(game.batch);

        for (final FireballController fireball : fireballs) {
            fireball.draw(game.batch);
        }
        for (final PowerUpItem mI : powerUps) {
            mI.draw(game.batch);
        }
        for (final CoinController cI : coinItems) {
            cI.draw(game.batch);
        }
        if (enableDebug) {
            b2dr.render(world, gamecam.combined);
        }
        game.batch.end();

        // draw hud
        hud.getStage().draw();

        // clean Object from view
        cleanUpObject();

        if (marioController.isDead()) {
            gameOver();
        }
        if (flagItem.isEndGame()) {
            winGame();
        }
    }

    @Override
    public final void resize(final int width, final int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public final void dispose() {
        mapRenderer.dispose();
        map.dispose();
        world.dispose();
        hud.dispose();
        b2dr.dispose();
    }

    /**
     * Get the world of the game.
     * 
     * @return world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Get the map of the game.
     * 
     * @return map
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * Get the position of Mario in the game screen.
     * 
     * @return position
     */
    public Vector2 getMarioPosition() {
        return this.marioController.getMarioPosition();
    }

    /**
     * Get the game over screen.
     */
    public void gameOver() {
        dispose();
        game.setScreen(new GameOverScreen(game));
    }

    /**
     * Get the game win screen.
     */
    public void winGame() {
        dispose();
        game.setScreen(new WinScreen(game));
    }

    /**
     * Get the camera of mario position.
     * 
     * @return camera
     */
    public OrthographicCamera getCamera() {
        return this.gamecam;
    }

    /**
     * Get the hud of the game.
     * 
     * @return hud
     */
    public Hud getHud() {
        return hud;
    }

    /**
     * Get Mario's controller.
     * 
     * @return marioController
     */
    public MarioControllerImpl getMarioController() {
        return marioController;
    }
}