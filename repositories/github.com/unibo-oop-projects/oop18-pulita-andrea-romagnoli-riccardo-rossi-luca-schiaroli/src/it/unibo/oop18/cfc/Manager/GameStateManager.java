// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

package it.unibo.oop18.cfc.Manager;

import java.awt.Graphics2D;
import it.unibo.oop18.cfc.GameState.*;
import it.unibo.oop18.cfc.Util.JukeBox;


public class GameStateManager {
	
	private GameState introState;
	private GameState menuState;
	private GameState playState;
	private GameState gameOverState;
	private GameState pauseState;
	
	private GameState currentState;
	
	public GameStateManager() {		
		JukeBox.init();
		pauseState = new PauseState(this);
		introState = new IntroState(this);
		menuState = new MenuState(this);
		gameOverState = new GameOverState(this);
		playState = new PlayState(this);
		setState(GameStates.INTRO);
	}
	
	public void newGame() {
		playState.init();
		setState(GameStates.PLAY);
	}
	
	public void setState(GameStates gameState) {
		switch(gameState) {
			case INTRO:
				currentState = introState;
				introState.init();
				break;
			case MENU:
				currentState = menuState;
				menuState.init();
				break;
			case PLAY:
				currentState = playState;
				break;
			case GAMEOVER:
				currentState = gameOverState;
				gameOverState.init();
				break;
			case PAUSE:
				currentState = pauseState;
			default:
				break;
		}
	}
	
	public void update() {
		currentState.update();
	}
	
	public void draw(Graphics2D g) {
		currentState.draw(g);
	}
	
	public PlayState getPlayState() {
		return (PlayState) playState;
	}
	
	public GameState getCurrentGameState() {
		return currentState;
	}
}
