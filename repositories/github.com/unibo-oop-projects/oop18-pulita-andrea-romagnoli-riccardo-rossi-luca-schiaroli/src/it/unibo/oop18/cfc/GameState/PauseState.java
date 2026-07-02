// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package it.unibo.oop18.cfc.GameState;

import java.awt.Graphics2D;

import it.unibo.oop18.cfc.Manager.Content;
import it.unibo.oop18.cfc.Manager.GameStateManager;

public class PauseState extends GameState {
	
	public PauseState(GameStateManager gsm) {
		super(gsm, GameStates.PAUSE);
	}
	
	public void init() {}
	
	public void update() {}
	
	public void draw(Graphics2D g) {

		Content.drawString(g, "paused", 400, 200);

		Content.drawString(g, "arrow keys : move", 120, 270);

		Content.drawString(g, "space : action", 200, 340);

		Content.drawString(g, "F1: return to menu", 120, 410);
	}
}
