package hollowmen.model;

import org.jbox2d.dynamics.World;

import hollowmen.enumerators.Difficulty;
import hollowmen.model.utils.GameOverException;
import hollowmen.utilities.Pair;

/**
 * A {@code Dungeon} has a sequence of floor, a single floor can be selected if it's unlocked.
 * For unlock a floor the player has to complete the previous one.
 * For complete a floor the player need to pass all the {@link Room} that compose that floor.
 * It holds methods for update the game
 * <br>
 * <br>
 * A {@code Dungeon} has a {@link Difficulty} which influence some parameter in the game
 * @author pigio
 *
 */
public interface Dungeon {

	/**
	 * This enum indicates the possible value of challenge, 
	 * their value will be used for modify the {@code Enemy} and their loot (less Exp and Gold).<br>
	 * <br>
	 * EASY, NORMAL, HARD.
	 * @author pigio
	 *
	 */
	/*public enum Difficulty{
		EASY,
		NORMAL,
		HARD;
	}*/
	
	/**
	 * This method handle the update cycle that can be summarized as:<br>
	 * move all Entities,<br>
	 * check Collision,<br>
	 * resolve Collision.
	 * 
	 * @param deltaTime the time elapsed from the last update.
	 * @throws GameOverException If the time is over or If Hero's death
	 */
	public void update(long deltaTime) throws GameOverException;

	/**
	 * This method set the {@code Floor} where the player will play
	 * 
	 * @param floorNumber where the player will play
	 * @throws IllegalStateException If the {@code Floor} is locked
	 * @throws NullPointerException
	 */
	public void goTo(int floorNumber) throws IllegalStateException;
	
	/**
	 * @return {@link Pair} X floor's number reached, Y max floor's number
	 */
	public Pair<Integer, Integer> getFloors();
	
	/**
	 * This method will set the current {@code Floor}'s number to 0,
	 * this {@code Floor} has the lobby {@code Room}<br>
	 * Increase the reached floor's number if this method is called when the {@code Hero}
	 * pass the last {@code Room} of the current {@code Floor}
	 */
	public void endRun();
	/**
	 * This method give the currently setted {@code Difficulty} for the {@code Dungeon}
	 * @return {@link Difficulty} currently setted
	 */
	public Difficulty getDifficulty();
	
	/**
	 * This method give the {@code Dungeons}'s {@code Shop}
	 * @return {@link Shop}
	 */
	public Shop getShop();
	
	/**
	 * This method gives the {@code Hero}!
	 * @return {@link Hero}
	 */
	public Hero getHero();
	
	
	/**
	 * This method give the floor number, it's number influence the {@link Enemy} found in each {@link Room}
	 * @return {@code int} that indicates the floor number
	 */
	public int getFloorNumber();
	
	/**
	 * This method give the current {@link Room} visited by the {@link Hero}, basically the {@code Room} where the player is
	 * @return {@link Room} where the player is
	 */
	public Room getCurrentRoom();
	
	/**
	 * This method change the current {@code Room} with one of its child OR to its parent if
	 * <b>choice</b> equals to any negative number
	 * @param choice (<u>starting from 0</u>) for select the child, or any negative value for parent
	 */
	public void changeRoom(int choice);
	
	/**
	 * This method gives the {@code Pokedex}
	 * @return {@link Pokedex}
	 */
	public Pokedex getPokedex();
	
	/**
	 * This method gives the {@code World}
	 * @return {@link World}
	 */
	public World getWorld();
	
	/**
	 * This method gives the {@code Time}
	 * @return {@link Time}
	 */
	public Time getTimer();
	

}
