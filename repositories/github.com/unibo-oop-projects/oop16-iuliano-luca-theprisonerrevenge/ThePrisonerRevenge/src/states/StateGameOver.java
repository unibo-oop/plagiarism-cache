package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import model.Model;
import resources.LoadResources;

/**
 * This Class implements {@link State}. It represent the GAME OVER State of
 * the game. The user can only go back on MENU STATE.
 * 
 * @author Luca
 *
 */
public class StateGameOver implements State {

	private final Model model;

	/**
	 * Initialize the {@link Model}.
	 * 
	 * @param model
	 *            a Model instance.
	 */
	public StateGameOver(final Model model) {
		this.model = model;
	}

	@Override
	public void update() {
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(LoadResources.getInstance().getStateGameOver(), 0, 0, null);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.model.resetGame();
			this.model.setState(StateEnum.MENU_STATE);
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
	}

}
