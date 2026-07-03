package Gioco;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(Main.scene.pilar.isVivo() == true){
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			
				// per non fare muovere il castello e start 
				if(Main.scene.getxPos() == -1){
					Main.scene.setxPos(0);
					Main.scene.setX1(-50);
					Main.scene.setX2(750);
				}
				Main.scene.pilar.setIn_movimento(true);
				Main.scene.pilar.setVerso_destra(true);
				Main.scene.setMov(1); // si muove verso sinistra
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			
				if(Main.scene.getxPos() == 4601){
					Main.scene.setxPos(4600);
					Main.scene.setX1(-50);
					Main.scene.setX2(750);
				}
			
				Main.scene.pilar.setIn_movimento(true);
				Main.scene.pilar.setVerso_destra(false);
				Main.scene.setMov(-1); // si muove verso destra 
			}
			// salto
			if(e.getKeyCode() == KeyEvent.VK_UP){
				Main.scene.pilar.setSalto(true);
				Audio.playSound("/audio/jump.wav");
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Main.scene.pilar.setIn_movimento(false);
		Main.scene.setMov(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
