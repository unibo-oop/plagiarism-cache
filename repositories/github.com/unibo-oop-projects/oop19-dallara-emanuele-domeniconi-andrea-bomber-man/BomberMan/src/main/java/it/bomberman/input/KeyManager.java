package it.bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, right, left, drop;
	public boolean up2, down2, right2, left2, drop2;

	public KeyManager() {
		keys = new boolean[256];
	}

	public void tick() {
		// TASTI PL1
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		drop = keys[KeyEvent.VK_SPACE];
		// TASTI PL2
		up2 = keys[KeyEvent.VK_UP];
		down2 = keys[KeyEvent.VK_DOWN];
		right2 = keys[KeyEvent.VK_RIGHT];
		left2 = keys[KeyEvent.VK_LEFT];
		drop2 = keys[KeyEvent.VK_ENTER];

	}

	// PRESSSIONE TASTO
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	// RILASCIO TASTO
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}

