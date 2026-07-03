package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import model.Model;
import model.ModelImpl;
import resources.LoadResources;

/**
 * This Class implements {@link State}. This class represent the OPTION STATE of
 * the application. It show the instruction to play the game and the story of
 * the Prisoner.
 * 
 * @author Luca
 *
 */
public class StateOption implements State {

	private final Model model;

	/**
	 * Initialize the {@link Model}.
	 * 
	 * @param model
	 *            a Model instance.
	 */
	public StateOption(final ModelImpl model) {
		this.model = model;
	}

	@Override
	public void update() {
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(LoadResources.getInstance().getStateOptions(), 0, 0, null);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.model.setState(StateEnum.MENU_STATE);
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
	}
}
