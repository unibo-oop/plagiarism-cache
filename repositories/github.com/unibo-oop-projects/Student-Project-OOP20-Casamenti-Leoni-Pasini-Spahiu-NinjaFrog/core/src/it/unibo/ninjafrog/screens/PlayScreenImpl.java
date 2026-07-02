package it.unibo.ninjafrog.screens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.ninjafrog.enemies.EnemyController;
import it.unibo.ninjafrog.enemies.EnemyControllerImpl;
import it.unibo.ninjafrog.frog.FrogController;
import it.unibo.ninjafrog.frog.FrogControllerImpl;
import it.unibo.ninjafrog.fruits.FruitBuilderImpl;
import it.unibo.ninjafrog.fruits.FruitPowerUp;
import it.unibo.ninjafrog.fruits.FruitType;
import it.unibo.ninjafrog.game.NinjaFrogGame;
import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.game.utilities.Pair;
import it.unibo.ninjafrog.game.utilities.SoundManager;
import it.unibo.ninjafrog.screens.hud.Hud;
import it.unibo.ninjafrog.screens.hud.HudImpl;
import it.unibo.ninjafrog.screens.levels.Level;
import it.unibo.ninjafrog.world.Collidable;
import it.unibo.ninjafrog.world.WorldCollisionListener;
import it.unibo.ninjafrog.world.WorldCreator;
import it.unibo.ninjafrog.world.WorldCreatorImpl;

/**
 * Implementation of the {@link it.unibo.ninjafrog.screens.PlayScreen
 * PlayScreen}.
 */
public final class PlayScreenImpl implements PlayScreen {
    private static final double BLANK_TIME = 0.5;
    private static final int TEXT_TIME = 1;
    private static final int BG3_X = 135;
    private static final int BG2_X = 300;
    private static final int BG1_X = 440;
    private static final int BG3_Y = 240;
    private static final int BG2_Y = 300;
    private static final int BG1_Y = 360;
    private static final int BG3_WIDTH = 950;
    private static final int BG2_WIDTH = 615;
    private static final int BG1_WIDTH = 338;
    private static final int BG_HEIGHT = 58;
    private static final int LABEL3_Y = 95;
    private static final int LABEL2_Y = 115;
    private static final int LABEL1_Y = 135;
    private static final int LABEL3_X = 50;
    private static final int LABEL2_X = 105;
    private static final int LABEL1_X = 150;
    private static final int WORLD_X_GRAVITY = 0;
    private static final int WORLD_Y_GRAVITY = -10;
    private static final int UNIT = 1;
    private static final int CAM_Z_COMPONENT = 0;
    private static final int HALF = 2;
    private static final int WORLD_POS_ITER = 2;
    private static final int WORLD_VEL_ITER = 6;
    private static final float WORLD_TIME_STEP = 1f / 60f;
    private static final float ACCUMULATOR_DEFAULT = 0.25f;
    private float accumulator;
    private final TextureAtlas atlas;
    private final NinjaFrogGame game;
    private final SoundManager sound;
    private final Hud hud;
    private final TiledMap map;
    private final OrthographicCamera cam;
    private final Viewport viewport;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final World world;
    private final FrogController playerController;
    private final EnemyController enemies;
    private final List<FruitPowerUp> fruits;
    private final List<Pair<Float, Float>> cherriesToSpawn;
    private final List<Pair<Float, Float>> melonsToSpawn;
    private final List<Pair<Float, Float>> orangesToSpawn;
    private float pauseTime;
    private final ShapeRenderer textBackground;

