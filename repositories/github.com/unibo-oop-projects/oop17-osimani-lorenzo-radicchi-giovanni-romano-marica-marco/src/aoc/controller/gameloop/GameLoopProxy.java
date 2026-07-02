package aoc.controller.gameloop;

import java.util.Optional;

/**
 * This interface contains all the methods used to communicate from outsider objects to the GameLoop;
 * once these methods are called, the Controller delegates the real actions to the functions in the GameLoop.
 */
public interface GameLoopProxy {
    
    /**
     * This method ends the GameLoop.
     */
    public void end();
    
    /**
     * This method pauses the GameLoop.
     * @throws Exception 
     * 			if the GameLoop is not running.
     */
    public void pause() throws Exception;
    
    /**
     * This method resumes the GameLoop from a pause.
     * @throws Exception 
     * 			if the GameLoop is not paused.
     */
    public void proceed() throws Exception;
    
    /**
     * This method returns the current Status of the GameLoop Thread
     * 
     * @return current GameLoopStatus
     */
    public GameLoop.Status getStatus();
    
    /**
     * This method returns the index of the current
     * level that it's being played.
     * @return the index
     */
    public Optional<Integer> getLevel();
}
