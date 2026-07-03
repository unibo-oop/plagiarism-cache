package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import model.Model;
import resources.LoadResources;

/**
 * This Class implements {@link State}. This class represent the MENU STATE of
 * the application. The user can chose to start the game, see the options or
 * exit the application by moving the selector and pressing Enter. In base on
 * the chose, the current {@link State} on {@link Model} will be change.
 * 
 * @author Luca
 *
 */
public class StateMenu implements State {

	public static int TEXT_X = 190;
	public static int TEXT_Y = 235;
	public static int TEXT_OFFSET = 28;
	private final Model model;
	private final Map<String, Integer> menuMap;
	private final int xSelector;
	private int ySelector;

	/**
	 * Initialize the {@link Model} and all the settings to navigate on
	 * principal menu.
	 * 
	 * @param model
	 *            a Model instance.
	 */
	public StateMenu(final Model model) {
		this.model = model;
		this.xSelector = TEXT_X - TEXT_OFFSET;
		this.ySelector = TEXT_Y;
		this.menuMap = new HashMap<>();
		this.menuMap.put("start", TEXT_Y + TEXT_OFFSET * 0);
		this.menuMap.put("options", TEXT_Y + TEXT_OFFSET * 1);
		this.menuMap.put("exit", TEXT_Y + TEXT_OFFSET * 2);
	}

	@Override
	public void update() {
	}

	@Override
	public void print(Graphics2D g) {
		g.drawImage(LoadResources.getInstance().getStateMenu(), 0, 0, null);
		g.drawImage(LoadResources.getInstance().getTextImage().get("selector"), this.xSelector, this.ySelector, null);
		for (Map.Entry<String, Integer> pair : this.menuMap.entrySet()) {
			g.drawImage(LoadResources.getInstance().getTextImage().get(pair.getKey()), TEXT_X, pair.getValue(), null);
		}
	}

	/**
	 * Move the selector UP on the menu.
	 */
	private void moveUpSelector() {
		if (this.ySelector < TEXT_Y + TEXT_OFFSET) {
			this.ySelector = TEXT_Y + TEXT_OFFSET * 2;
		} else {
			this.ySelector -= TEXT_OFFSET;
		}
	}

	/**
	 * Move the selector DOWN on the menu.
	 */
	private void moveDonwSelector() {
		if (this.ySelector > TEXT_Y + TEXT_OFFSET * 1) {
			this.ySelector = TEXT_Y;
		} else {
			this.ySelector += TEXT_OFFSET;
		}
	}

	/**
	 * Changes the {@link State} on {@link Model} in base of the position of the
	 * selector.
	 */
	private void enterChoose() {
		if (this.menuMap.get("start") == this.ySelector) {
			this.model.setState(StateEnum.GAME_STATE);
		}
		if (this.menuMap.get("options") == this.ySelector) {
			this.model.setState(StateEnum.OPTION_STATE);
		}
		if (this.menuMap.get("exit") == this.ySelector) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_UP) {
			this.moveUpSelector();
		}
		if (key.getKeyCode() == KeyEvent.VK_DOWN) {
			this.moveDonwSelector();
		}
		if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			this.enterChoose();
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
	}
}
