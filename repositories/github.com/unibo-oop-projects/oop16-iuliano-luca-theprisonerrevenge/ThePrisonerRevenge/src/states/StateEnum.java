package states;

/**
 * Represents an enumeration for the possible State who can be the application.
 * 
 * @author Luca
 */
public enum StateEnum {
	/**
	 * Menu state, show the menu and the selector who can be moved to start,
	 * options and exit.
	 */
	MENU_STATE,
	/**
	 * Game state.
	 */
	GAME_STATE,
	/**
	 * Option state where read the information and how to play.
	 */
	OPTION_STATE,
	/**
	 * Win state show victory title when you complete the game.
	 */
	WIN_STATE,
	/**
	 * Game over state show game over when you die.
	 */
	GAMEOVER_STATE;
}