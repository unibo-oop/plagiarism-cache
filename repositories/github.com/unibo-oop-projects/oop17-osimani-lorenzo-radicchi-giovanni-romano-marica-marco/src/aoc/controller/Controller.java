package aoc.controller;

import aoc.controller.datamanager.DataManager;
import aoc.controller.gameloop.GameLoop;
import aoc.controller.gameloop.GameLoop.Status;
import aoc.controller.gameloop.GameLoopInterface;
import aoc.controller.gameloop.GameLoopProxy;

import java.util.Optional;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;

/**
 * This class represents the main object Controller,using the Singleton Pattern.
 * It contains a reference to the eventual GameLoop.
 */
public class Controller implements GameLoopProxy {

    private final DataManager dataManager;
    private Optional<GameLoopInterface> gl;
    private int fps;
    
    /**
     * It contains the SINGLETON, initialized at first use.
     */
    private static class LazyHolder {
	/**
	 * Contains the reference to the Singleton.
	 */
	private static final Controller SINGLETON = new Controller();
    }
    
    /**
     * Controller Constructor.
     */
    private Controller() {
    	this.fps = GameConstants.FPS_30;
    	this.dataManager = DataManager.getDataManager();
    	this.gl = Optional.empty();
    }
    
    /**
     * Get the reference to the SINGLETON.
     * @return the SINGLETON
     */
    public static Controller get() {
	return LazyHolder.SINGLETON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void end() {
	this.applyMethod("end");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
	this.applyMethod("pause");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void proceed() {
	this.applyMethod("proceed");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getStatus() {
	try {
	    checkCondition(gl.isPresent(), "No level is being played");
	    return gl.get().getStatus();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Optional<Integer> index, final GameSceneRender gameview) {
	try {
	    if (index.isPresent()) {
		checkCondition(index.get() > 0 &&
		        index.get() <= GameConstants.N_LEVELS, "Level requested doesn't exist");
	    }
	    final GameLoop temp = new GameLoop(index, gameview);
	    gl = Optional.of(temp);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * Returns the current setting for the FPS.
     * @return int
     */
    public int getFPS() {
	return this.fps;
    }

    /**
     * Sets the FPS.
     * @param fps
     *          The desired setting.
     */
    public void setFPS(final int fps) {
	try {
	    checkCondition(GameConstants.FPS_SETTINGS.contains(fps), "This option isn't available");
	    this.fps = fps;
	 } catch (Exception e) {
	     e.printStackTrace();
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getLevel() {
	try {
	    checkCondition(gl.isPresent(), "No level is being played");
	    return gl.get().getLevel();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return Optional.empty();
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
    private void checkCondition(final boolean supplier, final String message) throws IllegalArgumentException {
	if (!supplier) {
	    throw new IllegalArgumentException(message);
	}
    }
    
    /**
     * Calls a method on the GameLoop object if present.
     * @param methodName
     *          the name of the method of GameLoopInterface
     */
    private void applyMethod(final String methodName) {
	try {
	    checkCondition(gl.isPresent(), "No level is being played");
	    GameLoop.class.getMethod(methodName).invoke(gl.get());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    
    
}
