package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.EnergyWave;
import entity.Entity;
import knight.Game;
import knight.Id;

public class KeyInput implements KeyListener {

	/**
	 * Gestisce le azioni che si verificano alla pressione di un tasto
	 *
	 * @param KeyEvent e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < Game.handler.entity.size(); i++) {
			Entity en = Game.handler.entity.get(i);
			if (en.getId() == Id.player) {
				switch (key) {
				case KeyEvent.VK_W: // salta
					if (!en.jumping) {
						en.jumping = true;
						en.gravity = 8.0;
					}
					break;
				case KeyEvent.VK_A: // sinistra
					en.setVelX(-5);
					break;
				case KeyEvent.VK_D: // destra
					en.setVelX(5);
					break;
				case KeyEvent.VK_K: // spara
					if (!en.fire && en.powerUpGet) {
						en.fire = true;
						if (en.SX) { // controllare coordinate inserite per creare onda
							Game.handler.addEntity(new EnergyWave(0, en.getX() - 70, en.getY(), 64, 64, true, Id.energyWave, Game.handler));
							Game.playerAttack.play();
						} else if (en.DX) {
							Game.handler.addEntity(new EnergyWave(1, en.getX() + 70, en.getY(), 64, 64, true, Id.energyWave, Game.handler));
							Game.playerAttack.play();
						}
					}
					break;
				}
			}
		}
	}

	/**
	 * Gestisce il rilascio dei tasti, termina quindi l'azione precedentemente
	 * avviata dalla pressione del relativo tasto
	 *
	 * @param KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < Game.handler.entity.size(); i++) {
			Entity en = Game.handler.entity.get(i);
			if (en.getId() == Id.player) {
				switch (key) {
				case KeyEvent.VK_W:
					en.setVelY(0);
					break;
				case KeyEvent.VK_A:
					en.setVelX(0);
					break;
				case KeyEvent.VK_D:
					en.setVelX(0);
					break;
				case KeyEvent.VK_K:
					en.fire = false;
					break;
				}
			}
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// non usata
	}

}
