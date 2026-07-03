package tq2.implementations.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import tq2.interfaces.Controls;
import tq2.interfaces.Handler;
import tq2.interfaces.Level;

/**
 * The Class ControlsImpl implements the interface Controls, which is made of the interfaces MouseListener and KeyListener.
 * 
 * 
 * @author Francesco Gori
 */
public abstract class ControlsImpl implements Controls {
	
	/** The Handler that contains the objects of the level this object is relative to. */
	Handler handler;
	
	/** The level this object is relative to. */
	protected Level level;
	
	/**
	 * Instantiates a new object of this class, initializing the values.
	 *
	 * @param handler the Handler
	 * @param level the level
	 */
	public ControlsImpl(Handler handler, Level level) {
		this.handler = handler;
		this.level = level;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Controls#getHandler()
	 */
	@Override
	public Handler getHandler() {
		return this.handler;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Controls#getLevel()
	 */
	@Override
	public Level getLevel() {
		return this.level;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.handler == null || this.getLevel() == null) {
			return;
		}
	}
}
