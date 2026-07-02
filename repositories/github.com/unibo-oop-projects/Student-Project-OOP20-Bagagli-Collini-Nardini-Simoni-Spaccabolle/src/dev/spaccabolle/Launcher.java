package dev.spaccabolle;

/**
 * The Class Launcher.
 */
public class Launcher {
    
    /** The Constant GAME_WIDTH. */
    public static final int GAME_WIDTH = 840;
    
    /** The Constant GAME_HEIGHT. */
    public static final  int GAME_HEIGHT = 740;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		Game game = new Game("SPACCABOLLE", GAME_WIDTH, GAME_HEIGHT);
		game.start();
	}
	
}
