package boxhead.controller.game;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import boxhead.controller.entities.AmmoController;
import boxhead.controller.entities.AmmoControllerImpl;
import boxhead.controller.entities.InputHandler;
import boxhead.controller.entities.PlayerController;
import boxhead.controller.entities.PlayerControllerImpl;
import boxhead.controller.entities.ShotController;
import boxhead.controller.entities.ShotControllerImpl;
import boxhead.controller.entities.ZombieController;
import boxhead.controller.entities.ZombieControllerImpl;
import boxhead.controller.level.LevelController;
import boxhead.controller.level.RoundController;
import boxhead.model.entities.gun.GunUpgradeManager;
import boxhead.model.score.Score;
import boxhead.model.score.ScoreImpl;
import boxhead.view.GameView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.util.Pair;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLevelImpl implements GameLevel{
	//Always power of 2 as size
	private static final int TILE_SIZE = 32;
		
	//Map dimensions
	private static final int MAP_WIDTH = 32;
	private static final int MAP_HEIGHT = 32;
		   
	private final ShotController shotController;
	private final InputHandler inputHandler;
	private final PlayerController playerController;
	private final ZombieController zombieController;
	private final RoundController roundController;
	private final AmmoController ammoController;
	private final LevelController map;
	private final GameView gameView;
	private final GunUpgradeManager gunUpgradeManager;
	private final Score score;
	
	public GameLevelImpl(final GameView view, final Game game) {
		final int scale;
		this.gameView = view;

		scale = (int) Math.ceil(gameView.getWidth() / 1000);
		this.map = new LevelController(MAP_WIDTH, MAP_HEIGHT, TILE_SIZE, scale);
		
		this.inputHandler = new InputHandler();
		this.playerController = new PlayerControllerImpl(this);

		this.zombieController = new ZombieControllerImpl(this);
		this.roundController = new RoundController(this);
		this.ammoController = new AmmoControllerImpl(this.map.getMap().getAmmoSpawnPoints());
		this.shotController = new ShotControllerImpl(this);

		this.playerController.getPlayer().setPosition(new Point2D(360, 360));
		this.playerController.getPlayer().setAmmoController(ammoController);

		this.gunUpgradeManager = new GunUpgradeManager(this.getGameView(), this.playerController.getPlayer());
		this.score = new ScoreImpl(this.gunUpgradeManager);
		this.zombieController.getZombieModel().linkScore(this.score);
		this.initHandlers(game.getScene());
		this.initializeModel();
	}
	
	/**
     * Used to add event handlers of the game.
     * 
     * @param root the scene.
     * 
     */
    private void initHandlers(final Scene root) {

        root.addEventHandler(KeyEvent.KEY_PRESSED, inputHandler.keyboard());
        root.addEventHandler(KeyEvent.KEY_RELEASED, inputHandler.keyboard());
        root.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
                if (event.getCode().equals(KeyCode.P)) {
                	if(GameState.state.equals(GameState.GameStateEnum.GAME)) {
                    	GameState.state = GameState.GameStateEnum.PAUSE;
                    	GameState.change = true;
                	} else if (GameState.state.equals(GameState.GameStateEnum.PAUSE)) {
                		GameState.state = GameState.GameStateEnum.GAME;
                		GameState.change = true;
                	}
                }
        });
    }

    private void initializeModel() {
        this.zombieController.getZombieModel().setWalls(this.getWalls());
        this.zombieController.getZombieModel().setSpawnPoints(this.map.getLevel().getZombieSpawnPoints());
        this.zombieController.getZombieModel().setPlayer(playerController.getPlayer());
        this.playerController.getPlayer().setWalls(this.getWalls());
        this.shotController.setWalls(this.getWalls());
    }
	
	@Override
	public void handle() {
		this.inputUpdate();
		this.gameUpdate();
		this.gameRender();
	}
	
	private void inputUpdate() {
		this.playerController.updateInput();
	}
	
	private void gameUpdate() {
		this.playerController.update();
		this.zombieController.update();
		this.roundController.update();
		this.shotController.update();
	}
	
	private void gameRender() {
		this.playerController.getPlayerView().render();
		final Set<Pair<Point2D, Image>> res = new HashSet<>();
		this.gameView.clear();
		
		this.gameView.completeRender(this.map.render());

	
		res.add(new Pair<>(this.playerController.getPlayer().getPosition(), this.playerController.getPlayerView().getImageView().getImage()));
		res.addAll(this.zombieController.getZombieView().getSprites());
		this.gameView.completeRender(res);
		
		this.ammoController.getAmmoView().forEach((a, v) -> {
			this.gameView.render(v.getSprite().getImage(), a.getPosition());
		});
		
		this.shotController.getShots().forEach((s, v) -> {
			this.gameView.render(v.getSprite().getImage(), s.getPosition());
		});
		this.gameView.renderAmmoLabel(String.valueOf(this.playerController.getPlayer().getCurrentGun().getCurrentAmmo()));
		this.gameView.renderHPLabel(String.valueOf(this.playerController.getPlayer().getHealth()));
		this.gameView.renderKillStreak(String.valueOf(this.score.getStreak()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final InputHandler getInputHandler() {
		return this.inputHandler;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final PlayerController getPlayerController() {
		return this.playerController;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ZombieController getZombieController() {
		return this.zombieController;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ShotController getShotController() {
		return this.shotController;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final GameView getGameView() {
		return this.gameView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Point2D getPlayerPos() {
		return this.playerController.getPlayer().getPosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<BoundingBox> getWalls() {
		return this.map.getLevel().getWalls().stream().map(w -> w.getBoundingBox()).collect(Collectors.toSet());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPlayerAlive() {
		return this.playerController.getPlayer().isAlive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Score getScore() {
		return this.score;
	}

}
