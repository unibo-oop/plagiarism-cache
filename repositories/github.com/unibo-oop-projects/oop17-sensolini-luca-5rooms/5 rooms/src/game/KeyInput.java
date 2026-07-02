package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class KeyInput extends KeyAdapter implements MouseMotionListener, MouseListener{
	
	private Controller controller;

	public KeyInput(Controller controller) {
		this.controller = controller;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		controller.keyPressed(key);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		controller.keyReleased(key);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		controller.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		controller.mouseDragged(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		controller.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		controller.mouseClicked(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