    /*
     * BOX DEBUGGER IN CASE OF DEBUG. private final Box2DDebugRenderer b2debug;
     */
    /**
     * Public constructor of the PlayScreenImpl.
     * 
     * @param game  The {@link it.unibo.ninjafrog.game.NinjaFrogGame game} class.
     * @param level The {@link it.unibo.ninjafrog.screens.levels.Level level}
     *              selected.
     * @param sound The {@link it.unibo.ninjafrog.game.utilities.SoundManager
     *              SoundManager}.
     */
    public PlayScreenImpl(final NinjaFrogGame game, final Level level, final SoundManager sound) {
        this.game = game;
        this.sound = sound;
        this.sound.playGameSong();
        this.atlas = new TextureAtlas("ninjaAndEnemies.pack");
        this.map = new TmxMapLoader().load(level.getMap());
        this.cam = new OrthographicCamera();
        this.viewport = new FitViewport(this.scale(GameConst.WIDTH), this.scale(GameConst.HEIGHT), this.cam);
        this.cam.position.set(this.halfOf(this.viewport.getWorldWidth()), this.halfOf(this.viewport.getWorldHeight()),
                CAM_Z_COMPONENT);
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.map, this.scale(UNIT));
        this.world = new World(new Vector2(WORLD_X_GRAVITY, WORLD_Y_GRAVITY), true);
        /*
         * BOX DEBUGGER IN CASE OF DEBUG. this.b2debug = new Box2DDebugRenderer();
         */
        this.hud = new HudImpl(this.game.getBatch());
        final WorldCreator worldCreator = new WorldCreatorImpl(this);
        worldCreator.createWorld();
        this.enemies = new EnemyControllerImpl(this);
        this.world.setContactListener(new WorldCollisionListener(this.enemies, this));
        this.playerController = new FrogControllerImpl(this);
        this.fruits = new ArrayList<>();
        this.orangesToSpawn = new LinkedList<>();
        this.cherriesToSpawn = new LinkedList<>();
        this.melonsToSpawn = new LinkedList<>();
        this.textBackground = new ShapeRenderer();
    }

    private float halfOf(final float value) {
        return value / HALF;
    }

    private float scale(final int value) {
        return value / GameConst.PPM;
    }

    private void update(final float dt) {
        this.playerController.handleInput();
        if (!this.playerController.isPaused()) {
            this.handleSpawningFruit();
            this.stepWorld(dt);
            this.playerController.update(dt);
            this.enemies.update(dt);
            for (final FruitPowerUp fruit : this.fruits) {
                fruit.update(dt);
            }
            if (this.playerController.isDoubleJumpActive()) {
                this.hud.update(dt);
                if (!this.hud.isTimerOn()) {
                    this.setDoubleJumpAbility(false);
                }
            }
            this.cam.position.x = this.playerController.getBody().getPosition().x;
            this.cam.update();
            this.mapRenderer.setView(this.cam);
        }
    }

    private void stepWorld(final float delta) {
        this.accumulator += Math.min(delta, ACCUMULATOR_DEFAULT);
        if (this.accumulator >= WORLD_TIME_STEP) {
            this.accumulator -= WORLD_TIME_STEP;
            this.world.step(WORLD_TIME_STEP, WORLD_VEL_ITER, WORLD_POS_ITER);
        }
    }

    @Override
    public void render(final float delta) {
        this.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.mapRenderer.render();
        /*
         * BOX DEBUGGER IN CASE OF DEBUG. this.b2debug.render(this.world,
         * this.cam.combined);
         */
        this.game.getBatch().setProjectionMatrix(this.hud.getStage().getCamera().combined);
        this.game.getBatch().setProjectionMatrix(this.cam.combined);
        this.game.getBatch().begin();
        this.playerController.draw(this.game.getBatch());
        this.enemies.draw(this.game.getBatch());
        for (final FruitPowerUp fruit : this.fruits) {
            fruit.draw(this.game.getBatch());
        }
        this.game.getBatch().end();
        this.hud.getStage().draw();
        if (this.playerController.isPaused()) {
            this.sound.pauseGameSong();
            this.pauseTime += delta;
            if (this.pauseTime < TEXT_TIME) {
                this.drawBackground(BG1_X, BG1_Y, BG1_WIDTH, BG_HEIGHT);
                this.drawBackground(BG2_X, BG2_Y, BG2_WIDTH, BG_HEIGHT);
                this.drawBackground(BG3_X, BG3_Y, BG3_WIDTH, BG_HEIGHT);
                this.game.getBatch().begin();
                final BitmapFont font = new BitmapFont();
                font.draw(this.game.getBatch(), "GAME PAUSED", LABEL1_X, LABEL1_Y);
                font.draw(this.game.getBatch(), "PRESS SPACE TO RESUME", LABEL2_X, LABEL2_Y);
                font.draw(this.game.getBatch(), "PRESS ESCAPE TO GO TO THE MAIN MENU", LABEL3_X, LABEL3_Y);
                this.game.getBatch().end();
            } else {
                if (this.pauseTime > TEXT_TIME + BLANK_TIME) {
                    this.pauseTime = 0;
                }
            }
        } else {
            this.sound.playGameSong();
        }
    }

    private void drawBackground(final float x, final float y, final float width, final float height) {
        this.textBackground.begin(ShapeRenderer.ShapeType.Filled);
        this.textBackground.setColor(0, 0, 0, 1);
        this.textBackground.rect(x, y, width, height);
        this.textBackground.end();
    }

    @Override
    public void dispose() {
        this.map.dispose();
        this.mapRenderer.dispose();
        this.world.dispose();
        this.hud.getStage().dispose();
        /*
         * BOX DEBUGGER IN CASE OF DEBUG. this.b2debug.dispose();
         */
    }

    @Override
    public void resize(final int width, final int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void spawnOrange(final Pair<Float, Float> position) {
        this.orangesToSpawn.add(position);
    }

    @Override
    public void spawnMelon(final Pair<Float, Float> position) {
        this.melonsToSpawn.add(position);
    }

    @Override
    public void spawnCherry(final Pair<Float, Float> position) {
        this.cherriesToSpawn.add(position);
    }

    private void handleSpawningFruit() {
        if (!this.orangesToSpawn.isEmpty()) {
            final Pair<Float, Float> position = this.orangesToSpawn.remove(0);
            this.fruits.add(FruitBuilderImpl.newBuilder().selectFruitType(FruitType.ORANGE)
                    .chooseXPosition(position.getX()).chooseYPosition(position.getY()).selectScreen(this).build());
        }
        if (!this.cherriesToSpawn.isEmpty()) {
            final Pair<Float, Float> position = this.cherriesToSpawn.remove(0);
            this.fruits.add(FruitBuilderImpl.newBuilder().selectFruitType(FruitType.CHERRY)
                    .chooseXPosition(position.getX()).chooseYPosition(position.getY()).selectScreen(this).build());
        }
        if (!this.melonsToSpawn.isEmpty()) {
            final Pair<Float, Float> position = this.melonsToSpawn.remove(0);
            this.fruits.add(FruitBuilderImpl.newBuilder().selectFruitType(FruitType.MELON)
                    .chooseXPosition(position.getX()).chooseYPosition(position.getY()).selectScreen(this).build());
        }
    }

    @Override
    public void setDoubleJumpAbility(final boolean b) {
        this.playerController.getModel().setDoubleJump(b);
    }

    @Override
    public void addLife() {
        this.hud.addLife();
        this.playerController.getModel().addLife();
    }

    @Override
    public void removeLife() {
        this.hud.removeLife();
        this.playerController.getModel().removeLife();
    }

    @Override
    public void addScore(final Collidable entity) {
        this.hud.addScore(entity.getScore());
    }

    @Override
    public void setWinScreen() {
        this.game.setScreen(new WinScreen(this.game, this.hud.getScore(), this.sound));
    }

    @Override
    public void setGameOverScreen() {
        this.game.setScreen(new GameOverScreen(this.game, this.hud.getScore(), this.sound));
    }

    @Override
    public void setMenuScreen() {
        this.game.setScreen(new MainMenu(this.game, this.sound));
    }

    @Override
    public float getNinjaXPosition() {
        return this.playerController.getBody().getPosition().x;
    }

    @Override
    public TiledMap getMap() {
        return this.map;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    @Override
    public void show() {
        // unused
    }

    @Override
    public void pause() {
        // unused
    }

    @Override
    public void resume() {
        // unused
    }

    @Override
    public void hide() {
        // unused
    }
}
