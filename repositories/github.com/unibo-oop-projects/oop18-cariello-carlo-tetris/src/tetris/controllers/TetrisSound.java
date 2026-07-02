package tetris.controllers;

public interface TetrisSound {

	/**
	 * Play sound theme.
	 */
	public void playTheme();
	
	/**
	 * Play sound deleted row.
	 */
	public void playLine();
	
	/**
	 * Play sound game over.
	 */
	public void playGameOver();
	
	/**
	 * Stop sound theme.
	 */
	public void stopTheme();
	
	/**
	 * Sound deleted row.
	 */
	public void stopLine();
	
	/**
	 * Stop sound game over.
	 */
	public void stopGameOver();
	
}
