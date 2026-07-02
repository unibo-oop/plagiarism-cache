package dev.spaccabolle.gfx;

import java.awt.Graphics;

import dev.spaccabolle.Launcher;
import dev.spaccabolle.entity.CollectBall;
import dev.spaccabolle.states.StateGame;


// TODO: Auto-generated Javadoc
/**
 * The Class DrawImage.
 */
public class DrawImage {
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		
			 g.drawImage(Assets.dark_background, 0, 0, Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT, null);
			 
			 g.drawImage(Assets.btn_save, 40, 640, 200, 90, null);
			 g.drawImage(Assets.btn_pause, 330, 640, 200, 90, null);
			 g.drawImage(Assets.btn_exit_statoGioco, 600, 640, 200, 90, null);
			 
			 StateGame.cannon.render(g);
			 StateGame.collectBallMap.render(g);
			 StateGame.collectBall.render(g);
			 
			 if(StateGame.pause) {
				StateGame.paused.render(g);	
			 }
			    
			 if(StateGame.exit) {
			    g.drawImage(Assets.quitGame, 200, 200, 400, 400, null);
			 }
			 if(CollectBall.victory) {				 
				 g.drawImage(Assets.victory, 170, 170, 500, Launcher.GAME_HEIGHT/2, null);
				 g.drawImage(Assets.dragon, 350, StateGame.yDragon, 150, 150, null);
			 }
			 if(CollectBall.gameOver) {		        	
		        g.drawImage(Assets.game_over, 170, 150, 500, Launcher.GAME_HEIGHT/2, null);
		     }
			 
		     
		
	}
}
