package base;

/**
 * Base game management
 */

import java.awt.event.*;


public abstract class Game implements ActionListener, KeyListener {
	
        private static final int DELAY = 50;
    
	protected DrawingArea area;
	protected Time time;
	
	/**
	 * 
	 * @param title
	 *        JPanel name
	 * @param width
	 *        length width
	 * @param high
	 *        length high
	 */
	public Game (final String title, final int width, final int high) {
		area = new DrawingArea (title, width, high);
		area.addKeyListener(this);
		time = new Time(DELAY, this);
	}

	public void start() {
		time.start();
	}
	
	public abstract void check();
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		check();
		area.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	
	    
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
	    
	}
	
	@Override
	public void keyReleased(KeyEvent e) {	
	
	}
}
