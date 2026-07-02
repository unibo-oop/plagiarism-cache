package tetris.controllers;

import java.awt.Graphics;
import javax.swing.Timer;
import tetris.models.ShapeImpl;

public interface BoardController{

	/**
     * This method check if the tetromino falls is ended.
     */
	public void gameAction();
	
	/**
	 * Check if the game is started
	 */
	public boolean isStarted();
	
	/**
	 * Check if the game is paused
	 */
	public boolean isPaused();
	
	/**
     * Sets no Shape to tetromino
     */
	public boolean isCurrentPieceNoShaped();
	
	/**
     * Sets no Shape to next tetromino
     */
	public boolean isNextPieceNoShaped();
	
	/**
     * Method that initializes (numLinesRemoved, timer) sound and starts the game
     */
	public void start();
	
	/**
     * This method sets the game in pause.
     */
	public void pause();
	
	/**
     * This method the tetromino of a line will fall
     */
	public void oneLineDown();
	
	/**
     * This method clear the game area
     */
	public void clearBoard();
	
	/**
     * This method makes the tetromino fall faster
     */
	public void dropDown();
	
	/**
     * This method paints the tetromino
     */
	public void paint(Graphics g, double width, double height);
	
	/**
     * This method generates a new tetromino
     */
	public void newPiece();
	
	/**
     * Check if it is possible to move tetromino
     */
	public boolean tryMove(ShapeImpl newPiece, int newX, int newY);
	
	/**
	 * This method drop down the tetromino
	 */
	public void pieceDropped();
	
	/**
	  * This method move the tetromino to left
	  */
	public void moveLeft();
	
	/**
	  * This method move the tetromino to right
	  */
    public void moveRight();
    
    /**
	  * This method rotate the tetromino to left
	  */
    public void rotateLeft();
    
    /**
	  * This method rotate the tetromino to right
	  */
    public void rotateRight();

    /**
	  * This method get timer from Timer class Java
	  */
    public Timer getTimer();
    
}
