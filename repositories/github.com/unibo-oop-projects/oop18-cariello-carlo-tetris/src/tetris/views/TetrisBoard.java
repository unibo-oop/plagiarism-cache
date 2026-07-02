package tetris.views;

import java.awt.Graphics;

public interface TetrisBoard{

	/**
	 * This method starts the controller
	 */
	 public void start();
	
	 /**
	  * Paints tetromino
	  */
	 public void paint(Graphics g);
	 
	 /**
	  * Sets the width of tetromino's unity
	  */
	 public int squareWidth();
	    
	 /**
	  * Sets the height of tetromino's unity
	  */
	 public int squareHeight();
	 
	 /**
	  * Paints the tetromino's unity
	  */
	 public void drawSquare(Graphics g, int x, int y, tetris.models.ShapeImpl.Tetrominoes shape);
	 
	 /**
	  * Shows name of current player and the number of deleted rows
	  */
	 public void setStatusText(String text);
	 
}
