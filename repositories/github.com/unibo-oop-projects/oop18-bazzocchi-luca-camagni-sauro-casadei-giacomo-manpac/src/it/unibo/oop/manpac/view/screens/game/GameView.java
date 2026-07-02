package it.unibo.oop.manpac.view.screens.game;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import it.unibo.oop.manpac.controller.ControllerObserver;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.utils.PairImpl;
import it.unibo.oop.manpac.utils.SoundManager;
import it.unibo.oop.manpac.utils.UtilsForUI;
import it.unibo.oop.manpac.utils.SoundManager.SoundName;
import it.unibo.oop.manpac.view.input.PacmanInputProcessor;
import it.unibo.oop.manpac.view.maze.Collision;
import it.unibo.oop.manpac.view.maze.MazeCreator;
import it.unibo.oop.manpac.view.maze.MazeCreatorImpl;
import it.unibo.oop.manpac.view.maze.entities.EntitiesTextureManager;
import it.unibo.oop.manpac.view.maze.entities.EntitiesTextureManagerImpl;
import it.unibo.oop.manpac.view.maze.entities.Pacman;
import it.unibo.oop.manpac.view.maze.entities.PhantomImpl;
import it.unibo.oop.manpac.view.screens.menu.ObservableScreen;
import it.unibo.oop.manpac.view.screens.menu.ScreenObserver;
import it.unibo.oop.manpac.view.screens.menu.ScreensMessenger;

/**
 * The graphics of the application, managed with LibGDX.
 */
public final class GameView implements ObservableScreen, ScreensMessenger.TheGame, ActionsResultProcessorObservable {
    private static final int HEIGHT_DEFAULT = 350;
    private static final int WIDTH_DEFAULT = 230;

    private static final float POWER_PILL_DURATION = 6.5f;
    //threshold to alert you of the end of power mode
    private static final float ALERT_TIME_POWER_ENDING = 2.0f;
    // time of spawn of phantom in seconds
    private static final int SPAWN_TICK_PHANTOM = 3; 

    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8;
    private static final int POSITIONITERATIONS = 3;

 // volume in the game
    private static final int VOLUME_GAME = 25; 
    private static final int VOLUME_PHANTOM_WAIL = 8;

    private static final float PACMANEFFECTOFFSET = (UtilsForUI.MOBILE_ENTITIES_RADIUS + 1);

    private Optional<ControllerObserver.OnlyGame> controller;
    private ScreenObserver screenObserver;
    private final ActionsResultProcessorObserver helper;

    private final Viewport gamePort;
    private final OrthogonalTiledMapRenderer renderer;
    private final Stage stage;
    private final SpriteBatch batch;

    private Body bodyToDestroy;
    private Fixture fixtureToDestroy;
    private Cell cellToDestroy;
    private final World world;
    private boolean removeFromMaze;

    private final Pacman pacman;
    private final List<PhantomImpl> phantoms;
    private Entity lastPacmanCollision;
    private final Map<Entity, Boolean> checkPhantomCollision;

    private boolean setPhantomToSpawn;
    private boolean lifeLost;

    private final Hud hud;
    private int highScore;
    private int currentScore;

    private float powerDuration;
    private boolean powerPill;
    //true when the sound of the power mode term has not yet been played
    private boolean powerEndingSound;

    private boolean pacmanEffectTp;
    private final float leftPortalX;
    private final float rightPortalX;

    private final int allPills;

    private final EntitiesTextureManager<Entity, Directions> textureManager;
    private final Map<Entity, Sprite> phantomsSprite;
    private final Sprite pacmanSprite;

    private final ExecutorServiceGameImpl scheduledExecutor;
    private final Map<Entity, Boolean> phantomOut;

    private boolean start;

