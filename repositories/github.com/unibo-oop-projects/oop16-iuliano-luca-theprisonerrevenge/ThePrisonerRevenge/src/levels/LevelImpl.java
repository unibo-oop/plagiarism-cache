package levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elements.Background;
import elements.Bullet;
import elements.EnemyGun;
import elements.Player;
import resources.LoadResources;
import view.ViewImpl;

/**
 * This class implements {@link Level}. It represent the Level of the game, it
 * create all the elements present on screen when the user start a game: like
 * Background, Player, Enemies ant it initialize the bulletsList where will be
 * add the bullets fired by the Characters. It include the
 * {@link resources.LoadResources} instance to pass the sprite animation to
 * every element.
 * 
 * @author Luca
 *
 */
public class LevelImpl implements Level {

	private static final int CHARACTER_Y = (ViewImpl.HEIGHT / ViewImpl.SCALE) - 100;
	private static final int MAP_Y_OFFSET = (ViewImpl.HEIGHT / ViewImpl.SCALE) - LoadResources.HEIGHT;
	private static final int ZERO = 0;
	private static final int ENEMYS_NUM_MIN = 1;
	private static final int ENEMYS_NUM_MAX = 30;
	private static final int ENEMYS_NUM = 25;
	private final List<Bullet> bulletsList;
	private final List<EnemyGun> enemyList;
	private final LoadResources resource = LoadResources.getInstance();
	private Player player;
	private Background background;

	/**
	 * Initialize the Level, create the Player, and populate the level with
	 * Enemies.
	 */
	public LevelImpl() {
		this.bulletsList = new ArrayList<>();
		this.enemyList = new ArrayList<>();
		this.background = new Background(ZERO, MAP_Y_OFFSET);
		this.player = new Player(ZERO, CHARACTER_Y, this.background, this.resource.getPlayerAnimations(), this.bulletsList);
		this.generateEnemies(ENEMYS_NUM);
	}

	@Override
	public Background getBackground() {
		return this.background;
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public List<EnemyGun> getEnemies() {
		return this.enemyList;
	}

	@Override
	public List<Bullet> getBulletList() {
		return this.bulletsList;
	}

	@Override
	public void resetLevel() throws IllegalStateException {
		if (this.enemyList.isEmpty() || !this.player.isAlive()) {
			this.bulletsList.clear();
			this.enemyList.clear();
			this.background = new Background(ZERO, MAP_Y_OFFSET);
			this.player = new Player(ZERO, CHARACTER_Y, this.background, this.resource.getPlayerAnimations(), this.bulletsList);
			this.generateEnemies(ENEMYS_NUM);
		} else {
			throw new IllegalStateException("ERROR! The game can't be reset before it's over.");
		}
	}

	@Override
	public void generateEnemies(final int num) throws IllegalArgumentException {
		if (num < ENEMYS_NUM_MIN || num > ENEMYS_NUM_MAX) {
			throw new IllegalArgumentException("ERROR! The level can't have less then 1 or more then 30 Enemies.");
		} else {
			this.enemyList.clear();
			Random random = new Random();
			int randomNumber;
			for (int i = 0; i < num; i++) {
				randomNumber = random.nextInt(950) + 600;
				this.enemyList.add(new EnemyGun(randomNumber, CHARACTER_Y, this.player, this.background, this.resource.getEnemyAnimations(), this.bulletsList));
			}
		}
	}
}
