package main.java.controller;

import java.util.List;

import main.java.model.*;

public class GameLoopImpl implements GameLoop{
	private boolean running;
	private boolean paused = false;
	final double frameRate = 1 /60.0;
	double lastKnownTime= System.currentTimeMillis();
	final private Tetris game;
	//private InputHandler iH;
/*	
 * still not developed TODO
 * 
 * private View view;
 * 
 * 
*/	
	GameLoopImpl(Tetris game /*, View view */){
		this.game = game;
		//this.view = view; TODO
	}
	
	public void run() {
		//double t = 0;			not sure if we need the passed time
		lastKnownTime = System.currentTimeMillis();

		List<Input> gameAction;
		while (running)
		{
			if (isPaused()) {
				this.gamePaused();
			}else {
				double thisTime = System.currentTimeMillis();
				double elapsed = thisTime - lastKnownTime;
				lastKnownTime = thisTime;  
				
				while( elapsed > 0.0) {
					double delta = Math.min(elapsed, frameRate);
					gameAction = iH.handler();
					game.sendInput(gameAction);
					game.update(gameAction, elapsed); 
					elapsed -= delta;
					//t += delta;
				}
			 this.renderGame();
			}	  
		}
	}
	
	
	public boolean isPaused() {
		return this.paused;
	}
	
	public boolean isRunning() {
		return this.running;
	}

	private void gamePaused() {
		// TODO
		this.paused=false;
	}

	private void renderGame() {
		// TODO Auto-generated method stub
		
	}

	private void inputHandler() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void start() {
		this.running=true;
		this.run();
	}


	@Override
	public void togglePause() {
		this.paused=!this.paused;
	}

}
