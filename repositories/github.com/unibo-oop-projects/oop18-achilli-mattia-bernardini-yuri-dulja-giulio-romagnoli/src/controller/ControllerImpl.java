package controller;

import model.Model;
import utility.Utilities;
import view.View;

/**
 * 
 * This class represents the controller.
 * 
 */

public final class ControllerImpl implements Controller {
	private final static int PERIOD = 24; //60 FPS
	
	
	private GameLoop gameLoop;
	private Model model;
	private View view;
	//Command list, mouse

	
	public ControllerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGame() {
		gameLoop = new GameLoop();
		gameLoop.start();
	}

	@Override
	public void stopGame() {
		gameLoop.stopGameLoop();

	}
	
	private void endGame() {
		stopGame();
	}
	
	private class GameLoop extends Thread { 
		private volatile boolean running;
		
		public GameLoop() {
			super();
			running = true;
		}
		
		public void run() {
			long lastTime = System.currentTimeMillis();
			while(running && !model.checkGameOver()) { /* Running and not gameover */
				final long current = System.currentTimeMillis();
                final int elapsed = (int) (current - lastTime);
                processInput();
                //model.update(elapsed);
                //view.setStateGame(getEntitiesForView(), model.getGameData());
                Utilities.waitForNextFrame(PERIOD, current);
                lastTime = current;
			}
			if(model.checkGameOver()) {
				ControllerImpl.this.endGame();
			}
		}
		
		private void processInput() {
			//Input from mouse command example
		}
		
		public void stopGameLoop() {
            this.running = false;
        }

        @Override
        public void start() {
            this.running = true;
            super.start();
        }
	}
}
