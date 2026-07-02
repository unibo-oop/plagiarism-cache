package hollowmen.view.ale;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

/**
 * The {@code KeyInput} class is used to inform when a key is pressed
 * or released.
 * 
 * @author Alessia
 *
 */
public class KeyInput implements KeyEventDispatcher{
	Game game;
	
	public KeyInput(Game game){
		this.game=game;
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED){
			this.game.keyManager(e);
		}
		return false;
	}
}
