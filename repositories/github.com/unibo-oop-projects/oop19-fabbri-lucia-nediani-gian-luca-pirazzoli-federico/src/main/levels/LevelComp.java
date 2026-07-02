package main.levels;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import main.dynamicBody.character.enemy.TypeEnemy;
import main.dynamicBody.move.Direction;
import main.worldModel.utilities.Pair;

public interface LevelComp {
	
	/**
	 * Method that creates a List of RoomImpl that contain the information of each room of the level
	 */
	void loadRooms();
	
	/**
	 * Method that returns the list created with loadRomms
	 * @return List<RoomImpl>, the list of rooms that make up the level
	 */
	List<RoomImpl> getLevel();

	/**
	 * Method that returns the ID of the current loaded room
	 * @return int, the ID of the room
	 */
	int getRoomID();
	
	/**
	 * Method that sets the current room loaded
	 * @param roomID, the room ID that should be loaded
	 */
	void setRoomID(int roomID);
	
	/**
	 * Method that returns whether the Coin placed in the level has been obtained
	 * @return true if Coin obtained, false otherwise
	 */
	public boolean isGotLevelCoin();

	/**
	 * Method that sets the boolean if the Coin has been obtained
	 * @param gotLevelCoin, boolean
	 */
	public void setGotLevelCoin(boolean gotLevelCoin);
	
	public boolean isPauseMenu();
	
	public void setPauseMenu(boolean pauseMenu);
	
	public void setChangedRoom(boolean changedRoom);
	
	public boolean isChangedRoom();
	
	public Map<TypeEnemy, Set<Pair<Direction, Animation>>> checkAnimations() throws SlickException;
	
	public boolean isGameWon();
	
	public void setGameWon(boolean gameWon);
}