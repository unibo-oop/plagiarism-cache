package dev.spaccabolle.gfx;

import java.awt.Color;
import java.awt.Graphics;

import dev.spaccabolle.states.StateGame;

/**
 * The Class Pause.
 */
public class Pause {
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
    	
    	g.drawImage(Assets.black, 200, 200, 400, 400, null);
    	g.drawRect(200, 200, 400, 400);
    	
    	g.drawImage(Assets.easy, 280, 210, 290, 90, null);
    	g.drawImage(Assets.normal, 280, 310, 290, 90, null);
    	g.drawImage(Assets.hard, 280, 410, 290, 90, null);
    	
    	g.drawImage(Assets.home, 210, 510, 90, 90, null);
    	g.drawImage(Assets.pause, 353, 510, 90, 90, null);
    	g.drawImage(Assets.exit, 495, 510, 90, 90, null);
    	
    	g.drawImage(Assets.dragon2, StateGame.xDragon, StateGame.yDragon, 100, 100, null);
	}
	
}
