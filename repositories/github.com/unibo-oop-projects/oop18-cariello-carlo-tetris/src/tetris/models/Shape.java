package tetris.models;

import tetris.models.ShapeImpl.Tetrominoes;

public interface Shape{
	
	/**
     * This method create a shape 
     */
	 public void setPieceShape(Tetrominoes pieceShape);
	 
	 /**
	  * Assign the tetromino's position on the row
	  */
	 public void setX(int index, int x);

	 /**
	  * Assign the tetromino's position on the column
	  */
	 public void setY(int index, int y);
	    
	 /**
	  * Return the value of X into the tetromino array.
	  */
	 public int x(int index);

	 /**
	  * Return the value of Y into the tetromino array.
	  */
	 public int y(int index);
	 
	 /**
	  * Get the shape of tetromino
	  */
	 public Tetrominoes getPieceShape();
	  
	 /**
	  * Generate the random tetromino
	  */
	 public void setRandomShape();

	 /**
	  * This method returns the smaller value of X in tetromino array
	  */
	 public int minX();

	 /**
	  * This method returns the smaller value of Y in tetromino array
	  */
	 public int minY();

	 /**
	  * Rotates the tetromino on the left
	  */
	 public ShapeImpl rotateLeft();

	 /**
	  * Rotates the tetromino on the right
	  */
	 public ShapeImpl rotateRight();
}
