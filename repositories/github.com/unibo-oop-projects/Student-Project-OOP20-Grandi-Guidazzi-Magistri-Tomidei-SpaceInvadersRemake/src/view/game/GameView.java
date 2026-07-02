package view.game;

import java.util.List;
import java.util.Set;

import model.entitiesutil.MappedEntity;

public interface GameView {

	/**
	 * method that return the list of the game events
	 * @return
	 */
	List<GameEvent> getEvents();

	/**
	 * method that update the graphic of the entities that are take from the input
	 * @param updates
	 */
	public void updateGui(Set<MappedEntity> updates);

	/**
	 * method that open the game over scene
	 */
	public void openGameOver();

	/**
	 * method that open the victory scene

	 */
	public void openVictoryScene();

	/**
	 * method that return the panel width
	 * @return
	 */
	public int getWidth();

	/**
	 * method that return the panel height
	 * @return
	 */
	public int getHeight();

	/**
	 * Method for clear the map
	 */
	void clearKeyMap();
	

}