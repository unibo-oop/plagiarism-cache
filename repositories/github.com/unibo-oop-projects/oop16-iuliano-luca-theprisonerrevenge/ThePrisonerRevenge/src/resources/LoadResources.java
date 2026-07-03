package resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * This Class is based on the SINGLETON PATTERN with lazy initialization. This
 * Class load all the graphics resource from PNG files and initialize Maps and
 * Lists who contains all the frames of the animations for example of the
 * specific {@link elements.Character} action and all the others graphics
 * elements as BufferedImage. This Class can be initialized one time to respect
 * the Singleton Pattern.
 * 
 * @author Luca
 */
public class LoadResources {

	public static final int SP_WIDTH = 37;
	public static final int SP_HEIGHT = 44;
	public static final int ZERO = 0;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 320;
	public static final int SP_WIDTH_MEDIUM = SP_WIDTH * 2;
	public static final int SP_WIDTH_LARGE = SP_WIDTH * 3;
	public static final int MAP_WIDTH_LARGE = WIDTH * 3;

	private static final String SP_PLAYER_URL = "/graphics/player/Player.png";
	private static final String SP_ENEMY_URL = "/graphics/enemy/Enemy.png";
	private static final String SP_MAP_URL = "/graphics/backgrounds/map.png";
	private static final String STATE_OPTION_URL = "/graphics/backgrounds/options.png";
	private static final String STATE_GAMEWIN_URL = "/graphics/backgrounds/gamewin.png";
	private static final String STATE_GAMEOVER_URL = "/graphics/backgrounds/gameover.png";
	private static final String STATE_MENU_URL = "/graphics/backgrounds/menu.png";
	private static final String TEXT_URL = "/graphics/backgrounds/texts.png";

	private final BufferedImage stateMenu;
	private final BufferedImage stateOptions;
	private final BufferedImage stateGameOver;
	private final BufferedImage stateWin;
	private final BufferedImage playerSprite;
	private final BufferedImage enemySprite;
	private final BufferedImage background;
	private final BufferedImage sky;

	private final Map<String, BufferedImage> text;
	private final Map<String, List<BufferedImage>> player;
	private final Map<String, List<BufferedImage>> enemy;

	private static LoadResources resourceInstance;

