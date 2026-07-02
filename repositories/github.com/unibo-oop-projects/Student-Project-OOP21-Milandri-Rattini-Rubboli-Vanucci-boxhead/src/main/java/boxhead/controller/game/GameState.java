package boxhead.controller.game;

/**
 * Used to manage and represent the different moments of the game.
 */
public class GameState {

	/**
	 * Initial state.
	 */
	public static GameStateEnum state = GameStateEnum.MENU;
	/**
	 * True if the scene needs to be changed, otherwise false.
	 */
	public static boolean change = false;
	/**
	 * True if the game has to be initialized, otherwise false.
	 */
	public static boolean init = false;
	/**
	 * True if the game needs to be closed, otherwise false.
	 */
	public static boolean close = false;
	/**
	 * True if the sound needs to be shut, otherwise false.
	 */
	public static boolean soundOff = false;
	
	private GameState() {
		
	}
	
	/**
	 * Enum for the different game states.
	 */
	public static enum GameStateEnum {
		
		MENU("menuView"),
		
		GAME("gameview"),
		
		PAUSE("gamePause"),
		
		END("end");
		
		private final String name;
		
		GameStateEnum(final String state) {
			this.name = state;
		}
		
		public final String getName() {
			return this.name;
		}
	}
}

