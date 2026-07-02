package game;

/**
 * 
 * 
 * @author Aurora
 *
 */
public class Run implements Runnable {
	private Thread thread;
	private boolean isRunning = false; //
	
	//__START__
	public void startGame() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	//Spostare!?
	//__STOP__
	
	/**
	 * 
	 * 
	 */
	public void stopGame() {
		try {
			thread.join();
			isRunning = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * Method for updating all elements
	 */
	public void gameUpdate() {
		/*if (gameState == GAME_PLAYING_STATE) {
			clouds.update();
			land.update();
			mainCharacter.update();
			enemiesManager.update();
			if (enemiesManager.isCollision()) {
				mainCharacter.playDeadSound();
				gameState = GAME_OVER_STATE;
				mainCharacter.dead(true);
			}
		}*/
	}
	@Override
	public void run() {
		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		//long endProcessGame;
		//long lag = 0;

		while (true) {
			gameUpdate();
			//repaint();
			//endProcessGame = System.nanoTime();
			elapsed = (lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) (elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
	}

  /**
   * The main methods that launches the game.
   * 
   * @param arg is unused
   */
  public static void main(String[] arg) {
    new Run();
  }

}
