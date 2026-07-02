package model;

import java.awt.event.MouseEvent;

import minigames.MiniGame;

public interface ModelInterface {
    /**
     * @author RealTutsGML (youtube)
	 * This class operates all the in game logic and computations.
	 */
    /**
	 * Computes all game logic at every loop.
	 */
	public void tick();
    /**
	 * loads a given minigame.
	 * @param minigame
	 */
	public int startGame(MiniGame game);
    /**
	 * Subject for user input.
	 */
	public void keyPressed (int key);
    /**
	 * Subject for user input.
	 */
	public void keyReleased (int key);
    /**
	 * Subject for user input.
	 */
	public void mouseDragged(MouseEvent e);
    /**
	 * Subject for user input.
	 */
	public void mouseMoved(MouseEvent e);
    /**
	 * Subject for user input.
	 */
	public void mouseClicked(MouseEvent e);

}
