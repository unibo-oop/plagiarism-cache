package minigames;

import java.util.LinkedList;
import minigames.MiniGameFactoryImpl.GAMES_ID;

public interface MiniGameFactory {
    /**
	 * This class stores and provides the recipes for all game types.
	 */
    /**
	 * Creates and immediately loads a minigame into the controller
	 * @param id of the minigame to be created
	 * @param difficulty of the minigame
	 */
	public void StartGame(GAMES_ID id, int difficulty);
    /**
	 * Creates a MiniGameImpl object with the CubeInvaders recipe.Loading of the game must be manual.
	 * @param difficulty
	 * @return minigame
	 */
	public MiniGameImpl CubeInvaders(int difficulty);
    /**
	 * Creates a MiniGameImpl object with the CubeDestroyer recipe.Loading of the game must be manual.
	 * @param difficulty
	 * @return minigame
	 */
	public MiniGameImpl CubeDestroyer(int difficulty);
    /**
	 * Creates a MiniGameImpl object with the SimonSays recipe.Loading of the game must be manual.
	 * @param difficulty
	 * @return minigame
	 */
	public MiniGameImpl SimonSays(int difficulty);
    /**
	 * Creates a game object with the appropriate recipe for the id.Loading of the game must be manual.
	 * @param id
	 * @return minigame
	 */
	public MiniGameImpl CreateGame(GAMES_ID id, int difficulty);
    /**
	 * Gets a string list of all the game recipe known
	 * @return string list
	 */
	public LinkedList<String> allNames();
    /**
	 * Creates a list of 5 minigames composed using a standard linear increasing difficulty (1 -> 5).
	 * @param repeat, if true it allows the same minigame to appear more than once.if false the final game list lenght may be shorter than 5.
	 * @return minigame list
	 */
	public LinkedList<MiniGame> increasingRecipe(boolean repeat);
}
