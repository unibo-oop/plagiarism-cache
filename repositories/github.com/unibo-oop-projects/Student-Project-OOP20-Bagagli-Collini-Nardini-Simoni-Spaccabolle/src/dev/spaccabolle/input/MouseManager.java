package dev.spaccabolle.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.spaccabolle.ui.UIManager;

/**
 * The Class MouseManager.
 */
public class MouseManager implements MouseListener, MouseMotionListener {

	/** The right pressed. */
	private boolean leftPressed, rightPressed;
	
	/** The mouse Y. */
	private int mouseX, mouseY;
	
	/** The ui manager. */
	private UIManager uiManager;
	
	/**
	 * Instantiates a new mouse manager.
	 */
	public MouseManager(){
		
	}
	
	/**
	 * Sets the UI manager.
	 *
	 * @param uiManager the new UI manager
	 */
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	// Getters
	
	/**
	 * Checks if is left pressed.
	 *
	 * @return true, if is left pressed
	 */
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	/**
	 * Checks if is right pressed.
	 *
	 * @return true, if is right pressed
	 */
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	/**
	 * Gets the mouse X.
	 *
	 * @return the mouse X
	 */
	public int getMouseX(){
		return mouseX;
	}
	
	/**
	 * Gets the mouse Y.
	 *
	 * @return the mouse Y
	 */
	public int getMouseY(){
		return mouseY;
	}
	
	// Implemented methods
	
	/**
	 * Mouse pressed.
	 *
	 * @param e the e
	 */
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
	}

	/**
	 * Mouse released.
	 *
	 * @param e the e
	 */
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
		if(uiManager != null)
			uiManager.onMouseRelease(e);
	}

	/**
	 * Mouse moved.
	 *
	 * @param e the e
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}
	
	/**
	 * Mouse dragged.
	 *
	 * @param e the e
	 */
	public void mouseDragged(MouseEvent e) {
		
	}

	/**
	 * Mouse clicked.
	 *
	 * @param e the e
	 */
	public void mouseClicked(MouseEvent e) {
		switch(e.getButton())
	     { 
	      case MouseEvent.NOBUTTON : // do stuff on button release
	           break;
	      case MouseEvent.BUTTON1 : // do stuff on click
	           break;

	     }
		
	}

	/**
	 * Mouse entered.
	 *
	 * @param e the e
	 */
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * Mouse exited.
	 *
	 * @param e the e
	 */
	public void mouseExited(MouseEvent e) {
		
	}

}