    /**
     * Constructor for GameView.
     */
    public GameView() {
        this.controller = Optional.empty();
        this.helper = new ActionsResultProcessorImpl(this);

        final OrthographicCamera gamecam = new OrthographicCamera();
        this.gamePort = new FitViewport(WIDTH_DEFAULT, HEIGHT_DEFAULT, gamecam);
        this.stage = new Stage(this.gamePort);
        this.batch = new SpriteBatch();

        this.hud = new Hud(this.stage.getWidth(), this.stage.getHeight(), this.batch);
        this.batch.setProjectionMatrix(this.hud.getMatrix4FromCam());

        this.world = new World(new Vector2(0, 0), true);
        this.world.setContactListener(new Collision(this));
        final TiledMap map = new TmxMapLoader().load("TileMap/levelmap.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.renderer.setView(gamecam);

        final MazeCreator creator = new MazeCreatorImpl(this.world, map);
        this.leftPortalX = creator.getLeftPortalX();
        this.rightPortalX = creator.getRightPortalX();
        this.pacman = creator.getPacman();
        this.phantoms = creator.getPhantoms();
        this.allPills = creator.getTotalPills();

        //set the sprites of entity
        this.textureManager = new EntitiesTextureManagerImpl();
        final Texture pacmanTexture = this.textureManager
                .getEntityTexture(new PairImpl<>(Entity.PACMAN, Directions.STOP));
        this.pacmanSprite = new Sprite(pacmanTexture);
        this.pacmanSprite.setSize(10, 10);

        // maps containing phantoms sprite and which phantom can exit the cell
        this.phantomsSprite = new EnumMap<>(Entity.class);
        this.phantomOut = new EnumMap<>(Entity.class);
        this.phantoms.forEach(phantom -> {
            final Sprite sprite = new Sprite(
                    this.textureManager.getEntityTexture(new PairImpl<>(phantom.getName(), Directions.RIGHT)));
            sprite.setSize(UtilsForUI.PHANTOM_SPRITE_SIZE, UtilsForUI.PHANTOM_SPRITE_SIZE);
            this.phantomsSprite.put(phantom.getName(), sprite);
            this.phantomOut.put(phantom.getName(), false);
        });

        // checkPhantomCollision is used to check which phantom has collided with a wall
        this.checkPhantomCollision = new EnumMap<>(Entity.class);
        this.phantoms.forEach(phantom -> {
            this.checkPhantomCollision.put(phantom.getName(), false);
        });

        // Executor Service for thread management, it is used to start the game with an
        // initial delay and to recall the phantoms out every few seconds
        this.scheduledExecutor = new ExecutorServiceGameImpl(this, this.phantoms);

        this.scheduledExecutor.createSingleThreadExecutor(SoundName.NEW_GAME.getDuration());

        this.start = false;
        this.powerEndingSound = false;
    }

    /**
     * Method called when the game can start.
     */
    public void startGame() {
        if (!this.start) {
            Gdx.input.setInputProcessor(new PacmanInputProcessor(this));
            this.phantoms.forEach(phantom -> controllerOrThrows().phantomIsCollidingToEntity(phantom.getName(), Entity.WALL));
            this.scheduledExecutor.createScheduledThreadPool(0, SPAWN_TICK_PHANTOM);
            playPhantomWail(SoundName.PHANTOM_WAIL);
            this.start = true;
        }
    }

    @Override
    public void show() {
        // Notify the controller of the number of pills in the game.
        controllerOrThrows().requestSetNumberPills(this.allPills);
        this.updateScore();
        this.updateLives();

        SoundManager.getSoundManager().stopAllSounds().setVolume(VOLUME_GAME).play(SoundName.NEW_GAME);
    }

    /**
     * Updates everything connected to graphics frame by frame.
     * 
     * @param delta the time interval between two render calls
     */
    public void render(final float delta) {
        this.world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

        if (this.start) {
            update(delta);
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.renderer.render();
        this.hud.draw();

        this.pacmanSprite.setPosition(this.pacman.getBody().getPosition().x - UtilsForUI.MOBILE_ENTITIES_RADIUS,
                this.pacman.getBody().getPosition().y - UtilsForUI.MOBILE_ENTITIES_RADIUS);

        this.batch.begin();
        this.pacmanSprite.draw(this.batch);

        // Cycle to set the texture at the center of each phantom as it moves
        for (final Map.Entry<Entity, Sprite> entry : this.phantomsSprite.entrySet()) {
            this.phantoms.stream().filter(phantom -> phantom.getName() == entry.getKey()).forEach(phantom -> {
                entry.getValue().setPosition(phantom.getPosition().x - UtilsForUI.MOBILE_ENTITIES_RADIUS,
                        phantom.getPosition().y - UtilsForUI.MOBILE_ENTITIES_RADIUS);
                entry.getValue().draw(this.batch);
            });
        }
        this.batch.end();
    }

    /**
     * Updates every entity in the maze and check if some events have happened.
     * 
     * @param deltaTime time between two update calls
     */
    private void update(final float deltaTime) {
        // make manpac move
        this.pacman.getBody().setLinearVelocity(vectorScal(this.pacman.getMovement(), Gdx.graphics.getDeltaTime()));

        // make phantoms move
        this.phantoms.forEach(phantom -> {
            phantom.getBody().setLinearVelocity(vectorScal(phantom.getMovement(), Gdx.graphics.getDeltaTime()));

        });
        if (lifeLost) {
            // when the pacman loses a life all the phantoms must reposition themselves
            // inside the cell
            final List<Entity> list = this.scheduledExecutor.getQueue();
            this.phantoms.stream().filter(p -> !list.contains(p.getName())).forEach(p -> {
                this.scheduledExecutor.addToTheQueue(p.getName());
                p.setPositionToSpawnPoint();
            });
            this.pacman.setPositionToSpawnPoint();
            this.pacman.setDirection(Directions.STOP);
            this.changePacmanSkin(Directions.STOP);
            this.updateLives();
            this.lifeLost = false;
        }
        if (setPhantomToSpawn) {
            // returns the last phantom eaten to his spawn point and adds it to the
            // executor's queue to wait to exit from the cell
            this.phantoms.stream().filter(p -> p.getName().equals(this.lastPacmanCollision)).forEach(p -> {
                p.setPositionToSpawnPoint();
                this.scheduledExecutor.addToTheQueue(p.getName());
            });
            this.phantomJustEaten(false);
        }
        if (this.pacmanEffectTp) {
            if (this.leftPortalX + this.pacman.getPosition().x < WIDTH_DEFAULT / 2) {
                this.pacman.setPosition(new Vector2(this.rightPortalX - PACMANEFFECTOFFSET, this.pacman.getPosition().y));
            } else {
                this.pacman.setPosition(new Vector2(this.leftPortalX + PACMANEFFECTOFFSET, this.pacman.getPosition().y));
            }
            this.pacmanEffectTp = false;
        }
        // if the getValue equals true, there was a collision of that specific phantom
        // with a wall
        for (final Map.Entry<Entity, Boolean> entry : this.checkPhantomCollision.entrySet()) {
            if (entry.getValue()) {
                this.adjustPhantomPosition(entry.getKey());
            }
        }
        // cycle to let out the phantoms that are inside the cell
        for (final Map.Entry<Entity, Boolean> entry : this.phantomOut.entrySet()) {
            if (entry.getValue()) {
                this.phantoms.stream().filter(p -> p.getName() == (entry.getKey())).forEach(p -> p.goOut());
                entry.setValue(false);
            }
        }
        if (this.removeFromMaze) {
            this.bodyToDestroy.destroyFixture(this.fixtureToDestroy);
            this.cellToDestroy.setTile(null);
            this.removeFromMaze = false;
        }
        if (this.powerPill) {
            this.powerDuration = this.powerDuration - deltaTime;
            if (this.powerDuration <= 0) {
                playPhantomWail(SoundName.PHANTOM_WAIL);
                this.powerPill = false;
                controllerOrThrows().stopTimerPower();
                // when the power pill ends the phantoms are no longer afraid and their normal
                // texture returns
                this.phantoms.forEach(p -> {
                    p.setFear(false);
                    this.changePhantomSkin(p.getName(), p.getDirection());
                });
            } else {
                if (this.powerEndingSound && this.powerDuration <= ALERT_TIME_POWER_ENDING) {
                    this.powerEndingSound = false;
                    changePhantomSkinFrightened();
                    playPhantomWail(SoundName.POWER_ENDING);
                }
            }
        }
    }

    /**
     * A collision between two entities has happened and will be processed.
     * 
     * @param collider  The entity that collided with something
     * @param colliding The entity that collider collided with
     */
    public void collisionEvent(final Entity collider, final Entity colliding) {
        if ((collider.equals(Entity.PACMAN))) {
            controllerOrThrows().pacmanIsCollidingToEntity(colliding);
            if (colliding.equals(Entity.BLINKY) || colliding.equals(Entity.CLYDE) || colliding.equals(Entity.PINKY)
                    || colliding.equals(Entity.INKY)) {
                this.lastPacmanCollision = colliding;
            }
        } else {
            if (colliding.equals(Entity.WALL)) {
                controllerOrThrows().phantomIsCollidingToEntity(collider, colliding);
            }
        }
    }

    /**
     * The tile and its content will be deleted in the next update cycle.
     * 
     * @param body    The Box2D body that will be destroyed
     * @param fixture The Box2D fixture that will be destroyed
     * @param cell    The TiledMapLayerCell that will be destroyed
     */
    public void setToDestroy(final Body body, final Fixture fixture, final Cell cell) {
        this.bodyToDestroy = body;
        this.fixtureToDestroy = fixture;
        this.cellToDestroy = cell;
    }

    @Override
    public void readyToDestroy() {
        this.updateScore();
        this.removeFromMaze = true;
    }

    /**
     * {@inheritDoc}
     */
    public void enablePower() {
        this.powerDuration = POWER_PILL_DURATION;
        this.powerPill = true;
        this.powerEndingSound = true;

        // when the phantoms are scared, fear is set and the texture is changed
        this.phantoms.forEach(p -> p.setFear(true));
        changePhantomSkinFrightened();

        playPhantomWail(SoundName.POWER_START);
    }

    /**
     * {@inheritDoc}
     */
    public void pacmanEffect() {
        this.pacmanEffectTp = true;
    }

    /**
     * Stops executor execution.
     */
    public void stopExecutorService() {
        this.scheduledExecutor.shutdownScheduledExecutor();
    }

    /**
     * Resize the screen if the window size is modified.
     * 
     * @param width  the new screen width
     * @param height the new screen height
     */
    @Override
    public void resize(final int width, final int height) {
        this.gamePort.update(width, height);
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setObserver(final ScreenObserver screenObserver) {
        this.screenObserver = screenObserver;
    }

    @Override
    public void startControllerObserving(final ControllerObserver controller) {
        if (Objects.isNull(controller)) {
            throw new IllegalArgumentException("The controller passed as input in startControllerObserving is null!");
        }
        this.controller = Optional.of((ControllerObserver.OnlyGame) controller);
    }

    @Override
    public void stopControllerObserving() {
        this.controller = Optional.empty();
    }

    /**
     * Called when a new direction is requested for the pacman.
     * 
     * @param newDirection The new direction
     */
    public void pacmanChangeDirection(final Directions newDirection) {
        controllerOrThrows().pacmanInputDirection(newDirection);
        this.changePacmanSkin(newDirection);
    }

    @Override
    public void sendScore(final int currentScore, final int highScore) {
        this.highScore = highScore;
        this.currentScore = currentScore;

        this.hud.setHighScore(this.highScore);
        this.hud.setCurrentScore(this.currentScore);
    }

    @Override
    public void sendPacManAction(final Action action) {
        this.helper.pacmanCollisionHelper(action);
    }

    @Override
    public void lifeJustLost(final boolean lost) {
        this.lifeLost = lost;
    }

    @Override
    public void phantomJustEaten(final boolean eaten) {
        this.updateScore();
        this.setPhantomToSpawn = eaten;
    }

    @Override
    public void setGameOver(final boolean win) {
        SoundManager.getSoundManager().stopAllSounds();
        this.updateScore();
        this.screenObserver.setGameOverScreen(win, this.currentScore, this.highScore);
    }

    /**
     * Request ScreenObserver to start the MainMenu.
     */
    public void setMainMenu() {
        SoundManager.getSoundManager().stopAllSounds();
        controllerOrThrows().requestResetGame();
        this.screenObserver.setMainMenuScreen();
    }

    @Override
    public void setPacmanDirection(final Directions direction) {
        this.pacman.setDirection(direction);
    }

    @Override
    public void sendPacManDirectionWithAction(final Action directionAction) {
        this.helper.pacmanChangeDirectionHelper(directionAction);
    }

    @Override
    public void sendPhantomAction(final Entity phantomName, final Action action) {
        this.helper.phantomChangeDirectionHelper(phantomName, action);
        // it is used to know which phantom had a collision with a wall
        this.checkPhantomCollision.put(phantomName, true);
    }

    @Override
    public void sendPacManLives(final int lives) {
        this.hud.setPacManLives(lives);
    }

    private void updateScore() {
        // requires the Controller to update
        controllerOrThrows().requestBothScore();
    }

    /**
     * Called when a phantom can exit the cell.
     * 
     * @param phantomName The name of the phantom that can exit
     */
    public void phantomExit(final Entity phantomName) {
        this.phantomOut.put(phantomName, true);
    }

    @Override
    public void updatePhantomTexture(final Entity phantomName, final Directions direction) {
        this.phantoms.stream().filter(phantom -> phantom.getName().equals(phantomName))
                .peek(phantom -> phantom.setDirection(direction)).filter(p -> !p.isScared())
                .forEach(p -> this.changePhantomSkin(p.getName(), direction));
    }

    // method to prevent bugs by adjusting the position of a phantom when there is a
    // collision with a wall
    private void adjustPhantomPosition(final Entity phantomName) {
        this.phantoms.stream().filter(p -> p.getName().equals(phantomName)).forEach(phantom -> {
            switch (phantom.getSecondLastDirection()) {
            case UP:
                phantom.setPosition(phantom.getPosition().add(0, -1));
                break;
            case DOWN:
                phantom.setPosition(phantom.getPosition().add(0, 1));
                break;
            case LEFT:
                phantom.setPosition(phantom.getPosition().add(1, 0));
                break;
            case RIGHT:
                phantom.setPosition(phantom.getPosition().add(-1, 0));
                break;
            default:
                break;
            }
        });

        this.checkPhantomCollision.put(phantomName, false);
    }

    private Vector2 vectorScal(final Vector2 vector, final float scal) {
        return new Vector2(vector.x * scal, vector.y * scal);
    }

    // It is used when the lives in the Hud need to be updated
    private void updateLives() {
        // requires the Controller to update
        controllerOrThrows().requestPacManLives();
    }

    private void changePacmanSkin(final Directions direction) {
        this.pacmanSprite.setTexture(this.textureManager.getEntityTexture(new PairImpl<>(Entity.PACMAN, direction)));
    }

    private void changePhantomSkin(final Entity entity, final Directions direction) {
        this.phantomsSprite.get(entity)
                .setTexture(this.textureManager.getEntityTexture(new PairImpl<>(entity, direction)));
    }

    //Call this when you need to put the skin of ghosts in fear
    private void changePhantomSkinFrightened() {
        final Texture tx;
        if (this.powerEndingSound) {
            tx = this.textureManager.getFrightenedPhantomTexture();
        } else {
            tx = this.textureManager.getFrightenedEndPhantomTexture();
        }

        this.phantomsSprite.entrySet().forEach(phantom -> phantom.getValue().setTexture(tx));
    }

    //This method is used to change the wail of ghosts according to situations (normal, frightened)
    private void playPhantomWail(final SoundName soundWail) {
        final int actualVolume = SoundManager.getSoundManager().getVolume();

        SoundManager.getSoundManager().stopAllSounds()
                    .setVolume(VOLUME_PHANTOM_WAIL)
                    .playWithLoop(soundWail)
                    .setVolume(actualVolume);
    }

    /**
     * It is used to check if the controller has been set; in the positive case it
     * returns it, in the negative case the IllegalStateException (with message) is
     * thrown for the absence of it.
     */
    private ControllerObserver.OnlyGame controllerOrThrows() throws IllegalStateException {
        return this.controller.orElseThrow(
                () -> new IllegalStateException(ControllerObserver.ERROR_INITIALIZE + " on " + this.getClass()));
    }

    @Override
    public void dispose() {
        stopExecutorService();
        this.world.dispose();
        this.batch.dispose();
        this.hud.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }



}
