package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import elements.*;
import levels.*;
import model.Model;

/**
 * This Class implements {@link State}. This class represent the GAME STATE of
 * the application. It create a new {@link Level} and the set of keys who will contains
 * the keys pressed by the user. All the graphics element present on the Level
 * can be updated and added in a Graphics2D. 
 * 
 * @author Luca
 *
 */
public class StateGame implements Game {

	private final Model model;
	private final Level level;
	private final Set<Integer> keys;

	/**
	 * Initialize the {@link Model} the {@link Level} and the keys Set.
	 * 
	 * @param model
	 *            a Model instance.
	 */
	public StateGame(final Model model) {
		this.model = model;
		this.level = new LevelImpl();
		this.keys = new HashSet<>();
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public void resetGame() {
		this.keys.clear();
		this.level.resetLevel();
	}

	/**
	 * Call the Update methods of all the {@link Element} present in the
	 * {@link Level} - Update or Remove the {@link Bullet}; - Update or Remove
	 * {@link EnemyGun}; - Check if the game is win or lose.
	 */
	@Override
	public void update() {
		this.level.getBackground().update();
		for (EnemyGun e : this.level.getEnemies()) {
			e.update();
		}
		for (int i = 0; i < this.level.getEnemies().size(); i++) {
			if (this.level.getEnemies().get(i).isDead()) {			
				this.level.getEnemies().remove(i);
			}
		}
		for (Bullet b : this.level.getBulletList()) {
			b.update();
		}
		if (this.level.getPlayer().isAlive()) {
			if (this.level.getEnemies().isEmpty()) {
				// GAME WIN condition:
				this.model.setState(StateEnum.WIN_STATE);
			}
			this.level.getPlayer().update(this.keys);
		} else {
			// GAME OVER condition:
			this.model.setState(StateEnum.GAMEOVER_STATE);
		}
	}

	@Override
	public void print(Graphics2D g) {
		this.level.getBackground().print(g);
		for (EnemyGun e : this.level.getEnemies()) {
			e.print(g);
		}
		for (Bullet b : this.level.getBulletList()) {
			b.print(g);
		}
		this.level.getPlayer().print(g);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.keys.clear();
			this.model.setState(StateEnum.MENU_STATE);
		} else {
			this.keys.add(key.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		keys.remove(key.getKeyCode());
	}
}