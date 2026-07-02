package model;

import java.util.List;
import java.util.Set;

import model.entities.Alien;
import model.entitiesutil.MappedEntity;
import model.entitiesutil.typeentities.GenericEntity;
import view.game.GameEvent;

/**
 * interface for managing the game world
 *
 */
public interface World {
	
	/**
	 * a method that returns the initial entity set
	 * @return
	 */
	public Set<GenericEntity> getLevelEntities();
	
	/**
	 * a method that returns a set of new entities
	 * @return
	 */
	public Set<GenericEntity> getNewEntities();
	
	/**
	 * a method that updates entities
	 * @return
	 */
	public void updateEntityLevel(int cycles);
	
	/**
	 * a method that returns entities to be contained in the panel through a mapping
	 * @return
	 */
	public Set<MappedEntity> getMappedEntities();
	
	/**
	 * a method that returns a list of aliens still alive
	 * @return
	 */
	public List<Alien> getAlienList();

	/**
	 * method that return the max width of the game world
	 * @return
	 */
	public double getMaxWorldWidth();
	
	/**
	 * method that return the max height of the game world
	 * @return
	 */
	public double getMaxWorldHeight();

	/**
	 * method that return the min width of the game world
	 * @return
	 */
	public double getMinWorldWidth();

	/**
	 * method that return the min height of the game world
	 * @return
	 */
	public double getMinWorldHeight();

	/**
	 * method that cleans from entities, increments the level and calls the method to create the new level
	 * @return
	 */
	public void startNextLevel(); 

	/**
	 * method for restarting the game
	 * @return
	 */
	public void restartLevel();

	/**
	 * method that return the score
	 * @return
	 */
	public int getScore();

	/**
	 * method to process the inputs that are take from keyBoard 
	 *
	 * @param list
	 * @param cycles
	 */
	public void processInput(List<GameEvent> list, int cycles);
	
}
