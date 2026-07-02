package main.java.model;
import java.util.List;

import javafx.scene.paint.Color;


public interface Tetromino {
	
	enum RotationSense{
		CLOCKWISE,
		COUNTERCLOCKWISE;
	}
	enum Direction{
		RIGHT, LEFT, DOWN , UP;
	}
	
	/*Returns the shape of the tetromino as it was set during the build
	 * 
	 *@return the Shape of the Tetromino 
	 */
	public Shape getShape();

	/*Returns the Color of the tetromino.
	 * 
	 * @returns the Color of The Tetromino
	 */
	public Color getColor();
	
	/*Returns all the squares that make up the Tetromino
	 * as a List.
	 * 
	 * @return	the components of a Tetromino as a List of Squares
	 */
	List<Square> getAllSquares();
	
	/*Changes the position of the Tetromino according to the dir parameter
	 * doesn't check the consistency of the results
	 * 
	 * @param	dir	direction in which the Tetromino is suppose to move
	 */
	void move(Direction d);
	
	/*Rotates the Tetromino in the specified sense
	 * 
	 * @param	rotSense	Rotation sense in which the Tetromino as 
	 * 						to rotate
	 */
	void rotate(RotationSense rotSense);
	
	/* Returns a TetrominoImpl that's equivalent of the one calling
	 * 
	 * @return a new TetrominoImpl cloned from the caller
	 */
	public Tetromino clone();
	
}
