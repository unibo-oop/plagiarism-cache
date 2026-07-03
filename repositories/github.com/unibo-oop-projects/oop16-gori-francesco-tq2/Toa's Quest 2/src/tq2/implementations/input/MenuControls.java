package tq2.implementations.input;

import java.awt.event.KeyEvent;

import tq2.implementations.level.TQMenu;
import tq2.interfaces.Handler;

/**
 * The Class MenuControls contains the controls for the main menu of the game "Toa's Quest 2".
 * 
 * @author Francesco Gori
 */
public class MenuControls extends ControlsImpl {

	/**
	 * Instantiates a new MenuControls.
	 *
	 * @param handler the handler
	 * @param level the level
	 */
	public MenuControls(Handler handler, TQMenu level) {
		super(handler, level);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.input.ControlsImpl#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		super.keyPressed(e);
		
		Integer key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			//move up in the menu
			((TQMenu)this.getLevel()).addChoice(-1);
		break;
		
		case KeyEvent.VK_S:
			//move down in the menu
			((TQMenu)this.getLevel()).addChoice(1);
		break;
		
		case KeyEvent.VK_ENTER:
			//select
			switch(((TQMenu)this.getLevel()).getChoice()) {
				case 0:
					this.getHandler().getGame().nextLevel();
					break;
				case 1:
					System.exit(0);
			}
		}
	}
}