	/**
	 * initialize all the BufferedImages and the Lists of BufferedImage who
	 * contains the sprite of Characters, the map images and all the elements of
	 * the menus. This builder will be called only one time and only if the
	 * single instance of this Class is null (lazy initialization).
	 */
	private LoadResources() {
		System.out.println("Resources intialized...");
		this.stateOptions = loadImage(STATE_OPTION_URL);
		this.stateWin = loadImage(STATE_GAMEWIN_URL);
		this.stateGameOver = loadImage(STATE_GAMEOVER_URL);
		this.stateMenu = loadImage(STATE_MENU_URL);
		this.sky = loadImage(SP_MAP_URL).getSubimage(ZERO, ZERO, MAP_WIDTH_LARGE, HEIGHT);
		this.background = loadImage(SP_MAP_URL).getSubimage(ZERO, HEIGHT, MAP_WIDTH_LARGE, HEIGHT);
		this.playerSprite = loadImage(SP_PLAYER_URL);
		this.enemySprite = loadImage(SP_ENEMY_URL);

		// load all text of menu:
		this.text = new HashMap<>();
		this.text.put("start", loadImage(TEXT_URL).getSubimage(ZERO, SP_HEIGHT * 0, SP_WIDTH_LARGE, SP_HEIGHT));
		this.text.put("options", loadImage(TEXT_URL).getSubimage(ZERO, SP_HEIGHT * 1, SP_WIDTH_LARGE, SP_HEIGHT));
		this.text.put("exit", loadImage(TEXT_URL).getSubimage(ZERO, SP_HEIGHT * 2, SP_WIDTH_LARGE, SP_HEIGHT));
		this.text.put("selector", loadImage(TEXT_URL).getSubimage(ZERO, SP_HEIGHT * 3, SP_WIDTH, SP_HEIGHT));
		// Name and LifePoints:
		this.text.put("Name", loadImage(TEXT_URL).getSubimage(ZERO, SP_HEIGHT * 4, SP_WIDTH_MEDIUM, SP_HEIGHT));
		this.text.put("LifePoints", loadImage(TEXT_URL).getSubimage(ZERO, SP_HEIGHT * 5, SP_WIDTH, SP_HEIGHT));

		// load elements animations:
		this.player = new HashMap<>();
		this.player.put("Wait", new ArrayList<BufferedImage>());
		this.player.put("Run", new ArrayList<BufferedImage>());
		this.player.put("Fly", new ArrayList<BufferedImage>());
		this.player.put("Fire", new ArrayList<BufferedImage>());
		this.player.put("Bullet", new ArrayList<BufferedImage>());
		this.player.put("Name", new ArrayList<BufferedImage>());
		this.player.put("LifePoints", new ArrayList<BufferedImage>());
		// wait animation:
		loadSpritesColl(this.player.get("Wait"), this.playerSprite, 12, SP_WIDTH, SP_HEIGHT * 0, SP_WIDTH, SP_HEIGHT, ZERO);
		// run animation:
		loadSpritesColl(this.player.get("Run"), this.playerSprite, 12, SP_WIDTH, SP_HEIGHT * 1, SP_WIDTH, SP_HEIGHT, ZERO);
		// fly animation:
		loadSpritesColl(this.player.get("Fly"), this.playerSprite, 6, SP_WIDTH, SP_HEIGHT * 2, SP_WIDTH, SP_HEIGHT,	ZERO);
		// fire animation:
		loadSpritesColl(this.player.get("Fire"), this.playerSprite, 12, SP_WIDTH, SP_HEIGHT * 3, SP_WIDTH, SP_HEIGHT, ZERO);
		loadSpritesColl(this.player.get("Fire"), this.playerSprite, 8, SP_WIDTH_LARGE, SP_HEIGHT * 4, SP_WIDTH_LARGE, SP_HEIGHT, ZERO);
		// bullet frames:
		loadSpritesColl(this.player.get("Bullet"), this.playerSprite, 2, SP_WIDTH, SP_HEIGHT * 5, SP_WIDTH, SP_HEIGHT, ZERO);

		this.enemy = new HashMap<>();
		this.enemy.put("Wait", new ArrayList<BufferedImage>());
		this.enemy.put("Run", new ArrayList<BufferedImage>());
		this.enemy.put("Fire", new ArrayList<BufferedImage>());
		this.enemy.put("Dead", new ArrayList<BufferedImage>());
		this.enemy.put("Bullet", new ArrayList<BufferedImage>());
		// wait animation:
		loadSpritesColl(this.enemy.get("Wait"), this.enemySprite, 12, SP_WIDTH, SP_HEIGHT * 0, SP_WIDTH, SP_HEIGHT,	ZERO);
		// run animation:
		loadSpritesColl(this.enemy.get("Run"), this.enemySprite, 12, SP_WIDTH, SP_HEIGHT * 1, SP_WIDTH, SP_HEIGHT,	ZERO);
		// fire animation:
		loadSpritesColl(this.enemy.get("Fire"), this.enemySprite, 4, SP_WIDTH, SP_HEIGHT * 2, SP_WIDTH, SP_HEIGHT, ZERO);
		loadSpritesColl(this.enemy.get("Fire"), this.enemySprite, 4, SP_WIDTH_MEDIUM, SP_HEIGHT * 2, SP_WIDTH_MEDIUM, SP_HEIGHT, SP_WIDTH * 4);
		loadSpritesColl(this.enemy.get("Fire"), this.enemySprite, 4, SP_WIDTH, SP_HEIGHT * 2, SP_WIDTH, SP_HEIGHT, SP_WIDTH * 12);
		// dead animation:
		loadSpritesColl(this.enemy.get("Dead"), this.enemySprite, 12, SP_WIDTH, SP_HEIGHT * 3, SP_WIDTH, SP_HEIGHT,	ZERO);
		// bullet frames:
		loadSpritesColl(this.enemy.get("Bullet"), this.enemySprite, 2, SP_WIDTH, SP_HEIGHT * 4, SP_WIDTH, SP_HEIGHT, ZERO);
	}

