package minigames;

import java.util.LinkedList;

import game.GameObject;

public interface MiniGame {
	
    /**
	 * adds all the minigame's assets (gameobjects) to the controller, effectively loading them
	 */
	public int StartGame();
    /**
	 * gets a string rapresentation of the minigame's assets
	 */
	public String asString();
    /**
	 * add an object to the list of assets
	 * @param gameobject
	 */
	public void addObject(GameObject o);

    /**
	 * gets a list of the game objects
	 */

	public LinkedList<GameObject> getObjects();
}
