package tq2.interfaces;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * The Interface Controls is an extension of the interfaces KeyListener and MouseListener.
 * 
 * @author Francesco Gori
 */
public interface Controls extends KeyListener, MouseListener{

	/**
	 * Returns the Handler this object is relative to.
	 *
	 * @return the Handler
	 */
	public abstract Handler getHandler();

	/**
	 * Returns the level this object is relative to.
	 *
	 * @return the level
	 */
	public abstract Level getLevel();

}