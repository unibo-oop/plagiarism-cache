package tetris.views;

import tetris.controllers.BoardControllerImpl;
import tetris.views.TetrisFrameImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class sets grid game.
 * @author Carlo
 *
 */
public class TetrisBoardImpl extends JPanel implements ActionListener, TetrisBoard {
		
	private static final long serialVersionUID = -8132523269499811787L;
	private final int BOARD_WIDTH = 10;
	private final int BOARD_HEIGHT = 22;
	private JLabel statusBar=new JLabel();
	private BoardControllerImpl controller;

	/**
	 * class constructor
	 * @param parent
	 */
	TetrisBoardImpl(TetrisFrameImpl parent) {
	    setFocusable(true);
	    controller = new BoardControllerImpl(BOARD_WIDTH, BOARD_HEIGHT, this/*, tetrisFrame.getTetrisFrame()*/);
	    statusBar = parent.getStatusBar();
	    addKeyListener(new TAdapter());
	}
	   
	public void start() {
	   controller.start();
	}

	/**
	 * Check if the tetromino falls is ended.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	   controller.gameAction();
	}    

	public void paint(Graphics g) {
	     super.paint(g);
	     controller.paint(g, getSize().getWidth(), getSize().getHeight());
	}    

	public int squareWidth() { 
	    return (int) getSize().getWidth() / BOARD_WIDTH; 
	}    
	    
	public int squareHeight() {
	    return (int) getSize().getHeight() / BOARD_HEIGHT; 
	}    

	public void drawSquare(Graphics g, int x, int y, tetris.models.ShapeImpl.Tetrominoes shape) {
	     Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102),
	              new Color(102, 204, 102), new Color(102, 102, 204),
	              new Color(204, 204, 102), new Color(204, 102, 204),
	              new Color(102, 204, 204), new Color(218, 170, 0)
	     };
	        
	     Color color = colors[shape.ordinal()];

	     g.setColor(color);
	     g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

	     g.setColor(color.brighter());
	     g.drawLine(x, y + squareHeight() - 1, x, y);
	     g.drawLine(x, y, x + squareWidth() - 1, y);

	     g.setColor(color.darker());
	     g.drawLine(x + 1, y + squareHeight() - 1,
	                x + squareWidth() - 1, y + squareHeight() - 1);
	     g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
	                x + squareWidth() - 1, y + 1);
	}    

	public void setStatusText(String text) {
	        statusBar.setText("<html> Welcome "+TetrisFrameImpl.name+"!" + " <br> your score is "+ BoardControllerImpl.numLinesRemoved+" deleted rows </html>"); 
	}    

	/**
	 * This method gets command from keyboard
	 * @author Carlo
	 *
	 */
	private class TAdapter extends KeyAdapter {
	      public void keyPressed(KeyEvent e){
	            if (!controller.isStarted() || controller.isCurrentPieceNoShaped()){
	                return;
	            }
	            int keycode = e.getKeyCode();

	            if (keycode == 'p' || keycode == 'P'){
	                controller.pause();
	                return;
	            }

	            if (controller.isPaused())
	                return;

	            switch (keycode){
	               	case KeyEvent.VK_LEFT:
	                    controller.moveLeft();
	                    break;
	                case KeyEvent.VK_RIGHT:
	                    controller.moveRight();
	                    break;
	                case KeyEvent.VK_DOWN:
	                    controller.rotateRight();
	                    break;
	                case KeyEvent.VK_UP:
	                    controller.rotateLeft();
	                    break;
	                case KeyEvent.VK_SPACE:
	                    controller.dropDown();
	                    break;
	            }
	        }
	    }    
	}