	/**
	 * Return the exclusive instance of this Class (pattern Singleton). If the
	 * Class is null, all the elements will be initialized and then returned.
	 * 
	 * @return LoadResources instance.
	 */
	public static LoadResources getInstance() {
		if (resourceInstance == null) {
			resourceInstance = new LoadResources();
		}
		return resourceInstance;
	}

	/**
	 * Get animation Map for {@link elements.Player}.
	 * 
	 * @return a Map of String and List of BufferedImages, contains all the
	 *         possible animations of the Player Character.
	 */
	public Map<String, List<BufferedImage>> getPlayerAnimations() {
		return this.player;
	}

	/**
	 * Get animation Map for {@link elements.EnemyGun}.
	 * 
	 * @return a Map of String and List of BufferedImages, contains all the
	 *         possible animations of the Enemy Character.
	 */
	public Map<String, List<BufferedImage>> getEnemyAnimations() {
		return this.enemy;
	}

	/**
	 * Get method for All Texts.
	 * 
	 * @return a Map of Strings and BufferedImages, contains all the texts of
	 *         all menus.
	 */
	public Map<String, BufferedImage> getTextImage() {
		return this.text;
	}

	/**
	 * State Menu image.
	 * 
	 * @return a BufferedImage that is the image of background in menu state of
	 *         application.
	 */
	public BufferedImage getStateMenu() {
		return this.stateMenu;
	}

	/**
	 * State Game Win image.
	 * 
	 * @return a BufferedImage that is the image of background in game win state
	 *         of application.
	 */
	public BufferedImage getStateWin() {
		return this.stateWin;
	}

	/**
	 * State Game Over image.
	 * 
	 * @return a BufferedImage that is the image of background in game over
	 *         state of application.
	 */
	public BufferedImage getStateGameOver() {
		return this.stateGameOver;
	}

	/**
	 * State Option image.
	 * 
	 * @return a BufferedImage that is the image of background in option state
	 *         of application.
	 */
	public BufferedImage getStateOptions() {
		return this.stateOptions;
	}

	/**
	 * Get BufferedImage for {@link elements.Background}.
	 * 
	 * @return a BufferedImage that is the image of the background.
	 */
	public BufferedImage getBackground() {
		return this.background;
	}

	/**
	 * Sky image BufferedImage for {@link elements.Background}.
	 * 
	 * @return a BufferedImage that is the image of the sky.
	 */
	public BufferedImage getSky() {
		return this.sky;
	}

	/**
	 * Add all the frames of the animation to the specific List of
	 * BufferedImage.
	 * 
	 * @param frameList
	 *            List of BufferedImage where will be add the frames.
	 * @param frameTableImage
	 *            BufferedImage contains the grid of character's animations
	 *            frames.
	 * @param frameNumber
	 *            number of frames of the specific animation.
	 * @param x
	 *            the X coordinate of the upper-left corner of the specified
	 *            frame.
	 * @param y
	 *            the y coordinate of the upper-left corner of the specified
	 *            frame.
	 * @param frameWidth
	 *            the single frame width.
	 * @param frameHeight
	 *            the single frame height.
	 * @param offset
	 *            the offset where start to get frames.
	 */
	private static void loadSpritesColl(final List<BufferedImage> frameList, final BufferedImage frameTableImage,
			final int frameNumber, final int x, final int y, final int frameWidth, final int frameHeight, final int offset) {
		for (int i = 0; i < frameNumber; i++) {
			frameList.add(frameTableImage.getSubimage(offset + i * x, y, frameWidth, frameHeight));
		}
	}

	/**
	 * Loads resources using the same class loader that loaded this current
	 * class.
	 * 
	 * @param URL
	 *            the path of the specific resource.
	 * @return a BufferedImage who contains the image indicated.
	 * @throws IOException
	 *             if an error occurs during reading.
	 */
	private static BufferedImage loadImage(final String URL) {
		try {
			return ImageIO.read(LoadResources.class.getResourceAsStream(URL));
		} catch (IOException e) {
			System.out.println("Error image not found...");
			e.printStackTrace();
			return null;
		}
	}
}
