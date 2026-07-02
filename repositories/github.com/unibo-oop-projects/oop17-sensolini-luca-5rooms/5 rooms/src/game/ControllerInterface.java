package game;

import java.awt.event.MouseEvent;
import java.util.List;
import minigames.MiniGame;
import model.Model;
import view.View;

public interface ControllerInterface {
    /**
	 * adds a game object to the game (will be drawn and computed)
	 * @param the object to add
	 */
	public void addObject(GameObject o);
    /**
	 * removes a specific game object
	 * @param the game to remove
	public void removeObject(GameObject o);
    /**
	 * clears the object list
	 */
	public void clear();
    /**
	 * returns all game objects with a given ID
	 * @param ID
	 * @return a list containing all of the game objects of the given ID
	 */
	public List<GameObject> getObject(ID id);
    /**
	 * returns all game objects
	 * @return a list containing all of the game objects
	 */
	public List<GameObject> getAll();
    /**
	 * returns all game objects that have collision boxes
	 * @return a list containing all of the game objects with collision boxes
	 */
	public List<GameObject> getCollisionable();
	
	 /**
	 * Subject for keyPressed.
	 * @param key
	 */
	public void keyPressed(int k);
	 /**
	 * Subject for keyReleased.
	 * @param key
	 */
	public void keyReleased(int k);
	 /**
	 * Subject for mouseDragged.
	 * @param mouse event
	 */
	public void mouseDragged(MouseEvent e);
	 /**
	 * Subject for mouseMoved.
	 * @param mouse event
	 */
	public void mouseMoved(MouseEvent e);
	/**
	 * Subject for mouseClicked.
	 * @param mouse event
	 */
	public void mouseClicked(MouseEvent e);
	/**
	 * gets the used view object
	 * @return view
	 */
	public View getView();
	/**
	 * gets the used model object
	 * @return model
	 */
	public Model getModel();
	/**
	 * adds a minigame to the list of games
	 * @param minigame
	 */
	public void addGame(MiniGame game);
	/**
	 * adds all minigame from a list to the controller's list
	 * @param a list of minigames
	 */
	public void addFromList (List<MiniGame> list);
	/**
	 * loads the next game in line
	 */
	public void playNext();
	/**
	 * Observer for a game's fail state
	 */
	public void FailState();
	/**
	 * Observer for a game's success state
	 */
	public void SuccessState();
	/**
	 * hides the view's menu
	 */
	public void menuHide();
	/**
	 * shows the view's menu
	 */
	public void menuShow();
	/**
	 * The controller may act as a subject for other game objects if they implement the Listener interface
	 * and list themselves using this method.In particular, the controller shall notify the deletion listeners
	 * whenever an item with the given id is deleted.Listener-id pairs are kept in a map.
	 * @param l , the listener to be listed, typically the caller itself.
	 * @param id, the id to listen for
	 */
	public void listenDeletion(Listener l, ID id);
	
}
