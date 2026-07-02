package aoc.model.level;

import aoc.model.Model.GameStatus;
import aoc.model.WorldConstants;
import aoc.model.entity.EntityInterface;
import aoc.model.entity.child.ChildInterface;
import aoc.model.entity.slipper.SlipperInterface;
import aoc.model.level.spawner.Spawner;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This abstract class implements the general method of a level.
 */
public abstract class BasicLevel implements Level {
    
    private GameStatus status;
    private final List<EntityInterface> entities = new LinkedList<>();
    private int currentLevel;
    private Spawner spawner;
    
    /**
     * Constructor for BasicLevel.
     */
    public BasicLevel() {
	this.status = GameStatus.PLAYING;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public GameStatus getGameStatus() {
    	return this.status;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
    	if (this.status.equals(GameStatus.PLAYING)) {
        	this.entities.removeIf(e -> !e.isAlive()
        	        || e.getPosition().getX() > WorldConstants.CELL_WIDTH * WorldConstants.WORLD_WIDTH && e instanceof SlipperInterface);
            	this.entities.forEach(x -> x.update());
    		if (spawner.readyToSpawn()) {
    			entities.add(spawner.entityToSpawn());
    		}
    		boolean res = entities.stream().noneMatch(e -> (e instanceof ChildInterface)
    		        && e.getPosition().getX() <= WorldConstants.GAMEOVER_LINE);
    		if (!res) {
    			this.status = GameStatus.LOST;
    		} else if (spawner.isEmpty()
    		        && this.entities.stream().noneMatch(e -> e instanceof ChildInterface)) {
    			this.win();
    		} else {
    			spawner.tick();
    		}
    	}
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntityInterface> getEntityList() {
    	return Collections.unmodifiableList(this.entities) ;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentLevel() {
    	return this.currentLevel;
    }
    
    /**
     * This method is called when the current level is won, and it contains the
     * instructions to execute next to that event.
     */
    protected abstract void win();
    
    /**
     * Set the state of the current level.
     * @param status
     *          GameStatus to set
     */
    protected void setStatus(final GameStatus status) {
    	this.status = status;
    }
    
    /**
     * Set the spawner of the current level.
     * @param spawner
     *          Spawner to set
     */
    protected void setSpawner(final Spawner spawner) {
    	this.spawner = spawner;
    }
    
    /**
     * Set the index of the current level.
     * @param spawner
     *         index to set
     */
    protected void setCurrentLevel(final int index) {
	this.currentLevel = index;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void notify(final Object subject)  {
    	if (subject instanceof SlipperInterface) {
		    entities.add((EntityInterface) subject);
		}
    }
}
