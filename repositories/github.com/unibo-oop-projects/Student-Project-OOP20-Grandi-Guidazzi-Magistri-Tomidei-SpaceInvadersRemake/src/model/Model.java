package model;

import java.util.List;
import java.util.Set;

import controller.GameController;
import model.entities.Alien;
import model.entitiesutil.MappedEntity;
import model.entitiesutil.typeentities.GenericEntity;
import view.game.GameEvent;

/**
 * interface of the game's model
 *
 */
public interface Model {

	/**
	 * method to process the inputs that are take from keyBoard 
	 * 
	 * @param list
	 * @param cycles
	 */
	public void processInput(List<GameEvent> list, int cycles);

	/**
	 * a method that returns a set of new entities
	 * @return
	 */
	public Set<GenericEntity> getNewEntities();

	/**
	 * a method that returns entities to be contained in the panel through a mapping
	 * @return
	 */
	public Set<MappedEntity> getMappedEntities();

	/**
	 * a method that updates entities
	 * @return
	 */
	public void updateEntityLevel(int cycles);

	/**
	 * method that return the current controller which is managing the game
	 * @return
	 */
	public GameController getController();

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
	 * method that return the world which contains the current entities
	 * @return
	 */
	public World getWorld();

	/**
	 * method that return the score
	 * @return
	 */
	public int getScore();

	/**
	 * method that process the game over
	 * @return
	 */
	public void processGameOver();
	
	/**
	 * method for restarting the game
	 */
	public void restartGame();
}
