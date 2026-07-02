package controller;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.gameStatusManager.ControllerGameStatusManager;
import controller.gameStatusManager.GameStatusManagerImpl;
import controller.gameStatusManager.ViewGameStatusManager;
import model.Model;
import model.ModelImpl;
import model.entitiesutil.MappedEntity;
import view.game.GameView;
import view.game.GameViewImpl;

/**
 * Implementation of {@link GameController}
 */
public class GameControllerImpl implements GameController, ViewGameController {

	private final int FPS = 60;
	private final int DEL = 1000/FPS;
	private final ControllerGameStatusManager stateGameManager;
	private final Model model;
	private final GameView view;

	private ScheduledExecutorService loop;
	private int frames;

	/**
	 * Implementation of {@link GameController}
	 */
	public GameControllerImpl() {
		this.stateGameManager = new GameStatusManagerImpl();
		this.model = new ModelImpl(this);
		this.view = new GameViewImpl(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNewGame() {
		if(!this.isRunning()) {
			this.loop = Executors.newScheduledThreadPool
					(Runtime.getRuntime().availableProcessors()-1);
			this.frames = 0;
			this.model.restartGame();
			this.view.clearKeyMap();
			this.stateGameManager.setStart();
			this.loop.scheduleWithFixedDelay(()-> gameLoop(), DEL, DEL, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * Stop the thread that updates the game
	 */
	private void stop() {
		if(this.isRunning()) {
			this.loop.shutdownNow();
			this.stateGameManager.setStop();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRunning() {
		return this.stateGameManager.getGameStatus().equals(GameStatus.RUNNING)
				&& !this.loop.isShutdown() && !this.loop.isTerminated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GameViewImpl getView() {
		return (GameViewImpl) this.view;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<MappedEntity> getLevelEntities() {
		return this.model.getMappedEntities();
	}

	/**
	 * Game Loop
	 */
	private void gameLoop() {
		this.stateGameManager.isGamePaused();
		switch(this.stateGameManager.getGameStatus()) {
			case STOPPED:
				this.stateGameManager.setResume();
				this.stop();
				break;
			case RESTARTED:
				this.frames = 0;
				this.model.restartGame();
				this.stateGameManager.setResume();
				break;
			case RESUMED:
				this.stateGameManager.setResume();
				break;
			default:
				break;
		}
		this.updateGame();
		this.render();
	}

	/**
	 * Update {@link GenericEntity}s position
	 */
	private void updateGame() {
		this.model.processInput(this.view.getEvents(), frames);
		this.model.updateEntityLevel(frames++);	//Update entities's position
	}

	/**
	 * Update the game view
	 */
	private void render() {
		this.view.updateGui(this.getLevelEntities());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWindowWidth() {
		return this.view.getWidth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWindowHeight() {
		return this.view.getHeight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gameOver() {
		this.view.openGameOver();
		this.stop();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void victory() {
		this.view.openVictoryScene();
		this.stop();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ViewGameStatusManager getViewStatusManager() {
		return (ViewGameStatusManager) this.stateGameManager;
	}

	@Override
	public int getScore() {
		return this.model.getScore();
	}
}
