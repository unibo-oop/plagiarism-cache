package cube.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import cube.Game;
import cube.graphics.gui.Button;

public class MouseInput implements MouseMotionListener, MouseListener {

	
	public int x, y;
	
	public void mouseClicked(MouseEvent e) {
		
		
	}

	/**
	 * Indica quando il mouse viene premuto, verrà successivamente valutato su quale pulsante viene premuto (Start/Exit)
	 *
	 * @param MouseEvent e
	 */
	public void mousePressed(MouseEvent e) {
		for(int i=0; i<Game.launcher.buttons.length; i++){
			Button button = Game.launcher.buttons[i];
			
			if(x>=button.getX() && y>=button.getY() && x<button.getX()+button.getWidth() && y<button.getY()+button.getHeight()){
				button.TriggerEvent();
			}
		}
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}

	
	public void mouseDragged(MouseEvent e) {
	
		
	}

	/**
	 * Restitousce nelle variabili x e y la relativa posizione del mouse
	 *
	 * @param MouseEvent e
	 */
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
	}

}
