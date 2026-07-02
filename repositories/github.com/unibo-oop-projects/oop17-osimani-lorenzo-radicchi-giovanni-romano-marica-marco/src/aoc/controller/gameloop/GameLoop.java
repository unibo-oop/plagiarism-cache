package aoc.controller.gameloop;

import java.util.List;
import java.util.Optional;
import aoc.controller.Controller;
import aoc.controller.GameConstants;
import aoc.controller.datamanager.DataManager;
import aoc.model.Model.GameStatus;
import aoc.model.Model;
import aoc.model.Model.ShootingStyle;
import aoc.model.ModelImpl;
import aoc.model.entity.EntityInterface;
import javafx.application.Platform;
import aoc.utilities.Direction;
import aoc.view.gameview.GameSceneRender;
import aoc.utilities.Images;


public class GameLoop extends Thread implements GameLoopInterface {
    
    public enum Status {
	
	/**
	 * The GameLoop is running.
	 */
	RUNNING,
	
	/**
	 * The GameLoop is paused
	 */
	PAUSED,
	
	/**
	 * The GameLoop is ready to run.
	 */
	READY,
	
	/**
	 * The GameLoop has been killed (can't run anymore).
	 */
	KILLED;
    }
    
    private final static double TIME_PER_UPDATE = 1000 / GameConstants.UPDATES_PER_SECOND;
    
    private final static double UPDATES_PER_FRAME = GameConstants.UPDATES_PER_SECOND/Controller.get().getFPS();
    
    private Status status;
    
    private Model model;
    
    private long updateTime;
    
    private DataManager dataManager;

    private GameSceneRender gameview;
    
    private Controller controller;
    
    private int counter = 0;
    
    public GameLoop(final Optional<Integer> index, final GameSceneRender gameview) {
    	super();
    	this.model = new ModelImpl(index);
    	this.gameview = gameview;
    	this.dataManager = DataManager.getDataManager();
    	this.controller = Controller.get();
    	
    	this.status = Status.READY;
    	this.start();
    }
    
    @Override
    public synchronized void end() {
       	this.status = Status.KILLED;
    }

    @Override
    public synchronized void pause() throws Exception {
      	this.checkCondition(this.status == Status.RUNNING, "The GameLoop is not running");
      	this.status = Status.PAUSED;
    }

    @Override
    public synchronized void proceed() throws Exception {
    	this.checkCondition(this.status == Status.PAUSED, "The GameLoop is not paused");
    	this.status = Status.RUNNING;
    }

    @Override
    public Status getStatus() {
    	return this.status;
    }

    @Override
    public void run() {
    	this.status = Status.RUNNING;
    	while(this.getStatus() == Status.RUNNING || this.getStatus() == Status.PAUSED) {
    		this.updateTime = System.currentTimeMillis();
    		this.manageInputs();
    		if(this.getStatus() != Status.PAUSED) {
    			this.update();
    			this.counter++;
    			if(counter >= UPDATES_PER_FRAME) {
    			    counter = 0;
    			    this.render(model.getEntityList());
    			}
    			this.waitNextUpdate();
    		}
    	}
    	try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public Optional<Integer> getLevel() {
	return Optional.of(model.getCurrentLevel());
    }
    
    protected void waitNextUpdate() {
    	long time = System.currentTimeMillis() - this.updateTime;
    	if (time < GameLoop.TIME_PER_UPDATE) {
    		try {
				Thread.sleep((long) (GameLoop.TIME_PER_UPDATE - time));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }

	protected void render(final List<EntityInterface> entities) {
    		final Runnable render = () -> {
    		gameview.init();
    		entities.forEach(e -> {
    			switch (e.getName()) {
				case "MOTHER":
					gameview.draw(Images.MOTHER.getImage(), e.getPosition());
					break;
				case "DUMB_CHILD":
					gameview.draw(Images.DUMB_CHILD.getImage(), e.getPosition());
					break;
				case "FAT_CHILD":
					gameview.draw(Images.FAT_CHILD.getImage(), e.getPosition());
					break;
				case "NERD_CHILD":
					gameview.draw(Images.NERD_CHILD.getImage(), e.getPosition());
					break;
				case "ATHLETIC_CHILD":
					gameview.draw(Images.ATHLETIC_CHILD.getImage(), e.getPosition());
					break;
				case "RICH_CHILD":
					gameview.draw(Images.RICH_CHILD.getImage(), e.getPosition());
					break;
				case "BASIC_SLIPPER":
					gameview.draw(Images.SLIPPER.getImage(), e.getPosition());
					break;
				default:
					break;
				}
    		});
    	};
    	Platform.runLater(render);
    }
    
    protected void update() {
    	this.model.update();
    	if(this.model.getGameStatus() != GameStatus.PLAYING) {
    		if(this.model.getGameStatus() == GameStatus.WON) {
    		    if(this.dataManager.getProgress() == this.model.getCurrentLevel()
    			&& this.model.getCurrentLevel() < GameConstants.N_LEVELS) {
    				this.dataManager.updateProgress();
    		    }
    		    Platform.runLater(() -> gameview.won());
    		} else if (this.model.getGameStatus() == GameStatus.LOST) {
    		    Platform.runLater(() -> gameview.lost());
    		}
    		this.end();
    	}
    }

    protected void manageInputs() {
	this.gameview.getInput().forEach(input -> {switch (input) {
	case UP:
		this.model.moveMother(Direction.UP);
		break;
	case DOWN:
		this.model.moveMother(Direction.DOWN);
		break;
	case SHOT:
		this.model.shoot(ShootingStyle.SINGLE);
		break;
	case RAPID_SHOT:
		this.model.shoot(ShootingStyle.RAPID);
		break;
	default:
		break;
	}});
    }

    /**
     * This method check if a condition is true;
     * if not it throws an Exception with the message passed as argument.
     * @param supplier
     *          the condition to check.
     * @param message
     *          the String of the eventual error
     * @throws IllegalArgumentException
     *          It is thrown in case the supplier is false.
     */
    private void checkCondition(final boolean supplier,final String message) throws IllegalArgumentException {
    	if (!supplier) {
    		throw new IllegalArgumentException(message);
    	}
    }
}