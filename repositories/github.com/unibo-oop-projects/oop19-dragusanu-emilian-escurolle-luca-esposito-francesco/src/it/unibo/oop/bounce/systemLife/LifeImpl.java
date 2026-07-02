package it.unibo.oop.bounce.systemLife;

import it.unibo.oop.bounce.obstacles.Checkpoint;
import it.unibo.oop.bounce.screens.GameOver;
import it.unibo.oop.bounce.screens.Hud;
import it.unibo.oop.bounce.screens.PlayScreen;
import it.unibo.oop.bounce.game.Bounce;

public class LifeImpl implements Life {
	
	public int lives;
	private GameOver gameOver;
	private final Hud hud;
	private Checkpoint checkpoint;
	private Bounce game;
	

	public LifeImpl(PlayScreen playscreen) {
		this.lives = 3;
		this.hud = playscreen.getHud();
	}

	@Override
	public void extraLife() {
		this.lives++;
		

	}

	@Override
	public void lessLife() {
		this.lives--;
		hud.lessLives();
//		if(this.lives == 0) {
//			gameOver.dispose();
//			gameOver.newScreen(this.game, checkpoint.getX(checkpoint), checkpoint.getY(checkpoint));
//			
//		}
//		else{
//			gameOver.newScreen(this.game, checkpoint.getX(checkpoint), checkpoint.getY(checkpoint));
//		}

	}
	
	public int getLives() {
		return this.lives;
	}
	

}
