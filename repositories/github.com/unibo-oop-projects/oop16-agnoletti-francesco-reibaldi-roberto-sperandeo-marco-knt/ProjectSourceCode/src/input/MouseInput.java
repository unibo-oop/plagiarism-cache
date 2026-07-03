package input;

import graphics.gui.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import knight.Game;

public class MouseInput implements MouseMotionListener, MouseListener {

	public int x, y;

	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * Indica quando il mouse viene premuto, per poi valutare quale pulsante
	 * viene premuto (Start/rank/info/Exit)
	 *
	 * @param MouseEvent e
	 */
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < Game.launcher.buttons.length; i++) {
			Button button = Game.launcher.buttons[i];
			Button buttonr = Game.rank.button;
			Button buttonInf = Game.info.button;
			if (x >= button.getX() && y >= button.getY()
					&& x < button.getX() + button.getWidth()
					&& y < button.getY() + button.getHeight()) {
				button.TriggerEvent();
			}
			if (x >= buttonr.getX() && y >= buttonr.getY()
					&& x < buttonr.getX() + buttonr.getWidth()
					&& y < buttonr.getY() + buttonr.getHeight()) {
				buttonr.TriggerEvent();
			}
			if (x >= buttonInf.getX() && y >= buttonInf.getY()
					&& x < buttonInf.getX() + buttonInf.getWidth()
					&& y < buttonInf.getY() + buttonInf.getHeight()) {
				buttonInf.TriggerEvent();
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
	 * Restituisce nelle variabili x e y la posizione del mouse
	 *
	 * @param MouseEvent e
	 */
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
}
