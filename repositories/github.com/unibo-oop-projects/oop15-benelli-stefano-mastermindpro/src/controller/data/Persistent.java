package controller.data;

import java.util.List;
import java.util.Optional;

import model.games.Game;

/**
 * 
 * @author Stefano Benelli
 * Basic representation of Persistent entity
 */
public interface Persistent {
	
	/**
	 * Save the pending Game
	 * @param game Game to save
	 * @throws PersistentException thrown if an error occurs during Save process
	 */
	void savePendingGame(Game game) throws PersistentException;
	
	/**
	 * Loads the pending Game (if exists)
	 * @return Game loaded
	 * @throws PersistentException thrown if an error occurs during Load process
	 */
	Optional<Game> loadPendingGame() throws PersistentException;
	
	/**
	 * Clear the pending Game
	 */
	void clearPendingGame();
	
	/**
	 * Archives the Game
	 * @param game Game to Archive
	 * @throws PersistentException thrown if an error occurs during Archiviation process
	 */
	void archiveGame(Game game) throws PersistentException;
	
	/**
	 * Loads all the Archived Games
	 * @return List of Archived Games
	 * @throws PersistentException thrown if an error occurs during Loading process
	 */
	List<Game> loadArchivedGames() throws PersistentException;
}
