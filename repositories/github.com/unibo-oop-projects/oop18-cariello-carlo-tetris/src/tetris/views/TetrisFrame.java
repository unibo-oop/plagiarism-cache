package tetris.views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import tetris.models.ShapeImpl.Tetrominoes;

public interface TetrisFrame {

	/**
	 * Sets image to the next tetromino 
	 */
	public void setNextPieceImage(ImageIcon imm);
	
	/**
	 * Sets image to the next tetromino 
	 */
	public void updateNextImage(Tetrominoes tetr);
	
	/**
	 * This method read the name of current player. Correct nickname is not empty and is at least 3 long
	 */
	public void insertName();
	
	public TetrisFrameImpl getTetrisFrame();
	
	/**
     * This method create a frame of game. This frame is composed by 3 panels.
     */
	public void init();
	 
	/**
     * This method shows the number of deleted rows
     */
	JLabel getStatusBar();
}
