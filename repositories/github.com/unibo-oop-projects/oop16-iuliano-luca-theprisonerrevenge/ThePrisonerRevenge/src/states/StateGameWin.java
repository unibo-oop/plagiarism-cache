package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import model.Model;
import resources.LoadResources;

/**
 * This Class implements {@link State}. This class represent the GAME WIN STATE
 * of the game. The user can only go back on MENU StateEnum.
 * 
 * @author Luca
 *
 */
public class StateGameWin implements State {

	private final Model model;

	/**
	 * Initialize the {@link Model}.
	 * 
	 * @param model
	 *            a Model instance.
	 */
	public StateGameWin(final Model model) {
		this.model = model;
	}

	@Override
	public void update() {
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(LoadResources.getInstance().getStateWin(), 0, 0, null);
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
