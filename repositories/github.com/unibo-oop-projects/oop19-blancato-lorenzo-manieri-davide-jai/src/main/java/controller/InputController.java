package controller;

import javafx.scene.Scene;
import model.Player;
import model.PlayerImpl;
import program.Game;

/**
 * 	class for control player movement and fire
 *
 */
public class InputController {
	
	private Player player;
	
	
	/**
	 * 
	 *	 @param scene	
	 */
	public InputController(Scene scene) {
		this.player = PlayerImpl.getInstance();
		Scene scena = scene; 
		
		scena.setOnKeyPressed(e -> {
			switch(e.getCode()) {
				case A: player.setDirection(Direction.LEFT); 
						break; 
				case D: player.setDirection(Direction.RIGHT);
						break; 
				case W: player.setDirection(Direction.UP);
						break;
				case S: player.setDirection(Direction.DOWN);
						break; 
				case SPACE: player.setDirection(Direction.SPACE);
						break;
				case P: if (Game.onRun) {
							Game.animator.stop();
							TimeManagerImpl.getInstance().pause();
							SpawnManager.resetSpawn();
							Game.onRun = false;
						}else {
							Game.animator.start();
							TimeManagerImpl.getInstance().resume();
							SpawnManager.SpawnTimer();
							Game.onRun = true;
						}
						break;
				default: 
						break; 
			}
		});
	
		scena.setOnKeyReleased(e ->{
			switch(e.getCode()) {
			case A: this.player.deleteDirection(Direction.LEFT);
					break; 
			case D: player.deleteDirection(Direction.RIGHT);
					break; 
			case W: player.deleteDirection(Direction.UP);
					break;
			case S: player.deleteDirection(Direction.DOWN);
					break; 
			case SPACE: player.deleteDirection(Direction.SPACE);
					break;
		
			default: 
					break; 
			}
		});
	}
	
	/**
	 * Enumeration for direction management
	 */
	public enum Direction{
		UP, DOWN, LEFT, RIGHT, SPACE
	}

}
