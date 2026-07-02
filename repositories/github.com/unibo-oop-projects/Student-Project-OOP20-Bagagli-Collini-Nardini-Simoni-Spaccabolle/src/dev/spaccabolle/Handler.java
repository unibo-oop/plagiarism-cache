package dev.spaccabolle;

import dev.spaccabolle.input.KeyManager;
import dev.spaccabolle.input.MouseManager;


/**
 * The Class Handler.
 */
public class Handler {
	
	/** The game. */
	private Game game;

	
	/**
	 * Instantiates a new handler.
	 *
	 * @param game the game
	 */
	public Handler(Game game){
		this.game = game;
	}
	
	/**
	 * Gets the key manager.
	 *
	 * @return the key manager
	 */
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	/**
	 * Gets the mouse manager.
	 *
	 * @return the mouse manager
	 */
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth(){
		return game.getWidth();
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight(){
		return game.getHeight();
	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(Game game) {
		this.game = game;
	}
}
