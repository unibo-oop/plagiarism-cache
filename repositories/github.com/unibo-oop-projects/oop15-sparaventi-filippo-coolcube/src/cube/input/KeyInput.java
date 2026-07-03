package cube.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cube.Game;
import cube.Id;
import cube.entity.Entity;

public class KeyInput implements KeyListener {

	/**
	 * Gestisce le azioni che si verificano alla pressione di un tasto 
	 *
	 * @param KeyEvent e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0; i<Game.handler.entity.size(); i++){
			Entity en = Game.handler.entity.get(i);
			if(en.getId()==Id.player) {
				switch(key){
				case KeyEvent.VK_W:
					if(!en.jumping){
						en.jumping = true;
						en.gravity = 8.0;
					}
					break;
				case KeyEvent.VK_A:
					en.setVelX(-5);
					break;
				case KeyEvent.VK_D:
					en.setVelX(5);
					break;
				}
			}
			
		}	
	}

	/**
	 * Gestisce il rilascio dei tasti, terminando l'azione precedentemente avviata
	 * dalla pressione del relativo tasto
	 *
	 * @param KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0; i<Game.handler.entity.size(); i++){
			Entity en = Game.handler.entity.get(i);
			if(en.getId()==Id.player){
				switch(key){
				case KeyEvent.VK_W:
					en.setVelY(0);
					break;
				case KeyEvent.VK_A:
					en.setVelX(0);
					break;
				case KeyEvent.VK_D:
					en.setVelX(0);
					break;
				}
			}
		}
	}
	
	public void keyTyped(KeyEvent arg0) {
		//non usata
	}

}
