package model;

import java.awt.event.KeyListener;

public class KeyEvent implements KeyListener {

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		if(e.getKeyCode() == e.VK_UP){
	        System.out.println("Pressed UP");
	        //scegliere la view e far muovere la racchetta
	    }
	    if(e.getKeyCode() == e.VK_DOWN){
	        System.out.println("Pressed DOWN");
	        //p.moveLeft(); 
	    }
	    System.out.println("sono stronzo");
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		System.out.println("released");
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		System.out.println("typed");
	}

}
