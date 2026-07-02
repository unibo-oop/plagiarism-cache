package dev.spaccabolle.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Class KeyManager.
 */
public class KeyManager implements KeyListener {
	
	/** The keys. */
	private boolean[] keys;
	
	/** The no. */
	public static boolean up, down, left, right, enter, pause, exit, 
	easy, normal, hard, space, home, restart, save, yes, no;
	
	/**
	 * Instantiates a new key manager.
	 */
	public KeyManager(){
		keys = new boolean[256];
	}
	
	/**
	 * Tick.
	 */
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		enter = keys[KeyEvent.VK_ENTER];
		space = keys[KeyEvent.VK_SPACE];
		pause = keys[KeyEvent.VK_P];  //pause
		exit = keys[KeyEvent.VK_E]; //exit game
		easy = keys[KeyEvent.VK_1]; //difficoltà gioco, modalità facile 
		normal = keys[KeyEvent.VK_2]; //difficoltà gioco, modalità normale
		hard = keys[KeyEvent.VK_3]; //difficoltà gioco, modalità difficile
		home = keys[KeyEvent.VK_H];
		save = keys[KeyEvent.VK_S];
		restart = keys[KeyEvent.VK_R];
		yes = keys[KeyEvent.VK_Y];
		no = keys[KeyEvent.VK_N];
	}

	/**
	 * Key pressed.
	 *
	 * @param e the e
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * Key released.
	 *
	 * @param e the e
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/**
	 * Key typed.
	 *
	 * @param e the e
	 */
	public void keyTyped(KeyEvent e) {
	
	}

}